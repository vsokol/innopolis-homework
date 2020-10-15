package store.sokolov.innopolis.homework_05.task_01_option_02;

/**
 * Выбрасывается, такое домашнее животное уже есть в хранилище
 *
 * @author Vladimir Sokolov
 */
public class PetDublicateException extends Exception {
    public PetDublicateException(String message) {
        super(message);
    }
}
