package controller;

import dao.ProjectDao;
import model.Project;
import java.util.ArrayList;

public class ProjectController {

    ProjectDao dao = new ProjectDao();

    public void safe(Project project){
        dao.safe(project);
    }

    public void remove(long id){
        dao.remove(id);
    }

    public Project getById(long id){
        return dao.getById(id);
    }

    public ArrayList<Project> getAll(){
        return dao.getAll();
    }

    public ArrayList<Long> getIds(String path){
        return dao.getIds(path);
    }
}
