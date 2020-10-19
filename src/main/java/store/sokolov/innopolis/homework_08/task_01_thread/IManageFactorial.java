package store.sokolov.innopolis.homework_08.task_01_thread;

/**
 * Интерфейс для запуска расчетов факториалов
 */
public interface IManageFactorial {
    void calc();
    void calc(int numberOfThreads);
}
