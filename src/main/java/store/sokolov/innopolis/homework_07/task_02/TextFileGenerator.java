package store.sokolov.innopolis.homework_07.task_02;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 * Генератор текстовых файлов, работающий по следующим правилам:
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
public class TextFileGenerator {
    private static Random rnd = new Random();
    /**
     * минимальная длина слова
     */
    private int minWordLen;
    /**
     * максимальная длина слова
     */
    private int maxWordLen;
    /**
     * минимальное количество слов в массиве
     */
    private int minLenArrayOfWords;
    /**
     * максимальное количество слов в массиве
     */
    private int maxLenArrayOfWords;
    /**
     * минимальное кол-во предложений в параграфе
     */
    private int minCountSentenceInParagraph;
    /**
     * максимальное кол-во предложений в параграфе
     */
    private int maxCountSentenceInParagraph;
    /**
     * минимальное кол-во слов в предложение
     */
    private int minCountWordInSentence;
    /**
     * максимальное кол-во слов в предложение
     */
    private int maxCountWordInSentence;

    /**
     * Конструктор для создания класса
     */
    public TextFileGenerator() {
        minWordLen = 1;
        maxWordLen = 15;
        minLenArrayOfWords = 1;
        maxLenArrayOfWords = 1000;
        minCountSentenceInParagraph = 1;
        maxCountSentenceInParagraph = 20;
        minCountWordInSentence = 1;
        maxCountWordInSentence = 15;
    }

    /**
     * Коструктор создания класса с указанием границ для генерации текста
     *
     * @param minWordLen                  минимальная длина слова
     * @param maxWordLen                  максимальная длина слова
     * @param minLenArrayOfWords          минимальное количество слов в массиве
     * @param maxLenArrayOfWords          максимальное количество слов в массиве
     * @param minCountSentenceInParagraph минимальное кол-во предложений в параграфе
     * @param maxCountSentenceInParagraph максимальное кол-во предложений в параграфе
     * @param minCountWordInSentence      минимальное кол-во слов в предложение
     * @param maxCountWordInSentence      максимальное кол-во слов в предложение
     */
    public TextFileGenerator(Integer minWordLen, Integer maxWordLen
            , Integer minLenArrayOfWords, Integer maxLenArrayOfWords
            , Integer minCountSentenceInParagraph, Integer maxCountSentenceInParagraph
            , Integer minCountWordInSentence, Integer maxCountWordInSentence) {
        this();
        this.minWordLen = minWordLen != null ? minWordLen : this.minWordLen;
        this.maxWordLen = maxWordLen != null ? maxWordLen : this.maxWordLen;
        this.minLenArrayOfWords = minLenArrayOfWords != null ? minLenArrayOfWords : this.minLenArrayOfWords;
        this.maxLenArrayOfWords = maxLenArrayOfWords != null ? maxLenArrayOfWords : this.maxLenArrayOfWords;
        this.minCountSentenceInParagraph = minCountSentenceInParagraph != null ? minCountSentenceInParagraph : this.minCountSentenceInParagraph;
        this.maxCountSentenceInParagraph = maxCountSentenceInParagraph != null ? maxCountSentenceInParagraph : this.maxCountSentenceInParagraph;
        this.minCountWordInSentence = minCountWordInSentence != null ? minCountWordInSentence : this.minCountWordInSentence;
        this.maxCountWordInSentence = maxCountWordInSentence != null ? maxCountWordInSentence : this.maxCountWordInSentence;
        // TODO нужно проверить на допустимость
    }

    /**
     * Создает n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
     *
     * @param path        каталог для созданных файлов
     * @param n           количество файлов, которые необходимо создать
     * @param size        размер создаваемых файлов
     * @param words       массив слов, из которых формируется наполнение файлов
     * @param probability ??? вероятность вхождения одного из слов этого массива в следующее предложение (1/probability)
     * @throws IllegalArgumentException выбрасывается, если неверно заданы входные параметры.
     */
    public void getFiles(String path, int n, int size, String[] words, int probability) throws IllegalArgumentException, IOException {
        if (n <= 0) {
            throw new IllegalArgumentException("Неверное значение параметра n = " + n + ". Количество файлов должно быть > 0.");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("Неверное значение параметра size = " + size + ". Размер файла не может быть меньше или равно 0.");
        }
        for (int i = 0; i < n; i++) {
            getFile(path + "\\test_" + i + ".txt", size, words, probability);
        }
    }

