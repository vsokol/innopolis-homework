package store.sokolov.innopolis.homework_08.task_01_option_3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Класс, управляющий многопоточным вычислением факториалов.
 * Числа, для которых необходимо вычислить факториалы сохраняются в очередь, откуда их будут извлекать потоки вычисления
 * Вычисленные значения факториалов сохраняются в мапу. *
 * Распареллеливание осуществляется путем вычисления факториалов для разных чисел. Для одного числа вычисление осуществляется в рамках одного потока.
 *
 *  @author Vladimir Sokolov
 */
public class ManageFactorial {
    /** очередь чисел на вычисление факториала */
    private final ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
    /** Кэш вычисленных факториалов*/
    private final ConcurrentHashMap<Integer, BigInteger> cash;

    /**
     * Конструктор класса. На основании массива заполняет очередь вычисления.
     * @param numbers массив чисел, для которых нужно вычислить факториалы
     */
    public ManageFactorial(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            cash = null;
            return;
        }
        for (int i = 0; i < numbers.length; i++) {
            queue.offer(numbers[i]);
        }
        cash = new ConcurrentHashMap<>(numbers.length);
    }

    public Map<Integer, BigInteger> calc() {
        int numberOfThreads = 4;
        return calc(numberOfThreads);
    }

    /**
     * Запускает потоки вычислений факторилов. Ожидает окончания обработки и возвращает резутьтат вычисления.
     * @param numberOfThreads количество потоков обработки
     * @return мапу, где в качестве ключа число, для которого вычислен факториал, а значение - это сам факториал
     */
    public Map<Integer, BigInteger> calc(int numberOfThreads) {
        int maxNumberOfThreads = Math.min(numberOfThreads, queue.size());
        List<FactorialCalc> threads = new ArrayList<>();
        // запускаем все потоки
        for (int i = 0; i < maxNumberOfThreads; i++) {
            FactorialCalc fc = new FactorialCalc(queue, cash);
            threads.add(fc);
            fc.start();
        }
        // ждем пока все потоки отработают
        while (true) {
            boolean isComlete = true;
            for (int i = 0; i < threads.size(); i++) {
                if (threads.get(i).isAlive()) {
                    // поток еще не завершил работу
                    isComlete = false;
                    break;
                }
            }
            if (isComlete) {
                break;
            }
        }
        return getResult();
    }

    /**
     * Переводит мапу с кешом вычисленных факториалов в мапу для возврата результатов вычислений
     * @return мапу, где в качестве ключа число, для которого вычислен факториал, а значение - это сам факториал
     */
    private Map<Integer, BigInteger> getResult() {
        Map<Integer, BigInteger> result = new HashMap<>();
        for (Map.Entry<Integer, BigInteger> item : cash.entrySet()) {
            result.put(new Integer(item.getKey()), new BigInteger(String.valueOf(item.getValue())));
        }
        return result;
    }
}
