package com.proiectpa.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Clasa are rolul de a gestiona toate comenzile care se pot produce asupra bazei de date si care sa aiba legatura
 * cu jocul de Minesweeper si cu packetul game_pa existent in baza de date
 */
public class GameDAO {
    private GameDAO() {}

    /**
     * Metoda are rolul de a insera in baza de date, cu ajutorul unei proceduri din PLSQL, un nou user
     *
     * @param username Username-ul noului utilizator al jocului
     */
    public static void addNewUser(String username) {
        Connection con = Database.getConnection();
        try {
            CallableStatement cstmt = con.prepareCall("{call game_pa.insertNewUser(?)}");
            cstmt.setString(1, username);
            cstmt.executeUpdate();
            con.commit();
            cstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda are rolul de a insera in baza de date, cu ajutorul unei proceduri din PLSQL, un nou timp de rezolvare
     * al jocului pentru un utilizator
     *
     * @param username Username-ul utilizatorului pentru care este adaugat scorul
     * @param score Timpul obtinut de utilizator in rezolvarea jocului
     */
    public static void insertUserScore(String username, int score) {
        Connection con = Database.getConnection();
        try {
            CallableStatement cstmt = con.prepareCall("{call game_pa.insertUserScore(?,?)}");
            cstmt.setString(1, username);
            cstmt.setInt(2, score);
            cstmt.executeUpdate();
            con.commit();
            cstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda are rolul de a insera in baza de date, cu ajutorul unei proceduri din PLSQL, un nou uer in tabela
     * de utilizatori loggati in sesiunea curenta
     *
     * @param username Username-ul utilizatorului care s-a loggat in sesiunea curenta
     */
    public static void insertNewLoggedUser(String username) {
        Connection con = Database.getConnection();
        try {
            CallableStatement cstmt = con.prepareCall("{call game_pa.insertNewLoggedUser(?)}");
            cstmt.setString(1, username);
            cstmt.executeUpdate();
            con.commit();
            cstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda are rolul de a interoga baza de date, cu ajutorul unei functii din PLSQL, si de a verifica daca un
     * anumit utilizator este loggat deja in sesiunea curenta de joc
     *
     * @param username Username-ul utilizatorului care este cautat in baza de date pentru a vedea daca este loggat
     *                 in sesiune curenta
     * @return Valorea returnata este 0 daca utilizatorul nu este loggat deja si 1 in caz contrar
     */
    public static boolean checkIfUserIsLogged(String username) {
        Connection con = Database.getConnection();
        int response = 0;
        try{
            CallableStatement cstmt = con.prepareCall("{? = call game_pa.checkIfUserIsLogged(?)}");
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setString(2, username);
            cstmt.executeUpdate();
            response = cstmt.getInt(1);
            cstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (response != 0);
    }
}
