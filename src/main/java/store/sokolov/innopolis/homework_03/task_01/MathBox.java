package store.sokolov.innopolis.homework_03.task_01;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class MathBox <Number> {
    private Collection<Number> list = new HashSet<Number>();

    public MathBox(Number[] number) {
        if (number != null && number.length != 0) {
            Collections.addAll(list, number);
        }
    }

//    public T summator() {
//        if (list == null || list.size() == 0) {
//            return (T) 0;
//        }
//        T res = 0;
//        for (T item : list) {
//            res = res + item;
//        }
//        return (T) res;
//    }

    @Override
    public String toString() {
        return "" + list;
    }
}
