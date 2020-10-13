package store.sokolov.innopolis.homework_07.task_01;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для чтения текста из файла и разбивки его на слова
 *
 * @author Vladimir Sokolov
 */
public class ReadAndParseTextFile {

    /**
     * Возвращает разбитый на слова текст, который читает из файла.
     * Если два слова отличатся только регистром, то сохраняется только первое
     * @param fileName - назваеие и путь к файлу из которого читается текст
     * @return мапу со словами. В качестве ключа используется слово в нижнем регистре
     */
    public static Map<String, String> getWordsFromFile(String fileName) {
        Map<String, String> words = new TreeMap<>();
        try ( FileInputStream fileInputStream = new FileInputStream(fileName);
              BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> list = getWordsFromLine(line);
                for (String s : list) {
                    words.put(s.toLowerCase(), s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // throw new IOException(e);
        }
        return words;
    }

    /**
     * Возвращает список слов, которые получаются из строки путем ее разбиение.
     * Учитываются только слова состоящие только из букв.
     * @param line - строка, которая разбиватеся на слова
     * @return список слов, выделенных из строки
     */
    public static List<String> getWordsFromLine(String line) {
        List<String> list = new ArrayList<>();
        if (line == null) {
            return list;
        }
        Pattern pattern = Pattern.compile("[A-Za-zА-Яа-я]+", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            list.add(line.substring(start, end));
        }
        return list;
    }
}
