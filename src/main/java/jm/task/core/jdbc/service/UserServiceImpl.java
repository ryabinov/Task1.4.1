package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    //UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() throws SQLException {
        //userDaoJDBC.createUsersTable();
        userDaoHibernate.createUsersTable();
    }

    @Override
    public void dropUsersTable() throws SQLException {
        // userDaoJDBC.dropUsersTable();
        userDaoHibernate.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        //userDaoJDBC.saveUser(name, lastName, age);
        userDaoHibernate.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        //userDaoJDBC.removeUserById(id);
        userDaoHibernate.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        //  return userDaoJDBC.getAllUsers();
        return userDaoHibernate.getAllUsers();
    }

    @Override
    public void cleanUsersTable() throws SQLException {
        //  userDaoJDBC.cleanUsersTable();
        userDaoHibernate.cleanUsersTable();
    }
}