/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This file describes the model of Game account
*/
package model;

/**
 *
 * @author Inspiron
 */
public class GameAccount {
    private int id;
    private String realname;
    private String username;
    private String password;
    private String dob;
    private String email;
    private String gender;
    private String avatar;

    public GameAccount(int id, String realname, String username, String password, String dob, String email, String gender, String avatar) {
        this.id = id;
        this.realname = realname;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
}
