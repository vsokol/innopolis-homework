package store.sokolov.innopolis.homework_07.task_01;

import java.util.Map;

/**
 * Задание 1. Написать программу, читающую текстовый файл. Программа должна составлять отсортированный по алфавиту список слов,
 * найденных в файле и сохранять его в файл-результат. Найденные слова не должны повторяться, регистр не должен учитываться.
 * Одно слово в разных падежах – это разные слова.
 * ----------
 * Имя файла откуда читаем текстовый файл задается в первом параметра программы
 * Имя файла куда сохраняем слова задается во втором параметре программы
 *
 * @author Vladimir Sokolov
 */
public class Main {
    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            System.out.println("Не заданы файл источник и приемник");
            return;
        }
        // читаем название и путь к файлу источнику, из которого читается текстовая информация
        String fileSource = args[0];
        // читаем название и путь к файлу приемнику, куда мы будем записывать слова
        String fileDestination = args[1];

        Map<String, String> map = ReadAndParseTextFile.getWordsFromFile(fileSource);
        //System.out.println(map.toString());
        SaveTextFile.saveWordsToFile(map, fileDestination);
    }
}
