/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package constant;

/**
 *
 * @author Admin
 */
public interface IConstant {

    String REGEX_FIRSTNAME = "^[a-zA-Z-. ]+$";
    String REGEX_LASTNAME = "^[a-zA-Z-. ]+$";
    String REGEX_STREET = "^[a-zA-Z0-9-. ]+$";
    String REGEX_CITY = "^[a-zA-Z0-9-. ]+$";
    String REGEX_PROVINCE = "^[a-zA-Z0-9-. ]+$";
    String REGEX_COUNTRY = "^[a-zA-Z ]+$";
    String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    String REGEX_PHONE = "^0[0-9]{9}$";
}
