package controller;

import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;
import model.User;
import util.Email;
import util.Helper;

/**
 *
 * @author Huy Nguyen
 */
public class ForgotPasswordController extends HttpServlet {

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
        request.getRequestDispatcher("/jsp/forgotPasswordPage.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        if (email == null || email.equals("")) {
            Helper.setNotification(request, "Email cannot be blank!", "RED");
        } else {
            UserDAO udao = new UserDAO();
            User user = udao.getActiveUserByEmail(email);
            if (user != null) {
                String newPassword = String.copyValueOf(generatePassword(10));
                boolean isResetPasswordSuccess = udao.changePassword(user, Helper.hashPassword(newPassword)) > 0;
                if (isResetPasswordSuccess) {
                    Email.sendEmail(
                            email,
                            "Online Shopping System: Password Changed",
                            "You has reset your password on our website.\n"
                            + "Your new password is: "
                            + newPassword
                    );
                    Helper.setNotification(request, "Your new password was sent to your email!", "GREEN");
                } else {
                    Helper.setNotification(request, "Reset password failed!", "RED");
                }
            } else {
                Helper.setNotification(request, "User does not exist!", "RED");
            }
        }
        request.getRequestDispatcher("/jsp/forgotPasswordPage.jsp").forward(request, response);
    }

    private static char[] generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$%^&*";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for (int i = 4; i < length; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return password;
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
