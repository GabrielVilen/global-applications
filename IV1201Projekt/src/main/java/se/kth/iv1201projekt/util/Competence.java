package se.kth.iv1201projekt.util;

/**
 *
 * @author Samy
 */
public class Competence {
    private String name;
    private double yearsOfExperience;

    public Competence(String name, double yearsOfExperience) {
        this.name = name;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(double yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
    
    
}
