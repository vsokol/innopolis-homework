package store.sokolov.innopolis.homework_08.task_01_future;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

/**
 * Класс, управляющий многопоточным вычислением факториалов.
 * Результаты вычисления сохраняются в объекте {@link Future}
 * Распареллеливание осуществляется путем вычисления факториалов для разных чисел. Для одного числа вычисление осуществляется в рамках одного потока.
 *
 *  @author Vladimir Sokolov
 */
public class ManageFactorial {
    /** массив чисел для вычисления факториалов */
    private int[] arrayOfNumber;

    public ManageFactorial(int[] arrayOfNumber) {
        this.arrayOfNumber = arrayOfNumber;
    }

    /**
     * Метод для многопоточного вычисления факториалов
     * @return вычисленные факторилы заданных чисел
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public List<FactorialValue<Integer, BigInteger>> calc() throws InterruptedException, ExecutionException {
        int numberOfThreads = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Collection<FactorialCalc> tasks = new ArrayList<>();
        for (int i = 0; i < arrayOfNumber.length; i++) {
            tasks.add(new FactorialCalc(arrayOfNumber[i]));
        }
        List<Future<FactorialValue<Integer, BigInteger>>> futureList = executorService.invokeAll(tasks);
        List<FactorialValue<Integer, BigInteger>> factorialResult = getResults(futureList);
        executorService.shutdownNow();
        return factorialResult;
    }

    /**
     * Конвертация результатов из списка объектов Future в список FactorialValue
     * @param futureList результаты расчетов
     * @return результаты расчетов
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<FactorialValue<Integer, BigInteger>> getResults(List<Future<FactorialValue<Integer, BigInteger>>> futureList) throws ExecutionException, InterruptedException {
        List<FactorialValue<Integer, BigInteger>> factorialResult = new ArrayList<>();
        if (futureList == null || futureList.size() == 0) {
            return factorialResult;
        }
        for (Future<FactorialValue<Integer, BigInteger>> future : futureList) {
            factorialResult.add(future.get());
        }
        return factorialResult;
    }
}
