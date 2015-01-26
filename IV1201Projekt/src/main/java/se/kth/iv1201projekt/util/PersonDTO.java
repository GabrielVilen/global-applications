package se.kth.iv1201projekt.util;

import java.util.List;

/**
 * Definierar en användare med dess erfarenheter
 * @author Samy
 */
public class PersonDTO {
 private int id;
 private String name;
 private String surname;
 private String ssn;
 private String email;
 private String role;
 private final String username;
 private List<Competence> competences;
 private Availability availability;

    public PersonDTO(String name, String surname, String ssn, String email, String role, String username, List<Competence> competences, Availability availability) {
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;
        this.role = role;
        this.username = username;
        this.competences = competences;
        this.availability = availability;
    }
    
    public PersonDTO(int id, String name, String surname, String ssn, String email, String role, String username, List<Competence> competences, Availability availability) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;
        this.role = role;
        this.username = username;
        this.competences = competences;
        this.availability = availability;
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

    public String getSurname() {
        return surname;
    }

    public String getSsn() {
        return ssn;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public List<Competence> getCompetences() {
        return competences;
    }
    
    public void addCompetence(Competence competence){
        competences.add(competence);
    }
    
    public boolean removeCompetence(String name){
        for(Competence c : competences){
            if(c.equals(name)){
                competences.remove(c);
                return true;
            }
        }
        return false;
    }

    public Availability getAvailability() {
        return availability;
    }

}
