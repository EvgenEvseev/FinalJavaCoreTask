package view;

import java.util.Scanner;

public class BigConsole {


    synchronized static public void start(){

        SkillView skillView = new SkillView();
        DeveloperView developerView = new DeveloperView();
        TeamView teamView = new TeamView();
        ProjectView projectView = new ProjectView();
        CompanyView companyView = new CompanyView();
        CustomerView customerView = new CustomerView();

        while(true) {

            Scanner scan = new Scanner(System.in);
            System.out.println("Добро пожаловать в главное меню. Выберите раздел для работы с помощью 1-7");
            System.out.println("1)Раздел навыков (Skill)");
            System.out.println("2)Раздел разработчиков(Developer)");
            System.out.println("3)Раздел команд (Team)");
            System.out.println("4)Раздел проектов(Project)");
            System.out.println("5)Раздел кампаний(Company)");
            System.out.println("6)Раздел клиентов(Customer)");
            System.out.println("7)Выход из программы");

            int n =0;
            if(scan.hasNextInt()){
                n = scan.nextInt();
            }else {
                System.out.println("Некоректный ввод данных, введи число от 1 до 5");
                scan.next();
            }
            switch (n){
                case 1:
                    skillView.start();
                case 2:
                    developerView.start();
                case 3:
                    teamView.start();
                case 4:
                    projectView.start();
                case 5:
                    companyView.start();
                case 6:
                    customerView.start();
                case 7:
                    System.out.println("Выход");
                    System.exit(0);
            }
        }
    }
}
