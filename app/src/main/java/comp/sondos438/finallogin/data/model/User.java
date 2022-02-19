package comp.sondos438.finallogin.data.model;

import java.time.LocalDate;

public class User {
    private double ID;
    private String name;
    private String email;
    private String password;
    private String nationality;
    private String gender;
    private String phone;
    private String Type;


public User(){

}
    public User(double ID, String name, String email, String password, String nationality, String gender, String phone, String Type) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password=password;
        this.nationality = nationality;
        this.gender = gender;
        this.phone = phone;
        this.Type=Type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getID() {
        return ID;
    }

    public void setID(double ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getType() { return Type; }

    public void setType(String type) {
        Type = type;
    }
}
