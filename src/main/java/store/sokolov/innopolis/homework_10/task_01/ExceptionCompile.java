package store.sokolov.innopolis.homework_10.task_01;

public class ExceptionCompile extends Exception {
    private String message;
    private Process process;
    public ExceptionCompile(String message, Process process) {
        super(message);
        this.message = message;
        this.process = process;
    }
}
