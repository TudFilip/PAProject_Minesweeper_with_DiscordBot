package com.proiectpa.database;

import com.proiectpa.bot.config.ConfigValues;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clasa reprezinta o instanta a bazei de date pe care o folosim. Are constructor privat si asigura faptul ca la fiecare
 * moment doar o singura instanta a bazei de date exista
 */
public class Database {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = ConfigValues.loadValues("user");
    private static final String PASSWORD = ConfigValues.loadValues("password");
    private static Connection connection = null;

    private Database(){}

    /**
     * Este singura metoda publica din clasa. Aceasta are rolul de a returna instanta bazei de date
     *
     * @return Returneaza o conexiune catre baza de date
     */
    public static Connection getConnection() {
        createConnection();
        return connection;
    }

    /**
     * Metoda are rolul de a initializa o conexiune cu baza de date folosita
     */
    private static void createConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(
                    URL, USER, PASSWORD
            );
        } catch (SQLException e) {
            System.err.println("Cannot connect to DB: " + e);
        } catch (ClassNotFoundException e) {
            System.err.println("Cannot find DB driver: " + e);
        }
    }
}
