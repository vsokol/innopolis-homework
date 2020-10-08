package store.sokolov.innopolis.homework_03.task_02;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Класс для хранения коллекции объектов. В коллекции могут содержаться одинаковые элементы.
 *
 * @author Vladimir Sokolov
 */
public class ObjectBox {
    // внутренняя коллекция для хранения объектов
    private Collection<Object> list = new ArrayList<Object>();

    /**
     * Добавляет объект в коллекцию
     * @param o объект, который необходимо добавить в коллекцию
     */
    public void addObject(Object o) {
        list.add(o);
    }

    /**
     * Удаляет все объекты из коллекции, которые равные объекту, переданному в качестве параметра.
     * @param o объект, который необходимо удалить из коллекции
     */
    public void deleteObject(Object o) {
        while (list.contains(o)) {
            list.remove(o);
        }
    }

    /**
     * Представляет объект в виде строки.
     * @return строковое представление объекта
     */
    public String dump() {
        return toString();
    }

    /**
     * Представляет объект в виде строки
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "" + list;
    }
}
