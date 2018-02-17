package view;

import controller.DeveloperController;
import controller.TeamController;
import model.Developer;
import model.Team;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class TeamView extends ViewDraft {

    DeveloperController dao1 = new DeveloperController();
    TeamController dao2 = new TeamController();
    String path = "./src/team.txt";

    @Override
    synchronized public void start() {
        while(true){
            consoleCommonInfo("команда");
            int n =0;
            if(scan.hasNextInt()){
                n = scan.nextInt();
            }else {
                System.out.println("Некоректный ввод данных, введи число от 1 до 5");
                scan.next();
            }
            switch (n){
                case 1:
                    boolean isAdded = false;
                    String ex;
                    long choise;
                    long id;
                    String name;
                    HashSet<Developer> developers=new HashSet<>();

                    System.out.println("Добавление новой команды...");

                    System.out.println("Введите ID: ");
                    id=scanLong();

                    ArrayList<Long> IDs = dao2.getIds(path);
                    for (long d : IDs) {
                        if (Objects.equals(id, d)) {
                            System.out.println("ID уже занят...");
                            isAdded=true;
                            break;
                        } else {
                            break;
                        }
                    }
                    if(isAdded)break;
                    System.out.println("Введите имя команды:");
                    name = scanString();

                    System.out.println("Теперь из списка разработчиков добавьте в команду нужных, вводя соответствующие ID разработчиков");
                    while(true) {
                        System.out.println("Для завершения выбора введите Exit");
                        dao1.getAll();
                        if (scan.hasNextLong()) {
                            choise = scanLong();
                            Developer developer = dao1.getById(choise);
                            if (developers.add(developer)) {
                                System.out.println("Новый разработчик добавлен.");
                            } else System.out.println("Разработчик был добавлен ранее.");
                        } else {
                            ex = scan.next();
                            if (ex.equalsIgnoreCase("exit")) {
                                System.out.println("Добавление разработчиков завершено.");
                                break;
                            } else continue;
                        }
                    }
                    Team team = new Team(id,name,developers);
                    dao2.safe(team);
                    break;
                case 2:
                    long rem;
                    System.out.println("Для удаления команды введите ID:");
                    rem = scanLong();
                    dao2.remove(rem);
                    break;
                case 3:
                    System.out.println("Для вывода информации о команде введите ID: ");
                    long inf;
                    inf = scanLong();
                    Team team1 = dao2.getById(inf) ;
                    if(team1==null)break;
                    String teamstr = team1.getId() + ","+ team1.getName() + ".";
                    System.out.println(teamstr);
                    team1.getDevelopers();
                    break;
                case 4:
                    dao2.getAll();
                    break;
                case 5:
                    BigConsole.start();
            }
        }
    }
}







