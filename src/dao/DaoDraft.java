package dao;

// класс заготовка для сущностей Dao


import model.ModelDraft;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

abstract class DaoDraft {

    // Абстрактные методы для наследников
    abstract ModelDraft getById(long id);

    // Общий для наследников метод удаления сущностей
    protected void remove(long id, String path) {
        ArrayList<String> AL = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            boolean ex = true;
            String line;
            while ((line = br.readLine()) != null) {
                if (Long.valueOf(line.split(",")[0]) != id) {
                    AL.add(line);
                } else {
                    System.out.println("Сущность с ID " + id + " успешно удалена");
                    ex = false;
                }
            }
            if (ex) System.out.println("Сущность с ID " + id + " не найдена");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
                for (String aAL : AL) {
                    bw.write(aAL);
                    bw.write("\r\n");
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //метод презаписывающий ID удаленных сущностей в вышестоящей сущности
    public void rewriteId(long id,String upperPath){
        try(BufferedReader br = new BufferedReader(new FileReader(upperPath))){
            String line;
            while ((line = br.readLine())!=null){
                String info = line.split("/")[0];
                String Ids = line.split("/")[1];
                String [] Idsmass = Ids.split(",");
                String IdsNew="";
                for(String n:Idsmass) {
                    if(Long.valueOf(n)!=id){
                        IdsNew+=(n+",");
                }
                line = info+"/"+IdsNew;
                        try(BufferedWriter bw = new BufferedWriter(new FileWriter(upperPath))){
                            bw.write(line);
                            bw.write("\r\n");
                        }
                    }
                }
            }catch (Exception e){
            System.out.println(e);
        }
    }


    // метод используемый для получения ID сущностей, используется в логике приложения для  метода извлечения всех сущностей
    public ArrayList<Long> getIds(String path) {
        String line;
        ArrayList<Long> ids = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                long h = Long.valueOf(line.split(",")[0]);
                ids.add(h);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return ids;
    }


    // Метод сохранения для сущностей Team, Project, Company

    protected void safe(ModelDraft modelDraft, String path) {
                try (FileWriter wr = new FileWriter(path, true)) {
                    String Str = modelDraft.getId() + "," + modelDraft.getName() + "," + "/" + modelDraft.getIds();
                    wr.write(Str);
                    wr.write("\r\n");
                    System.out.println("Сущность успешно добавлена :");
                    System.out.println(Str);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

