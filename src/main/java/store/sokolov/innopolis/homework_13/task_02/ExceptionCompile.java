package store.sokolov.innopolis.homework_13.task_02;

public class ExceptionCompile extends Exception {
    private String message;
    private Process process;
    public ExceptionCompile(String message, Process process) {
        super(message);
        this.message = message;
        this.process = process;
    }
}
