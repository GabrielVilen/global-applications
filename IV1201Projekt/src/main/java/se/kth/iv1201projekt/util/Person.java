package se.kth.iv1201projekt.util;

/**
 *
 * @author Samy
 */
public class Person {
 private final int id;
 private String name;
 private String surname;
 private String ssn;
 private String email;
 private String role;
 private final String username;


    public Person(int id, String name, String surname, String ssn, String email, String role, String username) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;
        this.role = role;
        this.username = username;
    }
 
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
 
 
}
