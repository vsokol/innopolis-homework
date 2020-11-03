package store.sokolov.innopolis.homework_02.task_03;

import java.util.List;

/**
 * Сортировка пузырьком {@link List}<{@link Person}>
 */
public class PersonsBubbleSort implements CanSortPeople {
    /**
     * Сортировка пузырьком
     * @param list коллекция {@link List}<{@link Person}>, которую необходимо отсортировать
     * @throws EqualNameAndAge выбрасывается, если при сортировке встречаются 2 совпадающих объекта класса {@link Person}
     */
    public void sort(List<Person> list) throws EqualNameAndAge {
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j).compareTo(list.get(j+1)) > 0) {
                    Person tempPerson = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, tempPerson);
                } else if (list.get(j).compareTo(list.get(j+1)) == 0) {
                    Person person = list.get(j);
                    throw new EqualNameAndAge("Имена и возраст совпали {имя - " + person.getName() + ", возраст = " + person.getAge() + "}", person);
                }
            }
        }
    }
}
