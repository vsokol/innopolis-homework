package store.sokolov.innopolis.homework_02.task_03;

import java.util.List;

/**
 * Сортировка выбором {@link List}<{@link Person}>
 */
public class PersonsSelectionSort implements CanSortPeople {
    /**
     * Сортировка выбором
     *
     * @param list коллекция {@link List}<{@link Person}>, которую необходимо отсортировать
     * @throws EqualNameAndAge выбрасывается, если при сортировке встречаются 2 совпадающих объекта класса {@link Person}
     */
    @Override
    public void sort(List<Person> list) throws EqualNameAndAge {
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            int min = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(min).compareTo(list.get(j)) > 0) {
                    min = j;
                } else if (list.get(min).compareTo(list.get(j)) == 0) {
                    Person person = list.get(j);
                    throw new EqualNameAndAge("Имена и возраст совпали {имя - " + person.getName() + ", возраст = " + person.getAge() + "}", person);
                }
            }
            if (min != i) {
                Person tempPerson = list.get(min);
                list.set(min, list.get(i));
                list.set(i, tempPerson);
            }
        }
    }
}
