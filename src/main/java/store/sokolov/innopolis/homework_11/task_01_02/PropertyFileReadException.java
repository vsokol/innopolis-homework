package store.sokolov.innopolis.homework_11.task_01_02;

/**
 * Класс ислючение. Будет выбрасываться при возникновении ошибок при чтении конфигурационного файла
 */
public class PropertyFileReadException extends Exception {
    public PropertyFileReadException(String message, Exception exception) {
        super(message, exception);
    }
}
