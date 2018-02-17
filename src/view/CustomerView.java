package view;

import controller.CustomerController;
import controller.ProjectController;
import model.Customer;
import model.Project;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class CustomerView extends ViewDraft {

    ProjectController dao1 = new ProjectController();
    CustomerController dao2 = new CustomerController();
    String path = "./src/customer.txt";

    @Override
    synchronized public void start() {
        while(true){
            consoleCommonInfo("клиент");
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
                    String firstname;
                    String lastname;
                    String address;
                    HashSet<Project> projects = new HashSet<>();

                    System.out.println("Добавление нового клиента...");

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
                    System.out.println("Введите имя клиента:");
                    firstname = scanString();
                    System.out.println("Введите фамилию клиента:");
                    lastname = scanString();
                    System.out.println("Введите адрес клиента:");
                    address = scanString();

                    System.out.println("Теперь из списка проектов присвойте клиенту нужные, вводя соответствующие ID проектов");

                    while(true) {
                        System.out.println("Для завершения выбора введите Exit ");
                        dao1.getAll();
                        if (scan.hasNextLong()) {
                            choise = scanLong();
                            Project project = dao1.getById(choise);
                            if (projects.add(project)) {
                                System.out.println("Новый проект добавлен. ");
                            } else System.out.println("Проект был добавлен ранее. ");
                        } else {
                            ex = scan.next();
                            if (ex.equalsIgnoreCase("exit")) {
                                System.out.println("Добавление проектов завершено. ");
                                break;
                            } else continue;
                        }
                    }

                    Customer customer = new Customer(id,firstname,lastname,address,projects);
                    dao2.safe(customer);
                    break;
                case 2:
                    long rem;
                    System.out.println("Для удаления клиента введите ID:");
                    rem = scanLong();
                    dao2.remove(rem);
                    break;
                case 3:
                    System.out.println("Для вывода информации о  клиенте введите ID: ");
                    long inf;
                    inf = scanLong();
                    Customer customer1 = dao2.getById(inf) ;
                    if(customer1==null)break;
                    String customerStr = customer1.getId() + ","+ customer1.getName() + "," + customer1.getLastName()+
                            customer1.getAddress() + ".";
                    System.out.println(customerStr);
                    customer1.getProjects();
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
