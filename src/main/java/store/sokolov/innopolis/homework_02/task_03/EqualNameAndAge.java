package store.sokolov.innopolis.homework_02.task_03;

/**
 * Класс {@code EqualNameAndAge} представляет собой вариант {@code Throwable} исключения.
 * <p>Класс {@code EqualNameAndAge} является <em>checked exceptions</em>.
 * Checked exceptions необходимо объявлять в предложении метода или конструктора {@code throws}, если они могут быть вызваны
 * выполнением метода или конструктора и распространяться за пределы метода или конструктора.
 *
 * @author Vladimir Sokolov
 */
public class EqualNameAndAge extends Exception {
    private Person person;

    /**
     * Создает новое исключение с собщением {@code message} и объектом класса {@link Person}
     * <p>Причина - сравниваются одинаковые объекты класса {@link Person}</p>
     */
    public EqualNameAndAge(String message, Person person) {
        super(message);
        this.person = person;
    }
}
