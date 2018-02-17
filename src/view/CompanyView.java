package view;

import controller.CompanyController;
import controller.ProjectController;
import model.Company;
import model.Project;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class CompanyView extends ViewDraft {

    ProjectController dao1 = new ProjectController();
    CompanyController dao2 = new CompanyController();
    String path = "./src/company.txt";

    @Override
    synchronized public void start() {
        while(true){
            consoleCommonInfo("компания");
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
                    HashSet<Project> projects = new HashSet<>();

                    System.out.println("Добавление новой компании...");

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
                    System.out.println("Введите имя компании:");
                    name = scanString();
                    System.out.println("Теперь из списка проектов присвойте компании нужные, вводя соответствующие ID проектов");

                    while(true) {
                        System.out.println("Для завершения выбора введите Exit");
                        dao1.getAll();
                        if (scan.hasNextLong()) {
                            choise = scanLong();
                            Project project = dao1.getById(choise);
                            if (projects.add(project)) {
                                System.out.println("Новый проект добавлен.");
                            } else System.out.println("Проект был добавлен ранее.");
                        } else {
                            ex = scan.next();
                            if (ex.equalsIgnoreCase("exit")) {
                                System.out.println("Добавление проектов завершено.");
                                break;
                            } else continue;
                        }
                    }
                    Company company = new Company(id,name,projects);
                    dao2.safe(company);
                    break;
                case 2:
                    long rem;
                    System.out.println("Для удаления компании введите ID:");
                    rem = scanLong();
                    dao2.remove(rem);
                    break;
                case 3:
                    System.out.println("Для вывода информации о компании введите ID: ");
                    long inf;
                    inf = scanLong();
                    Company company1 = dao2.getById(inf) ;
                    if(company1==null)break;
                    String companyStr = company1.getId() + ","+ company1.getName() + ".";
                    System.out.println(companyStr);
                    company1.getProjects();
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
