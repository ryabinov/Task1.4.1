package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    //Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует
    void createUsersTable() throws SQLException;
    //Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
    void dropUsersTable() throws SQLException;
    //Добавление User в таблицу
    void saveUser(String name, String lastName, byte age) throws SQLException;
    //Удаление User из таблицы ( по id )
    void removeUserById(long id) throws SQLException;
    //Получение всех User(ов) из таблицы
    List<User> getAllUsers() throws SQLException;
    //Очистка содержания таблицы
    void cleanUsersTable() throws SQLException;
}