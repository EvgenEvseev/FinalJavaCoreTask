package model;

import java.util.HashSet;

public class Company implements ModelDraft {
    long id;
    String name;
    HashSet<Project> projects;

    public Company(long id, String name, HashSet<Project> projects) {
        this.id = id;
        this.name = name;
        this.projects = projects;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
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

        Company company = (Company) o;

        return id == company.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
