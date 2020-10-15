package store.sokolov.innopolis.homework_07.task_03_bonus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * напишите простую программу, которая читает файл в кодировке UTF-8, а пишет в другой файл в кодировке UTF-16.
 * Использовать InputStreamReader/OutputStreamWriter
 * -----
 * @author Vladimir Sokolov
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FileConverter fileConverter = new FileConverter(args[0], args[1]);
        fileConverter.convert();
    }
}
