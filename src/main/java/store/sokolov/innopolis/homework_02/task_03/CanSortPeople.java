package store.sokolov.innopolis.homework_02.task_03;

import java.util.List;

/**
 * Интерфейс сортировки коллекции {@link List}<{@link Person}>
 */
public interface CanSortPeople {
    /**
     * Сортирует людей
     * @param list коллекция {@link List}<{@link Person}>, которую необходимо отсортировать
     */
    void sort(List<Person> list) throws EqualNameAndAge;
}
