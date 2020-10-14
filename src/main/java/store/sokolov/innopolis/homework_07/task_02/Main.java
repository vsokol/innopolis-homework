package store.sokolov.innopolis.homework_07.task_02;

import java.io.IOException;

/**
 * Задание 2. Создать генератор текстовых файлов, работающий по следующим правилам:
 * - Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных слов могут находиться запятые.
 * - Слово состоит из 1<=n2<=15 латинских букв
 * - Слова разделены одним пробелом
 * - Предложение начинается с заглавной буквы
 * - Предложение заканчивается (.|!|?)+" "
 * - Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений. В конце абзаца стоит разрыв строки и перенос каретки.
 * - Есть массив слов 1<=n4<=1000. Есть вероятность probability вхождения одного из слов этого массива в следующее предложение (1/probability).
 * Необходимо написать метод getFiles(String path, int n, int size, String[] words, int probability),
 * который создаст n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
 * ------------------
 *
 * @author Vladimir Sokolov
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // testGenerateWords();
        String dir;
        if (args == null || args.length == 0) {
            System.out.println("Не задан каталог для файлов");
            dir = System.getProperty("user.dir");
        } else {
            dir = args[0];
        }
        TextFileGenerator tg = new TextFileGenerator();
        String[] words = tg.getWords();
        tg.getFiles(dir, 5, 10000, words, 1);
    }

    private static void testGenerateWords() {
        TextFileGenerator tg = new TextFileGenerator();
        String[] arr = tg.getWords();
        printArray(arr);
    }

    private static <T> void printArray(T[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + " : '" + arr[i] + "'");
        }
    }
}
