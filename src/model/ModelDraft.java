package model;

public interface ModelDraft {
     //Получение ID
     long getId();
     //Получение имени
     String getName();

     //Метод нужный для логики сохранения в текстовый файл. Извлекает ID-шники сущностей ниже
     String getIds();

}
