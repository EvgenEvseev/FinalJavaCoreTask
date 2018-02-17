package model;

import javax.swing.text.html.HTMLDocument;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Developer implements ModelDraft {
    long id;
    String firstName;
    String lastName;
    String speciality;
    HashSet<Skill> skills;
    BigDecimal salary;

    public Developer(long id, String firstName, String lastName, String speciality, HashSet<Skill> skills, BigDecimal salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.speciality = speciality;
        this.skills = skills;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return firstName;
    }


    @Override
    public String getIds() {
        String skillstr="";
        for(Skill e:skills){
            skillstr+=e.getId()+",";
        }
        return skillstr;
    }

    public String getLastName(){
        return lastName;
    }

    public String getSpeciality(){
        return speciality;
    }


    public HashSet<Skill> getSkills(){
        System.out.println("Skills:");
        for(Skill e:skills){
            System.out.println(e.getId()+", "+e.getName());
        }
        return skills;
    }

    public String getSalary(){
        return String.valueOf(salary);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Developer developer = (Developer) o;

        return id == developer.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
