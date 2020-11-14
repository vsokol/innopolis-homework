package store.sokolov.innopolis.homework_19.task_1_2;

import java.io.IOException;
import java.sql.*;

/**
 * Описание задачи - tasks.md
 * Инициализация БД. Запросы читаются из каталога указанного в первом параметре программы и выполняются на бд.
 */
public class Main {
    public static DBInit db;

    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("Домашнее задание 19");
        db = new DBInit("jdbc:postgresql://localhost:5432/online_shop?user=shop&password=shop", args[0]);
        System.out.println("-- Сброс и инициализация базы данных --");
        db.executeSQLs();
        task1();
        task2();
        task3();
        task4();
        task5();
    }

    /**
     * Применить параметризованный запрос
     */
    public static void task1() throws SQLException {
        System.out.println("-- Применить параметризованный запрос --");
        String sql = "select " +
                "  id" +
                ", type_id" +
                ", checklist_id" +
                ", name" +
                ", is_required" +
                ", descr" +
                " from checklist_item" +
                " where checklist_id = ?";
        PreparedStatement statement = db.getConnection().prepareStatement(sql);
        statement.setLong(1, 1001);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.print("id = " + resultSet.getLong("id") + "\t");
            System.out.print("type_id = " + resultSet.getLong("type_id") + "\t");
            System.out.print("checklist_id = " + resultSet.getLong("checklist_id") + "\t");
            System.out.print("name = " + resultSet.getString("name") + "\t");
            System.out.print("is_required = " + resultSet.getBoolean("is_required") + "\t");
            System.out.print("descr = " + resultSet.getString("descr") + "\t");
            System.out.println();
        }
    }

    /**
     * Применить батчинг
     */
    public static void task2() throws SQLException {
        System.out.println("-- Применить батчинг --");
        Connection connection = db.getConnection();
        String sql = "insert into checked_object (parent_id, name, descr) values (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 1; i <= 10; i++) {
            statement.setLong(1, 1004);
            statement.setString(2, "Отдел №" + i);
            statement.setString(3, "Описание отдела №" + i);
            statement.addBatch();
        }
        statement.executeBatch();
        statement.close();

        printCheckedObject(connection);
    }

    /**
     * Использовать ручное управление транзакциями
     */
    public static void task3() throws SQLException {
        System.out.println("-- Использовать ручное управление транзакциями --");
        Connection connection = db.getConnection();
        connection.setAutoCommit(false);
        String sql = "update checked_object" +
                " set name = ?" +
                " where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "Отдел №111");
        statement.setLong(2, 10000);
        statement.executeUpdate();
        connection.rollback();

        statement = connection.prepareStatement(sql);
        statement.setString(1, "Отдел №111-111");
        statement.setLong(2, 10000);
        statement.executeUpdate();
        connection.commit();

        printCheckedObject(connection);
    }

    /**
     * - Предусмотреть использование savepoint при выполнении логики из нескольких запросов
     */
    public static void task4() throws SQLException {
        System.out.println("-- Предусмотреть использование savepoint при выполнении логики из нескольких запросов --");
        Connection connection = db.getConnection();
        connection.setAutoCommit(false);
        Savepoint sp1 = connection.setSavepoint();

        String sql = "update checked_object" +
                " set name = ?" +
                " where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "Отдел №6-6-6");
        statement.setLong(2, 10005);
        statement.executeUpdate();
        Savepoint sp2 = connection.setSavepoint();

        statement = connection.prepareStatement(sql);
        statement.setString(1, "Отдел №666");
        statement.setLong(2, 10005);
        statement.executeUpdate();
        Savepoint sp3 = connection.setSavepoint();

        connection.rollback(sp2);
        connection.commit();

        printCheckedObject(connection);
    }

    /**
     * Предусмотреть rollback операций при ошибках
     */
    public static void task5() throws SQLException {
        System.out.println("-- Предусмотреть rollback операций при ошибках --");
        Connection connection = db.getConnection();
        try {
            connection.setAutoCommit(false);

            String sql = "delete from checked_object" +
                    " where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, 10007);
            statement.execute();

            sql = "delete from checked_object" +
                    " where id1 = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, 10005);
            statement.execute();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            printCheckedObject(connection);
        }
    }

    /**
     * Вывод в консоль выборки из таблицы checked_object
     * @param connection соединение с бд
     * @throws SQLException
     */
    public static void printCheckedObject(Connection connection) throws SQLException {
        Statement statementSQL = connection.createStatement();
        String sql = "select id, parent_id, name, descr" +
                " from checked_object";

        ResultSet resultSet = statementSQL.executeQuery(sql);
        while (resultSet.next()) {
            System.out.print("id = " + resultSet.getLong("id") + "\t");
            System.out.print("parent_id = " + resultSet.getLong("parent_id") + "\t");
            System.out.print("name = " + resultSet.getString("name") + "\t");
            System.out.print("descr = " + resultSet.getString("descr") + "\t");
            System.out.println();
        }
    }
}
