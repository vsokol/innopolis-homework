package store.sokolov.innopolis.homework_10.task_01;

import java.io.*;

/**
 * Класс для подготовки переданного класса к загрузке.
 * Если путь к компилятору не передан, то он определяется автоматически на основании переменной java.home
 *
 * @author Vladimir Sokolov
 */
public class PrepareClassForLoad implements IPrepareClassForLoad {
    /**
     * полный путь к компилятору
     */
    private String javac;
    /**
     * полный путь и имя класса, который будет загружаться
     */
    private String classFile;

    public PrepareClassForLoad(String classFile) {
        this(null, classFile);
    }

    public PrepareClassForLoad(String javac, String classFile) {
        this.javac = javac != null ? javac : getJavaC();
        this.classFile = classFile;
    }

    /**
     * Возвращает полный путь и название компилятора
     *
     * @return полный путь и название компилятора
     */
    private String getJavaC() {
        String jrePath = System.getProperties().getProperty("java.home");
        String fileSeparator = getFileSeparator();
        // TODO нужно определить систему для определения расширения
        String extension = "exe";
        return jrePath.substring(0, jrePath.lastIndexOf(fileSeparator)) + fileSeparator + "bin" + fileSeparator + "javac." + extension;
    }

    private String getFileSeparator() {
        return System.getProperties().getProperty("file.separator");
    }

    /**
     * Проверяет поля на то, что они указывают на реальные файлы
     *
     * @throws FileNotFoundException выбрасывается, если файл с указанным путем и именем не существует
     */
    protected void checkFields() throws FileNotFoundException {
        checkFile(javac);
        checkFile(classFile);
    }

    /**
     * Проверяет указанное имя файла на существование
     *
     * @param fileName путь и имя файла, которые необходимо проверить
     * @throws FileNotFoundException выбрасывается, если файл с указанным путем и именем не существует
     */
    private void checkFile(String fileName) throws FileNotFoundException {
        if (fileName == null || fileName.isEmpty()) {
            return;
        }
        // проверяем наличие файлф
        File file = new File(fileName);
        if (!file.isFile()) {
            throw new FileNotFoundException("Файл " + fileName + " не найден ");
        }
    }

    /**
     * Возвращает скомпилированный класс
     * @throws IOException
     * @throws InterruptedException
     * @throws ExceptionCompile выбрасывается, если не получилось скомпилировать класс
     */
    @Override
    public String getPrepareClass() throws IOException, InterruptedException, ExceptionCompile {
        String code = buildCode();
        saveCode(code);
        checkFields();
        Process process = Runtime.getRuntime().exec(new String[]{"\"" + javac + "\"", "\"" + classFile + "\""});
        if (process.waitFor() != 0) {
            throw new ExceptionCompile("Ошибка компиляции " + javac + " " + classFile, process);
        }
        String path = classFile.substring(0,classFile.lastIndexOf(getFileSeparator()));
        String compileClass = classFile.substring(classFile.lastIndexOf(getFileSeparator()));
        compileClass = compileClass.substring(0, compileClass.indexOf(".java")) + ".class";
        return path + compileClass;
    }

    /**
     * Сохраняет сформированный код в файле classFile
     * @param code код класса
     * @throws IOException
     */
    public void saveCode(String code) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(classFile);
        BufferedOutputStream output = new BufferedOutputStream(outputStream);
        output.write(code.getBytes());
        output.flush();
        output.close();
        outputStream.close();
    }

    /**
     * Формирует код класса
     * @throws IOException
     */
    public String buildCode() throws IOException {
        StringBuilder code = new StringBuilder();
        code.append("public class SomeClass /*implements Worker*/ {\n");
        code.append("    /*@Override*/\n");
        code.append("    public void doWork() {\n");
        String readCode = readCode();
        code.append(readCode);
        code.append("    }\n");
        code.append("}");
        return code.toString();
    }

    /**
     * Считывает текст метода doWork с консоли
     * @return текст метода
     * @throws IOException выбрасывается при возникновении ошибки ввода-вывода
     */
    public String readCode() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // считываем код метода
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = reader.readLine();
            if (line.isEmpty()) {
                break;
            }
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }
}
