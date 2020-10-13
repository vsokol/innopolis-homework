package store.sokolov.innopolis.homework_07.task_01;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Класс для чтения текста из файла и разбивки его на слова
 *
 * @author Vladimir Sokolov
 */
public class SaveTextFile {
    /**
     * Сохраняет список слов в файл.
     * @param map - мапа со словами, которые необходимо сохранить в файл
     * @param fileName - файл, в который сохраняются слова
     */
    public static void saveWordsToFile(Map<String, String> map, String fileName) {
        if (map == null || map.size() == 0) {
            return;
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8))) {
            for (String s : map.values()) {
                writer.write(s);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
