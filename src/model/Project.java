package model;

import java.util.HashSet;

public class Project implements ModelDraft {
    long id;
    String name;
    HashSet<Team> teams;

    public Project(long id, String name, HashSet<Team> teams) {
        this.id = id;
        this.name = name;
        this.teams = teams;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getIds() {
        String teamstr="";
        for(Team e:teams){
            teamstr+=e.getId()+",";
        }
        return teamstr;
    }

    public HashSet<Team> getTeams(){
        System.out.println("Teams:");
        for(Team team:teams){
            String Teams = team.getId() + "," +team.getName() + ".";
            System.out.println(Teams);
        }
        return teams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return id == project.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}

