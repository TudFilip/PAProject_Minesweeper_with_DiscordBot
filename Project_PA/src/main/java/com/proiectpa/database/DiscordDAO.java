package com.proiectpa.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Clasa are rolul de a gestiona toate comenzile care se pot produce asupra bazei de date si care sa aiba legatura
 * cu BOT-ul de Discord si cu packetul discord_bot existent in baza de date
 */
public class DiscordDAO {
    private DiscordDAO() {}

    /**
     * Metoda are rolul de a interoga baza de date, folosind o functie PLSQL, si de a returna lista cu toti userii care
     * si-au creat cont pentru a juca jocul
     *
     * @return Returneaza o lista cu toti userii existenti in baza de date
     */
    public static String getAllUsers() {
        Connection con = Database.getConnection();
        StringBuilder response = new StringBuilder("");
        try {
            CallableStatement cstmt = con.prepareCall("{? = call discord_bot.getAllUsers()}");
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.execute();
            response.append(cstmt.getString(1));
            cstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    /**
     * Metoda are rolul de a interoga baza de date, folosind o functie PLSQL, si de a returna lista cu primii 3 useri
     * care au obtinut cel mai bun timp in rezolvarea jocului
     *
     * @return Returneaza o lista cu primii 3 useri care au obtinut cel mai mic timp in rezolvarea jocului
     */
    public static String getTopUsers() {
        Connection con = Database.getConnection();
        StringBuilder response = new StringBuilder("");
        try {
            CallableStatement cstmt = con.prepareCall("{? = call discord_bot.getTopUsers()}");
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.executeUpdate();
            response.append(cstmt.getString(1));
            cstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    /**
     * Metoda are rolul de a interoga baza de date, folosind o functie PLSQL, si de a verifica daca un username se afla
     * in tabelul cu userii inregistrati in joc
     *
     * @param username Username-ul care este cautat in baza de date
     * @return Returneaza 1 in cazul in care username-ul dat ca parametru este in baza de date si 0 in caz contrar
     */
    public static Integer checkIfUserExists(String username) {
        Connection con = Database.getConnection();
        Integer response = 0;
        try {
            CallableStatement cstmt = con.prepareCall("{? = call discord_bot.checkIfUserExists(?)}");
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setString(2, username);
            cstmt.executeUpdate();
            response = cstmt.getInt(1);
            cstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     * Metoda are rolul de a interoga baza de date, folosind o functie PLSQL, si de a returna cel mai bun timp de
     * rezolvare a jocului al unui user
     *
     * @param username Username-ul care este cautat in baza de date
     * @return Returneaza cel mai bun timp de rezolvare al jocului al username-ului dat ca parametru
     */
    public static Integer getUserMaxScore(String username) {
        Connection con = Database.getConnection();
        Integer response = 0;
        try{
            CallableStatement cstmt = con.prepareCall("{? = call discord_bot.getUserMaxScore(?)}");
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setString(2, username);
            cstmt.executeUpdate();
            response = cstmt.getInt(1);
            cstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return response;
    }
}
