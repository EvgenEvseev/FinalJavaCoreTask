package view;

import controller.ProjectController;
import controller.TeamController;
import model.Project;
import model.Team;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class ProjectView extends ViewDraft {

    TeamController dao1 = new TeamController();
    ProjectController dao2 = new ProjectController();
    String path = "./src/project.txt";

    @Override
    synchronized public void start() {
        while(true){
            consoleCommonInfo("проект");
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
                    HashSet<Team> teams = new HashSet<>();

                    System.out.println("Добавление нового проекта...");

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
                    System.out.println("Введите имя проекта:");
                    name = scanString();
                    System.out.println("Теперь из списка команд добавьте в проект нужные, вводя соответствующие ID команд");

                    while(true) {
                        System.out.println("Для завершения выбора введите Exit");
                        dao1.getAll();
                        if (scan.hasNextLong()) {
                            choise = scanLong();
                            Team team = dao1.getById(choise);
                            if (teams.add(team)) {
                                System.out.println("Новая команда добавлена.");
                            } else System.out.println("Команда была добавлена ранее.");
                        } else {
                            ex = scan.next();
                            if (ex.equalsIgnoreCase("exit")) {
                                System.out.println("Добавление команд завершено.");
                                break;
                            } else continue;
                        }
                    }
                    Project project = new Project(id,name,teams);
                    dao2.safe(project);
                    break;
                case 2:
                    long rem;
                    System.out.println("Для удаления  проекта введите ID:");
                    rem = scanLong();
                    dao2.remove(rem);
                    break;
                case 3:
                    System.out.println("Для вывода информации о проекте введите ID: ");
                    long inf;
                    inf = scanLong();
                    Project project1 = dao2.getById(inf) ;
                    if(project1==null)break;
                    String projectStr = project1.getId() + ","+ project1.getName() + ".";
                    System.out.println(projectStr);
                    project1.getTeams();
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
