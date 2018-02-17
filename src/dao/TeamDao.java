package dao;

import model.Developer;
import model.ModelDraft;
import model.Team;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;


public class TeamDao extends DaoDraft {
    private String path = "./src/team.txt";
    private String upperPath = "./src/project.txt";

    public void safe(Team team){
        safe(team,path);
    }

    public void remove(long id){
        remove(id,path);
        rewriteId(id,upperPath);
    }


    @Override
    public Team getById(long id) {
            Team team = null;
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                DeveloperDao dd = new DeveloperDao();
                boolean rly=true;
                while ((line = br.readLine()) != null) {
                    if (Objects.equals(line.split(",")[0], Long.toString(id))) {
                        rly = false;
                        long ID = Long.valueOf(line.split(",")[0]);
                        String name = line.split(",")[1];
                        String allIdOfDevelopers = line.split("/")[1];
                        HashSet<Developer> developers = new HashSet<>();
                        for(int i =0;i<allIdOfDevelopers.split(",").length;i++){
                            Developer developer = dd.getById(Long.valueOf(allIdOfDevelopers.split(",")[i]));
                            developers.add(developer);
                        }
                        team = new Team(ID,name,developers);
                    }
                }
                if(rly)System.out.println("Команда с ID "+id+" не найдена. ");
            } catch (IOException e) {
                System.out.println("Ошибка"+e);
            }
            return team;
        }



    public ArrayList<Team> getAll() {
        ArrayList<Team>AL = new ArrayList<>();
        ArrayList<Long>ids = getIds(path);
        for(long i:ids){
            Team team = getById(i);
            String Teams = "\n"+team.getId() + "," + team.getName() + ".";
            System.out.println(Teams);
            team.getDevelopers();
            AL.add(team);
        }
        return AL;
    }
}
