package store.sokolov.innopolis.homework_02.task_01;

/**
 * Задание 1. Написать программу ”Hello, World!”. В ходе выполнения программы она должна выбросить исключение и завершиться с ошибкой.
 *
 *   1. Смоделировав ошибку «NullPointerException»
 *   2. Смоделировав ошибку «ArrayIndexOutOfBoundsException»
 *   3. Вызвав свой вариант ошибки через оператор throw
 *
 * Используется входной параметр. Возможные значения:
 *   - 1 - моделирование ошибки «NullPointerException»
 *   - 2 - моделирование ошибки «ArrayIndexOutOfBoundsException»
 *   - 3 - вызов собстенного исключения MyException
 * @author  Vladimir Sokolov
 */

public class Main {
    public static void main(String[] args) throws MyException {
        System.out.println("Hello, World!");

        if ("1".equals(args[0])) {
            // NullPointerException
            Object o = null;
            System.out.println(o.toString());
        } else if ("2".equals(args[0])) {
            // ArrayIndexOutOfBoundsException
            int[] arr = new int[2];
            System.out.println(arr[Integer.parseInt(args[0])]);
        } else if ("3".equals(args[0])) {
            // MyError
            throw new MyException("My Exception");
        }
    }
}