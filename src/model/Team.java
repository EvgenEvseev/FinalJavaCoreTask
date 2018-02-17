package model;

import java.util.HashSet;

public class Team implements ModelDraft  {
    long id;
    String name;
    HashSet<Developer> developers;

    public Team(long id, String name, HashSet<Developer> developers) {
        this.id = id;
        this.name = name;
        this.developers = developers;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getIds() {
        String developerstr="";
        for(Developer e:developers){
            developerstr+=e.getId()+",";
        }
        return developerstr;
    }

    public HashSet<Developer> getDevelopers(){
        System.out.println("Developers:");
        for(Developer developer:developers){
            String Developers = developer.getId() + "," + developer.getName() + "," + developer.getLastName() +
                    "," + developer.getSpeciality() + "," + developer.getSalary() +".";
            System.out.println(Developers);
        }
        return developers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        return id == team.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
