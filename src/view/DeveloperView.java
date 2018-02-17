package view;

import controller.DeveloperController;
import controller.SkillController;
import model.Developer;
import model.Skill;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class DeveloperView extends ViewDraft{
        SkillController dao1 = new SkillController();
        DeveloperController dao2 = new DeveloperController();
        String path = "./src/developer.txt";

    @Override
    synchronized public void start() {
        while(true){
            consoleCommonInfo("разработчик");
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
                        String firstName;
                        String lastName;
                        String speciality;
                        HashSet<Skill> skills = new HashSet<>();
                        BigDecimal salary;
                        System.out.println("Добавление нового разработчика...");
                        System.out.println("Введите ID: ");
                        id = scanLong();

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

                                System.out.println("Введите имя разработчика:");
                                firstName = scanString();

                                System.out.println("Введите фамилию разработчика: ");
                                lastName = scanString();

                                System.out.println("Введите специальность разработчика: ");
                                speciality = scanString();

                                System.out.println("Введите зарплату разработчика: ");
                                while (true) {
                                    if (scan.hasNextBigDecimal()) {
                                        salary = scan.nextBigDecimal();
                                        break;
                                    } else System.out.println("Некорректный ввод данных, введите число...");
                                    scan.next();
                                }
                                System.out.println("Теперь из списка навыков присвойте разработчику нужные, вводя соответствующие ID навыков");

                                while (true) {
                                    System.out.println("Для завершения выбора введите Exit");
                                    dao1.getAll();
                                    if (scan.hasNextLong()) {
                                        choise = scanLong();
                                        Skill skill = dao1.getById(choise);
                                        if (skills.add(skill)) {
                                            System.out.println("Новый навык присвоен.");
                                        } else System.out.println("Навык был присвоен ранее.");
                                    } else {
                                        ex = scan.next();
                                        if (ex.equalsIgnoreCase("exit")) {
                                            System.out.println("Присвоение навыков завершено.");
                                            break;
                                        } else continue;
                                    }
                                }
                                Developer dev = new Developer(id, firstName, lastName, speciality, skills, salary);
                                dao2.safe(dev);
                                break;

                case 2:
                    long rem;
                    System.out.println("Для удаления разработчика введите ID:");
                    rem = scanLong();
                    dao2.remove(rem);
                    break;
                case 3:
                    System.out.println("Для вывода информации о разработчике введите ID: ");
                    long inf;
                    inf = scanLong();
                    Developer developer = dao2.getById(inf);
                    if(developer==null)break;
                    String skillStr = "\n"+developer.getId() + "," + developer.getName() + "," + developer.getLastName() +
                            "," + developer.getSpeciality() + "," + developer.getSalary() +".";
                    System.out.println(skillStr);
                    developer.getSkills();
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
