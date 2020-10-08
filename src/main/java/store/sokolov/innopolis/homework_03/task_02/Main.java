package store.sokolov.innopolis.homework_03.task_02;

/**
 * Задание 2. Создать класс ObjectBox, который будет хранить коллекцию Object.
 * У класса должен быть метод addObject, добавляющий объект в коллекцию.
 * У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
 * Должен быть метод dump, выводящий содержимое коллекции в строку.
 * ----------
 *
 * @author Vladimir Sokolov
 */
public class Main {
    public static void main(String[] args) {
        ObjectBox box = new ObjectBox();
        // пустой
        System.out.println(box.dump());
        // наполнение
        Object o = new Object();
        box.addObject(o);
        box.addObject(new Object());
        box.addObject(new Object());
        Integer i = new Integer(5);
        box.addObject(i);
        box.addObject(new Integer(5));
        box.addObject(new Integer(10));
        String s1 = new String("123 321");
        box.addObject(s1);
        String s2 = "000";
        box.addObject(s2);
        System.out.println(box.dump());
        // 2 раза удаление одно и того же объекта
        box.deleteObject(o);
        System.out.println(box.dump());
        box.deleteObject(o);
        System.out.println(box.dump());
        // удаление Integer через ссылку
        box.deleteObject(i);
        System.out.println(box.dump());
        // удаление integer
        box.deleteObject(10);
        System.out.println(box.dump());
        // удаление строки через ссылку на нее
        box.deleteObject(s1);
        System.out.println(box.dump());
        // удаление строки
        box.deleteObject("000");
        System.out.println(box.dump());
    }
}
