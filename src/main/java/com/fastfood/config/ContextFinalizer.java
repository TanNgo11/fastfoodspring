package com.fastfood.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class ContextFinalizer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // This can be left empty or used foar initial setup tasks
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("Deregistered JDBC driver " + driver);
            } catch (SQLException e) {
                System.out.println("Error deregistering JDBC driver " + driver + ": " + e);
            }
        }
        try {
            com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.uncheckedShutdown();
        } catch (Exception e) {
            // Log or handle the exception as needed
            System.out.println("Error shutting down MySQL AbandonedConnectionCleanupThread: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
