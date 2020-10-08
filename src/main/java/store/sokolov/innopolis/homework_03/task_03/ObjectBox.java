package store.sokolov.innopolis.homework_03.task_03;

import java.util.*;

/**
 * Класс для хранения коллекции объектов. В коллекции могут содержаться одинаковые элементы.
 *
 * @author Vladimir Sokolov
 */
public class ObjectBox<T> {
    // внутренняя коллекция для хранения объектов
    private List<T> list = new ArrayList<T>();

    /**
     * Добавляет объект в коллекцию
     * @param o объект, который необходимо добавить в коллекцию
     */
    public void addObject(T o) {
        list.add(o);
    }

    /**
     * Удаляет все объекты из коллекции, которые равные объекту, переданному в качестве параметра.
     * @param o - объект, который необходимо удалить из коллекции
     */
    public void deleteObject(T o) {
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

    /**
     * Возвращает true, если в коллекции содержится указанный элемент
     * @param o - элемент, который ищется в коллекции
     * @return true, если в коллекции содержится указанный элемент
     */
    public boolean contains(T o) {
        return list.contains(o);
    }

    /**
     * Возвращает коллекцию
     * @return возвращает коллекцию, в которой храняться элементы
     */
    public List<T> getList() {
        return list;
    }
}
