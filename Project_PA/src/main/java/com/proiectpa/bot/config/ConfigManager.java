package com.proiectpa.bot.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Clasa are o metoda folosita pentru a prelua din fisierul de configuratii al aplicatiei proprietatile cerute. Este
 * o clas cu constructor privat, deci nu poate fi instantiata
 */
public class ConfigManager {
    private ConfigManager() {}

    /**
     * Metoda are rolul de a cauta si returna o proprietate ceruta, proprietate aflata in fisierul de configuratii
     *
     * @param key Proprietate care se cauta in fisierul de configuratii si a carei valoare trebuie returnata
     * @return Returneaza valorea proprietatii data ca si parametru. Valoare este preluata din fisierul de configuratii
     * si este de tip String
     */
    public static String getKeys(String key) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream("config.yml");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
