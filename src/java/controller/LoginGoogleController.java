package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import constant.IConstant;
import dao.CartDAO;
import dao.CartItemDAO;
import dao.CustomerDAO;
import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.CartItem;
import model.GooglePojo;
import model.User;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import util.Helper;

/**
 *
 * @author Huy Nguyen
 */
public class LoginGoogleController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            Helper.setNotification(request, "Login with Google failed!", "RED");
            response.sendRedirect("login");
        } else {
            String redirectURI = request.getScheme() + "://"
                    + request.getServerName() + ":"
                    + request.getServerPort()
                    + request.getRequestURI();
            System.out.println(redirectURI);
            String accessToken = getToken(code, redirectURI);
            GooglePojo googlePojo = getUserInfo(accessToken);
            HttpSession session = request.getSession();
            UserDAO udao = new UserDAO();
            User user = udao.getActiveUserByEmail(googlePojo.getEmail());
            if (user == null) {
                user = new User("Customer", googlePojo.getGiven_name(), googlePojo.getFamily_name(), null, null, null, null, null, null, googlePojo.getEmail(), null);
                int newUserId = udao.insert(user, true);
                user.setId(newUserId);
                CustomerDAO customerDAO = new CustomerDAO();
                customerDAO.insert(user.getId());
                CartDAO cartDAO = new CartDAO();
                cartDAO.insert(user.getId());
            }

            //Reset user infomation
            session.removeAttribute("userId");
            session.removeAttribute("userRole");
            session.removeAttribute("cartItem");

            //Get new infomation
            session.setAttribute("userId", user.getId());
            session.setAttribute("userRole", user.getRole());
            CartDAO cdao = new CartDAO();
            int cartId = cdao.getCartIdByCustomerId(user.getId());
            CartItemDAO cidao = new CartItemDAO();
            Vector<CartItem> cartItem = cidao.getCartItemByCartId(cartId);
            session.setAttribute("cartItem", cartItem);
            response.sendRedirect("home");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private String getToken(final String code, final String redirectURI) throws ClientProtocolException, IOException {
        String response = Request.Post(IConstant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", IConstant.GOOGLE_CLIENT_ID)
                        .add("client_secret", IConstant.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri",redirectURI)
                        .add("code", code)
                        .add("grant_type", IConstant.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    private GooglePojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = IConstant.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        GooglePojo googlePojo = new Gson().fromJson(response, GooglePojo.class);
        return googlePojo;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override

    public String getServletInfo() {
        return "Short description";
    }

}
