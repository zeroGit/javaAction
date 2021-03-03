package org.geektimes.projects.user.web.listener;

import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.repository.UserRepository;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.projects.user.sql.DBConnectionManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebListener
public class DBConnectionInitializerListener implements ServletContextListener {

    public static final String CREATE_USERS_TABLE_DDL_SQL = "CREATE TABLE users(" +
            "id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
            "name VARCHAR(16) NOT NULL, " +
            "password VARCHAR(64) NOT NULL, " +
            "email VARCHAR(64) NOT NULL, " +
            "phoneNumber VARCHAR(64) NOT NULL," +
            "head VARCHAR(128) NOT NULL," +
            "age INT NOT NULL," +
            "title VARCHAR(128) NOT NULL," +
            "autograph VARCHAR(256) NOT NULL " +
            ")";

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();
        Connection connection = null;
        try {
            //String databaseURL = "jdbc:derby:/db/user-platform;create=true";
            //connection = DriverManager.getConnection(databaseURL);

            // jndi
            Context initCtx = new InitialContext();
            Context ctx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) ctx.lookup("jdbc/UserPlatformDB");
            connection = ds.getConnection();

            Statement statement = connection.createStatement();
            statement.execute(CREATE_USERS_TABLE_DDL_SQL);

        } catch (SQLException e) {

            if (!e.getSQLState().equals("X0Y32")) {
                e.printStackTrace();
                throw new RuntimeException("db init error", e);
            }

            if (connection == null) {
                e.printStackTrace();
                throw new RuntimeException("db init error", e);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("db init error", e);
        } finally {

            DBConnectionManager manager = new DBConnectionManager();
            manager.setConnection(connection);

            UserRepository repository = new DatabaseUserRepository(manager);
            UserService userService = new UserServiceImpl(repository);
            servletContext.setAttribute("userService", userService);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
