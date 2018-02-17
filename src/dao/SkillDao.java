package dao;

import model.Skill;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class SkillDao extends DaoDraft {
    private String path = "./src/skill.txt";
    private String upperPath = "./src/developer.txt";

    public void safe(Skill skill) {
        try (FileWriter wr = new FileWriter(path, true)) {
            String skillStr = skill.getId() + "," + skill.getName();
            wr.write(skillStr);
            wr.write("\r\n");
            System.out.println("Навык успешно добавлен:");
            System.out.println(skillStr);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public Skill getById(long id) {
        Skill skill = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            boolean rly = true;
            while ((line = br.readLine()) != null) {
                if (Objects.equals(line.split(",")[0], Long.toString(id))) {
                    rly = false;
                    long ID = Long.valueOf(line.split(",")[0]);
                    String name = line.split(",")[1];
                    skill = new Skill(ID, name);
                }
            }
            if (rly) System.out.println("Навык с ID " + id + " не найдена. ");
        } catch (IOException e) {
            System.out.println("Ошибка" + e);
        }
        return skill;
    }


    public ArrayList<Skill> getAll() {
        ArrayList<Skill> AL = new ArrayList<>();
        ArrayList<Long> ids = getIds(path);
        for (long i : ids) {
            Skill skill = getById(i);
            System.out.println(skill.getId()+", "+skill.getName());
            AL.add(skill);
        }
        return AL;
    }

    public void remove(long id){
        remove(id,path);
        rewriteId(id,upperPath);
    }
}

