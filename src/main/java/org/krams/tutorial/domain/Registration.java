package org.krams.tutorial.domain;
import javax.persistence.*;

@Entity
@Table(name = "PEOPLE")
public class Registration{
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONENUMBER")
    private  Integer phoneNumber;

    @Column(name = "ABOUT")
    private  String about;

    @Column(name = "UNIVERSITY")
    private  String university;

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
