package controller;

import dao.TeamDao;
import model.Team;
import java.util.ArrayList;

public class TeamController {
    TeamDao dao = new TeamDao();

    public void safe(Team team){
        dao.safe(team);
    }

    public void remove(long id){
        dao.remove(id);
    }

    public Team getById(long id){
        return dao.getById(id);
    }
    public ArrayList<Team> getAll(){
        return dao.getAll();
    }

    public ArrayList<Long> getIds(String path){
        return dao.getIds(path);
    }
}
