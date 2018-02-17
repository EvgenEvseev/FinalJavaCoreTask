package dao;

import model.Company;
import model.Project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class CompanyDao extends DaoDraft {
    private String path = "./src/company.txt";

    public void safe(Company company){ safe(company,path); }

    public void remove(long id){ remove(id,path);}

    @Override
    public Company getById(long id) {
        Company company = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            ProjectDao projectDao = new ProjectDao();
            boolean rly=true;
            while ((line = br.readLine()) != null) {
                if (Objects.equals(line.split(",")[0], Long.toString(id))) {
                    rly = false;
                    long ID = Long.valueOf(line.split(",")[0]);
                    String name = line.split(",")[1];
                    String allIdOfProjects = line.split("/")[1];
                    HashSet<Project> projects = new HashSet<>();
                    for(int i =0;i<allIdOfProjects.split(",").length;i++){
                        Project project = projectDao.getById(Long.valueOf(allIdOfProjects.split(",")[i]));
                        projects.add(project);
                    }
                    company = new Company(ID,name,projects);
                }
            }
            if(rly)System.out.println("Компания с ID "+id+" не найдена. ");
        } catch (IOException e) {
            System.out.println("Ошибка"+e);
        }
        return company;
    }



    public ArrayList<Company> getAll() {
        ArrayList<Company>AL = new ArrayList<>();
        ArrayList<Long>ids = getIds(path);
        for(long i:ids){
            Company company = getById(i);
            String Projects = "\n"+company.getId() + "," + company.getName() + ".";
            System.out.println(Projects);
            company.getProjects();
            AL.add(company);
        }
        return AL;
    }
}
