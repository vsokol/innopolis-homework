package store.sokolov.innopolis.homework_08.task_01_future;

/**
 * Класс для хранения значения факториала
 * @param <T1> тип числа
 * @param <T2> тип значения факториала
 *
 * @author Vladimir Sokolov
 */
public class FactorialValue<T1, T2> {
    /** число */
    private T1 number;
    /** факториал числа */
    private T2 factorial;

    /**
     * Конструктор для создания объекта
     * @param number число
     * @param factorial факториал
     */
    public FactorialValue(T1 number, T2 factorial) {
        this.number = number;
        this.factorial = factorial;
    }

    /**
     * Возвращает число
     * @return число
     */
    public T1 getNumber() {
        return number;
    }

    /**
     * Возаращает значение факториала
     * @return значение факториала
     */
    public T2 getFactorial() {
        return factorial;
    }

    /**
     * Представление объекта в виде строки
     * @return текстовое предтавление объекта
     */
    @Override
    public String toString() {
        return "FactorialValue{" +
                "число=" + number +
                ", факториал=" + factorial +
                '}';
    }
}
