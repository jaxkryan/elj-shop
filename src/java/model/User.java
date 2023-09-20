package model;

public class User {
    private int id;
    private int roleId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String street;
    private String city;
    private String province;
    private String country;
    private String phone;
    private String email;
    private String password;

    public User() {
    }

    public User(int id, int roleId, String firstName, String lastName, String dateOfBirth, String street, String city, String province, String country, String phone, String email, String password) {
        this.id = id;
        this.roleId = roleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "User{id=" + id
                + ", roleId=" + roleId
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + ", dateOfBirth=" + dateOfBirth
                + ", street=" + street
                + ", city=" + city
                + ", province=" + province
                + ", country=" + country
                + ", phone=" + phone 
                + ", email=" + email
                + ", password=" + password
                + "}";
    }
}
