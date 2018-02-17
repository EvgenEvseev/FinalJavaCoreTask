package controller;

import dao.SkillDao;
import model.Skill;
import java.util.ArrayList;

public class SkillController {
    SkillDao dao = new SkillDao();

    public void safe(Skill skill){
        dao.safe(skill);
    }

    public void remove(long id){
        dao.remove(id);
    }

    public Skill getById(long id){
        return dao.getById(id);
    }
    public ArrayList<Skill> getAll(){
        return dao.getAll();
    }

    public ArrayList<Long> getIds(String path){
        return dao.getIds(path);
    }
}

