package controller;

import dao.CompanyDao;
import model.Company;
import java.util.ArrayList;

public class CompanyController {
    CompanyDao dao = new CompanyDao();

    public void safe(Company company){
        dao.safe(company);
    }

    public void remove(long id){
        dao.remove(id);
    }

    public Company getById(long id){
        return dao.getById(id);
    }

    public ArrayList<Company> getAll(){
        return dao.getAll();
    }

    public ArrayList<Long> getIds(String path){
        return dao.getIds(path);
    }
}
