package store.sokolov.innopolis.homework_02.task_03;

/**
 * Класс {@code IllegalAge} представляет собой вариант {@code Throwable} исключения.
 * <p>Класс {@code IllegalAge} является <em>checked exceptions</em>.
 * Checked exceptions необходимо объявлять в предложении метода или конструктора {@code throws}, если они могут быть вызваны
 * выполнением метода или конструктора и распространяться за пределы метода или конструктора.
 *
 * @author Vladimir Sokolov
 */
public class IllegalAge extends Exception {
    private int age;

    public int getAge() {
        return age;
    }

    /**
     * Создает новое исключение с собщением {@code message} и возрастом {@code age}
     * <p>Причина - возраст не попадает в отрезок от 0 до 100 лет</p>
     * @param message сообщение
     * @param age возраст
     */
    public IllegalAge(String message, int age) {
        super(message);
        this.age = age;
    }
}
