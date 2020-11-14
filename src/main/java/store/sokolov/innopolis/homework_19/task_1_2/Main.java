package store.sokolov.innopolis.homework_19.task_1_2;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Описание задачи - tasks.md
 * Инициализация БД. Запросы читаются из каталога указанного в первом параметре программы и выполняются на бд.
 */
public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("Домашнее задание 19");
        DBInit db = new DBInit("jdbc:postgresql://localhost:5432/online_shop?user=shop&password=shop", args[0]);
        db.executeSQLs();
        System.out.println("Выполнено");
    }
}
