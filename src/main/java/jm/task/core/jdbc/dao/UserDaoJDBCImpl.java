package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    private Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }
    public void createUsersTable() throws SQLException {

        String sqlCT = "CREATE TABLE IF NOT EXISTS user" +
                "(id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50)," +
                "lastName VARCHAR(50)," +
                "age INT)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlCT) ) {
            preparedStatement.executeUpdate();
            System.out.println("создал таблицу");
        }
    }

    public void dropUsersTable() throws SQLException {

        String dropUT = "DROP TABLE IF EXISTS user";
        try(PreparedStatement preparedStatement = connection.prepareStatement(dropUT) ) {

            preparedStatement.executeUpdate();
            System.out.println("Удалил таблицу");

        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        connection.setAutoCommit(false);
        String sqlADD = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlADD)) {

            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User с именем " +name+ " добавлен в таблицу");
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void removeUserById(long id) throws SQLException {
        connection.setAutoCommit(false);
        String sqlRId = "DELETE FROM user WHERE id = ?" ;

        User user = new User();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRId) ) {

            preparedStatement.setLong(1, id);
            user.getId();
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User c id: " +id+ " удален из таблицы");
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }

    }
    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        connection.setAutoCommit(false);
        String sqlList = "SELECT id, name, lastName, age FROM user";
        try(Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sqlList);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
                connection.commit();
                System.out.println(user.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }

        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        connection.setAutoCommit(false);
        String sqlCT = "DELETE FROM user";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlCT)) {
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Отчищена таблица от Users");
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }
}