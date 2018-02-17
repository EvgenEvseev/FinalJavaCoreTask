package dao;

import model.Developer;
import model.Skill;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class DeveloperDao extends DaoDraft {
    private String path = "./src/developer.txt";
    private String upperPath = "./src/team.txt";

    public void safe(Developer developer) {
            try (FileWriter wr = new FileWriter(path, true)) {
                String skillStr = developer.getId() + "," + developer.getName() + "," + developer.getLastName() +
                        "," + developer.getSpeciality() + "," + developer.getSalary() + "," + "/" + developer.getIds();
                wr.write(skillStr);
                wr.write("\r\n");
                System.out.println("Разработчик успешно добавлен :");
                System.out.println(skillStr);
            } catch (Exception e) { System.out.println(e);
        }
    }


    @Override
    public Developer getById(long id) {
        Developer developer = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            SkillDao sd = new SkillDao();
            boolean rly=true;
            while ((line = br.readLine()) != null) {
                if (Objects.equals(line.split(",")[0], Long.toString(id))) {
                    rly = false;
                    long ID = Long.valueOf(line.split(",")[0]);
                    String firstname = line.split(",")[1];
                    String secondname = line.split(",")[2];
                    String speciality = line.split(",")[3];
                    Double sal = Double.valueOf(line.split(",")[4]);
                    BigDecimal salary = new BigDecimal(sal);
                    String allIdOfSkills = line.split("/")[1];
                    HashSet<Skill>skills= new HashSet<>();
                    for(int i =0;i<allIdOfSkills.split(",").length;i++){
                        Skill skill = sd.getById(Long.valueOf(allIdOfSkills.split(",")[i]));
                        skills.add(skill);
                    }
                    developer = new Developer(ID,firstname,secondname,speciality,skills,salary);
                }
            }
            if(rly)System.out.println("Разработчик с ID "+id+" не найден. ");
        } catch (IOException e) {
            System.out.println("Ошибка"+e);
        }
        return developer;
    }

    public ArrayList<Developer> getAll() {
        ArrayList<Developer>AL = new ArrayList<>();
        ArrayList<Long>ids = getIds(path);
        for(long i:ids){
            Developer developer = getById(i);
            String Developers = "\n"+developer.getId() + "," + developer.getName() + "," + developer.getLastName() +
                    "," + developer.getSpeciality() + "," + developer.getSalary() +".";
            System.out.println(Developers);
            developer.getSkills();
            AL.add(getById(i));
        }
        return AL;
    }

    public void remove(long id){
        remove(id,path);
        rewriteId(id,upperPath);
    }
}
