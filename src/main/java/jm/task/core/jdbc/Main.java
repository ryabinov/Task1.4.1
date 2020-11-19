package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Sasawwha", "adDzdaen", (byte) 11);
        userService.saveUser("Sadwasha", "Dzwaen", (byte) 121);
        userService.saveUser("Saadaa", "D1zen", (byte) 111);
        userService.saveUser("Sadasha", "1eddaDzen", (byte) 5);
        userService.getAllUsers();
        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.getAllUsers();
        userService.dropUsersTable();

    }
}