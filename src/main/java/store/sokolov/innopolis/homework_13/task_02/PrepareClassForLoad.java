package store.sokolov.innopolis.homework_13.task_02;

import java.io.*;

/**
 * Класс для подготовки переданного класса к загрузке.
 * Если путь к компилятору не передан, то он определяется автоматически на основании переменной java.home
 *
 * @author Vladimir Sokolov
 */
public class PrepareClassForLoad {
    /**
     * полный путь к компилятору
     */
    private String javac;
    /**
     * полный путь и имя класса, который будет загружаться
     */
    private String className;

    public PrepareClassForLoad(String className) {
        this(null, className);
    }

    public PrepareClassForLoad(String javac, String className) {
        this.javac = javac != null ? javac : getJavaC();
        this.className = className;
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
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public String getPrepareClass() throws IOException, InterruptedException, ExceptionCompile {
        String code = buildCode(className);
        String fileName = saveCode(code);
        checkFields();
        Process process = Runtime.getRuntime().exec(new String[]{"\"" + javac + "\"", "\"" + fileName + "\""});
        if (process.waitFor() != 0) {
            throw new ExceptionCompile("Ошибка компиляции " + javac + " " + fileName, process);
        }
        String path = fileName.substring(0, fileName.lastIndexOf(getFileSeparator()));
        String compileClass = fileName.substring(fileName.lastIndexOf(getFileSeparator()));
        compileClass = compileClass.substring(0, compileClass.indexOf(".java")) + ".class";
        return path + compileClass;
    }

    /**
     * Сохраняет сформированный код в файле classFile
     *
     * @param code код класса
     * @throws IOException
     */
    public String saveCode(String code) throws IOException {
        String fileName = System.getProperty("java.io.tmpdir") + className /*.substring(0,className.indexOf("_"))*/ + ".java";
        try (FileOutputStream outputStream = new FileOutputStream(fileName);
            BufferedOutputStream output = new BufferedOutputStream(outputStream)) {
            output.write(code.getBytes());
            output.flush();
        }
        return fileName;
    }

    /**
     * Формирует код класса
     *
     * @throws IOException
     */
    public String buildCode(String className) {
        StringBuilder code = new StringBuilder();
        code.append("import java.util.ArrayList;\n");
        code.append("import java.util.List;\n");
        code.append("\n");
        code.append("public class ");
        code.append(className);
        code.append(" {\n");
        code.append("    public static int anInt = 100;\n");
        code.append("    public static long aLong = 6546531265465465456L;\n");
        code.append("    public static String string = \"Мама мыла раму\";\n");
        code.append("    public static List<Integer> list = new ArrayList<>();\n");
        code.append("\n");
        code.append("    static {\n");
        code.append("        for (int i = 0; i < 1000; i++) {\n");
        code.append("            list.add(i);\n");
        code.append("        }\n");
        code.append("    }\n");
        code.append("\n");
        code.append("    public void doWork() {\n");
        code.append("        for (int i = 0; i < list.size(); i++) {\n");
        code.append("            System.out.println(\"list(\" + i + \") = \" + list.get(i));\n");
        code.append("        }\n");
        code.append("    }\n");
        code.append("}");
        return code.toString();
    }
}