    /**
     * Создает файл размером size в каталоге path. words - массив слов, probability - вероятность.
     *
     * @param fileName    файл, в которой сохраняется сгенерированный текст
     * @param size        размер создаваемых файлов
     * @param words       массив слов, из которых формируется наполнение файлов
     * @param probability ??? вероятность вхождения одного из слов этого массива в следующее предложение (1/probability)
     */
    public void getFile(String fileName, int size, String[] words, int probability) throws IOException {
        String text = getText(words, size);
        // создание файла
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream))) {
            writer.write(text);
            writer.flush();
        }
    }

    /**
     * Возвращает сгенерированный на основании массива строк текст размером size
     *
     * @param words массив строк
     * @param size  размер текста
     * @return сгенерированный текст
     */
    protected String getText(String[] words, int size) {
        StringBuilder text = new StringBuilder();
        while (true) {
            text.append(getNextParagraph(words));
            if (text.length() >= size) {
                break;
            }
        }
        return text.substring(0, size);
    }

    /**
     * Возвращает следующий сгенерированный абзац.
     * Кол-во предложений в одном абзаце случайное и находится в границах от minCountSentenceInParagraph и до maxCountSentenceInParagraph.
     * В конце абзаца стоит разрыв строки и перенос каретки.
     *
     * @param words массив слов, на основании которых генерируется абзац
     * @return сгенерированный абзац
     */
    protected String getNextParagraph(String[] words) {
        int countSentence = generateNumber(minCountSentenceInParagraph, maxCountSentenceInParagraph);
        StringBuilder paragraph = new StringBuilder();
        for (int i = 0; i < countSentence; i++) {
            String sentence = getNextSentence(words);
            paragraph.append(sentence);
        }
        paragraph.append("\r\n");
        return paragraph.toString();
    }

    /**
     * Возвращает следующиее сгенерированное предложение
     * Кол-во слов в предложении случайное в границах от minCountWordInSentence и до maxCountWordInSentence слов.
     * В предложении после произвольных слов могут находиться запятые. Слова разделены одним пробелом.
     * Предложение начинается с заглавной буквы и заканчивается на (.|!|?)+" "
     *
     * @param words массив слов, на основании которых генерируется предложение
     * @return сгенерированное предложение
     */
    protected String getNextSentence(String[] words) {
        int countWord = generateNumber(minCountWordInSentence, maxCountWordInSentence);
        StringBuilder sentence = new StringBuilder();
        for (int i = 0; i < countWord; i++) {
            int idxWord = generateNumber(0, words.length - 1);
            sentence.append(i == 0 ? toUpperCaseFirstSymbol(words[idxWord]) : words[idxWord]);
            if (i != countWord - 1) {
                // не последнее слово в предложение, поставим знак "," с вероятностью 20%
                if (generateNumber(1, 5) == 5) {
                    sentence.append(",");
                }
            } else {
                // последнее слово в предложении, нужно поставить один из знаков окончания (.|!|?)
                sentence.append(getSentenceEndSign());
            }
            sentence.append(" ");
        }
        return sentence.toString();
    }

    /**
     * Возвращает знак окончания предложения полученный случайным образом
     * @return знак окончания предложения
     */
    protected String getSentenceEndSign() {
        String sentenceEndSign;
        switch (generateNumber(1, 3)) {
            case 1:
                sentenceEndSign = "!";
                break;
            case 2:
                sentenceEndSign = "?";
                break;
            default:
                sentenceEndSign = ".";
        }
        return sentenceEndSign;
    }

    /**
     * Возвращает строку, у которой первый символ преобразован к вернему регистру
     *
     * @param word строка, которую необходимо преобразовать
     * @return строку с первым символом приведенным в верхний регистр
     */
    protected String toUpperCaseFirstSymbol(String word) {
        if (word == null || word.isEmpty()) {
            return "";
        }
        if (word.length() == 1) {
            return word.toUpperCase();
        } else {
            return word.substring(0, 1).toUpperCase() + word.substring(1);
        }
    }

    /**
     * Возвращает массив сгенерированных слов. Длина массива от 1 до 1000.
     *
     * @return массив сгенерированных слов
     */
    public String[] getWords() {
        return generateArrayOfWords(minLenArrayOfWords, maxLenArrayOfWords);
    }

    /**
     * Возвращает сгенерированное слово. Длина слова от 1 до 15.
     *
     * @return сгенеророванное слово, состоящее из букв латинского алфавита
     */
    public String getWord() {
        return generateWord(minWordLen, maxWordLen);
    }

    /**
     * Возвращает массив сгенерированных слов.
     * Длина массива определяется случайным образом в пределах от minLenArrayOfWords до maxLenArrayOfWords.
     *
     * @param minLenArrayOfWords минимальная длина массива
     * @param maxLenArrayOfWords максимальныя длина массива
     * @return массив сгенерированных слов
     */
    protected String[] generateArrayOfWords(int minLenArrayOfWords, int maxLenArrayOfWords) {
        int len = generateNumber(minLenArrayOfWords, maxLenArrayOfWords);
        String[] arrayOfWords = new String[len];
        for (int i = 0; i < arrayOfWords.length; i++) {
            arrayOfWords[i] = getWord();
        }
        return arrayOfWords;
    }

    /**
     * Возвращает сгенерированное слово. Длина слова от minLen до maxLen.
     *
     * @param minLen минимальная длина слова
     * @param maxLen максимальная длина слова
     * @return сгенеророванное слово, состоящее из букв латинского алфавита
     */
    protected String generateWord(int minLen, int maxLen) {
        StringBuilder sb = new StringBuilder();
        int len = generateNumber(minLen, maxLen);
        for (int i = 0; i < len; i++) {
            char c = (char) (generateNumber('a', 'z'));
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Возвращает случайное число в пределах от minNumber до maxNumber
     *
     * @param minNumber нижняя граница
     * @param maxNumber верхняя граница
     * @return случайное число в заданных границах
     */
    private int generateNumber(int minNumber, int maxNumber) {
        return rnd.nextInt(maxNumber - minNumber + 1) + minNumber;
    }
}
