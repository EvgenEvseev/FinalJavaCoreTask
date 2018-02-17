package dao;

import model.Project;
import model.Team;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class ProjectDao extends DaoDraft  {
    private String path = "./src/project.txt";
    private String upperPath = "./src/company.txt";
    private String upperPath2 = "./src/customer.txt";

    public void safe(Project project){
        safe(project,path);
    }

    public void remove(long id){
        remove(id,path);
        rewriteId(id,upperPath);
        rewriteId(id,upperPath2);
    }


    @Override
    public Project getById(long id) {
        Project project = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            TeamDao teamDao = new TeamDao();
            boolean rly=true;
            while ((line = br.readLine()) != null) {
                if (Objects.equals(line.split(",")[0], Long.toString(id))) {
                    rly = false;
                    long ID = Long.valueOf(line.split(",")[0]);
                    String name = line.split(",")[1];
                    String allIdOfTeams = line.split("/")[1];
                    HashSet<Team> teams = new HashSet<>();
                    for(int i =0;i<allIdOfTeams.split(",").length;i++){
                        Team team = teamDao.getById(Long.valueOf(allIdOfTeams.split(",")[i]));
                        teams.add(team);
                    }
                    project = new Project(ID,name,teams);
                }
            }
            if(rly)System.out.println("Проект с ID "+id+" не найден. ");
        } catch (IOException e) {
            System.out.println("Ошибка"+e);
        }
        return project;
    }



    public ArrayList<Project> getAll() {
        ArrayList<Project>AL = new ArrayList<>();
        ArrayList<Long>ids = getIds(path);
        for(long i:ids){
            Project project = getById(i);
            String Projects = "\n"+project.getId() + "," + project.getName() + ".";
            System.out.println(Projects);
            project.getTeams();
            AL.add(project);
        }
        return AL;
    }
}
