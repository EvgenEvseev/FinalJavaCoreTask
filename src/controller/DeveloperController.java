package controller;

import dao.DeveloperDao;
import model.Developer;
import java.util.ArrayList;

public class DeveloperController {
    DeveloperDao dao = new DeveloperDao();

    public void safe(Developer developer){
        dao.safe(developer);
    }

    public void remove(long id){
        dao.remove(id);
    }

    public Developer getById(long id){
        return dao.getById(id);
    }

    public ArrayList<Developer> getAll(){
        return dao.getAll();
    }

    public ArrayList<Long> getIds(String path){
        return dao.getIds(path);
    }
}
