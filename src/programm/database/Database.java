package programm.database;

import programm.utils.Utils;

import java.sql.*;
import java.util.Arrays;
import java.util.Vector;

/**
 * Интерфейс базы данных
 */


public class Database {
    private static Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://192.168.0.2/test",
                    "test",
                    "OUSGfygq03");
            System.out.println("Вы успешно подключились к БД");
        } catch (SQLException e) {
            Utils.showError(null, "Ошибка при подключении к БД");
        }
    }

    /**
     * Получить все поля с базы данных
     * @param filter
     * @return
     */
    public Vector<String[]> getAll(String filter) {
        Vector<String[]> result = new Vector<String[]>();
        if (!filter.equals("")) {
            filter = "Where Title like  '%" + filter + "%'";
        }
        ResultSet res = query("select ID, Title, Cost, DurationInSeconds, Discount from service " + filter);

        try {
            while (res.next()) {
                String[] row = new String[]{
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5)
                };
                result.add(row);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    /**
     * Получить одну запись по id
     * @param id
     * @return
     */
    public String[] getById(String id) {
        String[] ret = new String[0];
        ResultSet resultSet = query("select ID,Title, Cost, DurationInSeconds, Discount  from service where ID = " + id);
        try {
            while (resultSet.next()) {
                ret = new String[]{
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),

                };
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ret;
    }

    /**
     * удалить запись по id
     * @param id
     */
    public void deleteById(String id) {
        sqlExecurte("Delete from service where ID=" + id);
    }

    /**
     * Добавить запись в базуданных
     * @param title
     * @param cost
     * @param durationInSeconds
     * @param discount
     */
    public void insertIntoTable(String title, String cost, String durationInSeconds, String discount) {
        sqlExecurte("INSERT INTO service (Title, Cost, DurationInSeconds, Discount) VALUES (" +
                "'" + title + "'," +
                "'" + cost + "'," +
                "'" + durationInSeconds + "'," +
                "'" + discount + "' ); "
        );
        //INSERT INTO table_name (column1, column2, column3, ...)
        //VALUES ( " +  'value1', 'value2', 'value3');
    }

    /**
     * Обновить данные в таблице
     * @param title
     * @param cost
     * @param durationInSeconds
     * @param discount
     * @param id
     */
    public void updateTable(String title, String cost, String durationInSeconds, String discount, String id) {
        sqlExecurte("UPDATE service SET Title = '" + title
                + "', Cost = '" + cost
                + "',DurationInSeconds = '" + durationInSeconds
                + "',Discount = '" + discount
                + "'WHERE ID = " + id);
//        UPDATE table_name
//        SET column1 = value1, column2 = value2, ...
//        WHERE condition;
    }

    private ResultSet query(String str) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(str);
        } catch (SQLException e) {
            Utils.showError(null, "Ошибка при запросе");
        }
        return null;
    }

    private boolean sqlExecurte(String sql) {
        try {
            return connection.createStatement().execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }



}