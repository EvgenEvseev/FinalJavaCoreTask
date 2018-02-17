package model;

import java.util.HashSet;


public class Customer implements ModelDraft{
    long id;
    String firstName;
    String lastName;
    String address;
    HashSet<Project> projects;

    public Customer(long id, String firstName, String lastName, String address, HashSet<Project> projects) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.projects = projects;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getAddress(){
        return address;
    }

    @Override
    public String getIds() {
        String projectstr="";
        for(Project e:projects){
            projectstr+=e.getId()+",";
        }
        return projectstr;
    }

    public HashSet<Project> getProjects(){
        System.out.println("Projects:");
        for(Project project:projects){
            String Projects = project.getId() + "," + project.getName() + ".";
            System.out.println(Projects);
        }
        return projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
