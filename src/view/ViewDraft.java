package view;
//Класс заготовка для сущностей View

import java.util.Scanner;

public abstract class ViewDraft {
    Scanner scan = new Scanner(System.in);

    protected void consoleCommonInfo(String suspect){
        System.out.println("\nДобро пожаловать в базу данных сущностей типа "+suspect+". Для выбора действий нажмите 1-5.");
        System.out.println("1) Добавить сущность типа "+suspect+". ");
        System.out.println("2) Удалить сущность типа "+suspect+". ");
        System.out.println("3) Информация о сущности типа "+suspect+" по ID.");
        System.out.println("4) Информация в виде списка о всех сущностях типа "+suspect+".");
        System.out.println("5) Выход в главное меню");
    }

   protected long  scanLong(){
        long n;
        while(true){
            if(scan.hasNextLong()){
                n = scan.nextLong();
                break;
            }else System.out.println("Некорректный ввод данных, введите число...");
            scan.next();
        }
        return n;
    }

    protected String scanString(){
       String n;
       scan.nextLine();
       n=scan.next();
       return n;
    }
    public abstract void start();
}

