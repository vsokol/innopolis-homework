package store.sokolov.innopolis.homework_03.task_03;

import java.util.*;

/**
 * Класс MathBox
 *
 * @author Vladimir Sokolov
 */
public class MathBox<T extends Number> extends ObjectBox {
    /**
     * Создает объект для хранения списка чисел на основании массива чисел
     *
     * @param numbers массив чисел, на основании которого формируется объект для хранения
     */
    public MathBox(Number[] numbers) {
        super();
        if (numbers != null && numbers.length != 0) {
            for (int i = 0; i < numbers.length; i++) {
                if (!contains(numbers[i])) {
                    addObject(numbers[i]);
                }
            }
        }
    }

    /**
     * Суммирует все числа в коллекции
     *
     * @return сумму чисел сохраненных в коллекции
     */
    public Number summator() {
        ListIterator<T> iterator = getList().listIterator();
        double res = 0.0;
        while (iterator.hasNext()) {
            Number item = iterator.next();
            if (item != null) {
                res += item.doubleValue();
            }
        }
        return res;
    }

    /**
     * Делит все числа в коллекции на заданное. Хранящиеся в коллекции данные полностью заменяются результатами деления.
     *
     * @param number число, на которое делятся все элементы.
     */
    public void splitter(Number number) {
        ListIterator<Number> iterator = getList().listIterator();
        while (iterator.hasNext()) {
            Number item = iterator.next();
            if (item != null) {
                iterator.set(item.doubleValue() / number.doubleValue());
            }
        }
    }

    /**
     * Формирование Hash кода объекта
     *
     * @return hash код текущего объекта
     */
    @Override
    public int hashCode() {
        ListIterator<Number> iterator = getList().listIterator();
        int h = 0;
        while (iterator.hasNext()) {
            Number item = iterator.next();
            if (item != null) {
                h += item.hashCode();
            }
        }
        return h;
    }

    /**
     * Сравнение двух объектов
     *
     * @param o - объект, с которым сравнивается текущий
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MathBox)) {
            return false;
        }

        MathBox<T> mb = (MathBox<T>) o;
        if (mb.getList().size() != getList().size()) {
            return false;
        }
        try {
            return getList().containsAll(mb.getList());
        } catch (ClassCastException unused) {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }
}
