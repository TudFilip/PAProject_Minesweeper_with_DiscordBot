package com.proiectpa.game;

import javax.swing.*;

import com.proiectpa.bot.config.ConfigFile;
import com.proiectpa.game.userinterface.pages.MainFrame;

/**
 * Clasa principala a jocului de Minesweeper din care este apelata metoda de initiere a aplicatiei
 *
 * @author Andreea
 */
public class Main {

    /**
     * Metoda main care are rolul de a porni si initializa jocul de Minesweeper
     *
     * @param args Argumentele primite la initializarea aplicatiei, in cazul de fata nu se primesc parametri
     */
    public static void main(String[] args) {
        try {
            ConfigFile.loadFile();
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            new MainFrame();
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

