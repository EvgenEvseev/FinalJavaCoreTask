package view;

import controller.SkillController;
import model.Skill;
import java.util.ArrayList;
import java.util.Objects;


public class SkillView extends ViewDraft  {
    SkillController dao = new SkillController();
    String path = "./src/skill.txt";

   synchronized public void start() {
        while(true){
            consoleCommonInfo("навык");
            int n =0;
            if(scan.hasNextInt()){
                n = scan.nextInt();
            }else {
                System.out.println("Некоректный ввод данных, введи число от 1 до 5");
                scan.next();
            }

            switch (n) {
                case 1:
                    boolean isAdded = false;
                    long id;
                    String name;
                    System.out.println("Добавление навыка ....\n");
                    System.out.println("Введите ID навыка:");
                    id = scanLong();
                    ArrayList<Long> IDs = dao.getIds(path);
                    System.out.println(IDs);
                    for (long d : IDs) {
                        if (Objects.equals(id, d)){
                            System.out.println("ID уже занят...");
                            isAdded=true;
                            break;
                        } else {
                            break;
                        }
                    }
                    if(isAdded)break;
                    System.out.println("Введите имя навыка:");
                    name=scanString();
                    Skill skill = new Skill(id,name);
                    dao.safe(skill);
                    break;
                case 2:
                    long rem;
                    System.out.println("Для удаления навыка введите ID:");
                    rem = scanLong();
                    dao.remove(rem);
                    break;
                case 3:
                    long inf;
                    System.out.println("Для получения информации о навыке, введите ID:");
                    inf = scanLong();
                    Skill skill1= dao.getById(inf);
                    if(skill1==null)break;
                    System.out.println(skill1.getId()+", "+skill1.getName());
                    break;
                case 4:
                    dao.getAll();
                    break;
                case 5:
                    BigConsole.start();
            }
        }
    }
}