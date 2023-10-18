package util;

import constant.IConstant;
import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = IConstant.HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = IConstant.HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest
                    .getInstance("SHA-256");
            md.update(password.getBytes());
            md.update(IConstant.SALT.getBytes());

            byte[] hashedPassword = md.digest();
            return bytesToHex(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
