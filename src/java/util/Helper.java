package util;

import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Helper {

    /**
     * Block from creating instance
     */
    private Helper() {
    }
    
    public static void setNotification(HttpServletRequest request, String notification, String type) {
        HttpSession session = request.getSession();
        session.setAttribute("notiType", type);
        session.setAttribute("notification", notification);
    }
    
    public static boolean isUserExists(HttpServletRequest request) {
        if (request.getParameter("userId") == null) {
            return false;
        } else {
            int userId = Integer.parseInt(request.getParameter("userId"));
            UserDAO udao = new UserDAO();
            return udao.getById(userId) != null;
        }
    }
}
