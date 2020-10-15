package store.sokolov.innopolis.homework_05.task_01_option_02;

/**
 * Выбрасывается, если указанного домашнего животного нет в хранилище
 *
 * @author Vladimir Sokolov
 */
public class PetNotExists extends Exception {
    public PetNotExists(String message) {
        super(message);
    }
}
