package store.sokolov.innopolis.homework_03.task_01;

import java.util.*;

/**
 * Класс MathBox
 *
 * @author Vladimir Sokolov
 */
public class MathBox {
    /** Коллекция для хранения списка чисел */
    private Collection<Number> list = new HashSet<Number>();

    /**
     * Создает внутреннюю коллекцию для хранения списка чисел на основании массива чисел
     * @param number массив чисел, на основании которого формируется внутренняя коллекция
     */
    public MathBox(Number[] number) {
        if (number != null && number.length != 0) {
            Collections.addAll(list, number);
        }
    }

    /**
     * Суммирует все числа сохраненные во внутренней коллекции <b>list<b/>
     * @return сумму чисел сохраненных во внутренней коллекции <b>list<b/>
     */
    public Number summator() {
        if (list == null || list.size() == 0) {
            return 0.0;
        }

        double res = 0.0;
        for (Number item : list) {
            res += item.doubleValue();
        }
        return res;
    }

    /**
     * Делит все числа сохраненные во внутренней коллекции на заданное. Хранящиеся в коллекции данные полностью заменяются результатами деления.
     * @param number число, на которое делятся в элементы внутренней коллекции.
     */
    public void splitter(Number number) {
        if (list == null || list.size() == 0) {
            return;
        }

        Collection<Number> splitList = new HashSet<Number>();
        for (Number item : list) {
            splitList.add(item.doubleValue() / number.doubleValue());
        }
        list = splitList;
    }

    /**
     * Формирует представление объекта в виде строки
     * @return представление объекта в виде строки
     */
    @Override
    public String toString() {
        return "" + list;
    }

    /**
     * Сравнение двух объектов
     * @param o - объект, с которым сравнивается текущий
     * @return <b>True</b> если объекты равны, иначе <b>False</b>
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MathBox)) {
            return false;
        }

        MathBox mb = (MathBox) o;
        Collection<Number> c = mb.list;
        if (c.size() != list.size()) {
            return false;
        }
        try {
            return list.containsAll(c);
        } catch (ClassCastException unused)   {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    /**
     * Формирование Hash кода объекта
     * @return фозвращает hash код текущего объекта
     */
    @Override
    public int hashCode() {
        int h = 0;
        for (Number item : list) {
            if (item != null) {
                h += item.hashCode();
            }
        }
        return h;
    }
}
