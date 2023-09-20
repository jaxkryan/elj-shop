package util;

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
    
    public static String getUserRole(int roleId) {
        String[] roles = {"Admin", "Customer", "Seller", "Storage Staff", "Marketing Staff", "Manager"};
        return roles[roleId - 1];
    } 
}
