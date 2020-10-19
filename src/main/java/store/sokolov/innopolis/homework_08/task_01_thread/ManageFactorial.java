package store.sokolov.innopolis.homework_08.task_01_thread;

import java.math.BigInteger;
import java.util.*;

/**
 * Класс, управляющий многопоточным вычислением факториалов.
 * Числа, для которых необходимо вычислить факториалы, сохраняются в мапу.
 * Вычисленные значения факториалов также сохраняются в мапу.
 * Количество потоков определяется либо через конструктор, либо значением по умолчанию defaultNumberOfThreads = 1
 * Распареллеливание осуществляется путем вычисления факториалов для разных чисел. Для одного числа вычисление осуществляется в рамках одного потока.
 *
 *  @author Vladimir Sokolov
 */
public class ManageFactorial implements IManageFactorial {
    /** Мапа для хранения чисел для которых вычисляются факториалы и значения вычисленных факториалов. */
    private Map<Integer, ValuePair<Integer, BigInteger>> map = new TreeMap<>();
    /** Количество потоков по умолчанию */
    private int defaultNumberOfThreads = 1;

    /**
     * Конструктор для создания объекта на основании массива чисел, для которых необходимо вычислить факториалы
     * @param values массив значений, для которых необходимо вычислить факториалы
     */
    public ManageFactorial(int[] values) {
        if (values == null || values.length == 0) {
            return;
        }
        for (int value : values) {
            map.put(value, new ValuePair<Integer, BigInteger>(value));
        }
    }

    /**
     * Возвращает мапу с числами и факториалами для этих чисел
     * @return мапа с числами и факториалами для этих чисел
     */
    public Map<Integer, ValuePair<Integer, BigInteger>> getMap() {
        return map;
    }

    /**
     * Запуск вычисления факториалов
     */
    @Override
    public void calc() {
        calc(defaultNumberOfThreads);
    }

    /**
     * Запуск многопоточного вычисления факториалов
     * @param numberOfThreads количество потоков для вычислений
     */
    @Override
    public void calc(int numberOfThreads) {
        int maxNumberOfThreads = Math.min(numberOfThreads, map.size());
        // System.out.println("maxNumberOfThreads = " + maxNumberOfThreads);
        List<FactorialCalc> threadPool = new ArrayList<>();
        numberOfThreads = 0;
        // проходим по всей мапе для вычисления факториала
        for (Map.Entry<Integer, ValuePair<Integer, BigInteger>> item : map.entrySet()) {
            if (numberOfThreads < maxNumberOfThreads) {
                // если есть свободные потоки, запускаем
                FactorialCalc fc = new FactorialCalc(item.getKey());
                threadPool.add(fc);
                numberOfThreads++;
                fc.start();
            } else {
                // нет свободных потоков, ожидаем освобождения хотя бы одного
                while (numberOfThreads >= maxNumberOfThreads) {
                    Iterator<FactorialCalc> iterator = threadPool.iterator();
                    while (iterator.hasNext()) {
                        FactorialCalc fc = iterator.next();
                        if (!fc.isAlive()) {
                            // поток отработал. получим из него данные
                            ValuePair<Integer, BigInteger> valuePair = map.get(fc.getNumber());
                            valuePair.setValue_2(fc.getFactorial());
                            iterator.remove();
                            numberOfThreads--;
                        }
                    }
                }
            }
        }
        // ожидаем окончания всех потоков
        boolean isFinished = false;
        while (!isFinished ) {
            Iterator<FactorialCalc> iterator = threadPool.iterator();
            isFinished = true;
            while (iterator.hasNext()) {
                FactorialCalc fc = iterator.next();
                if (!fc.isAlive()) {
                    ValuePair<Integer, BigInteger> valuePair = map.get(fc.getNumber());
                    valuePair.setValue_2(fc.getFactorial());
                    iterator.remove();
                    numberOfThreads--;
                } else {
                    isFinished = false;
                }
            }
        }
    }

    /**
     * Возвращает представление объекта в виде строки
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "ManageFactorial{" +
                "map=" + map +
                '}';
    }
}
