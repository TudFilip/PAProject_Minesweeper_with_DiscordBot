package com.proiectpa.bot.config;

/**
 * Clasa are o metoda ce se foloseste de metoda 'getKeys' a clasei ConfigManager pentru a returna valoarea
 * unei proprietati din fisierul de configuratii
 */
public class ConfigValues {
    private ConfigValues() {}

    /**
     *
     * @param keyName
     * @return Returneaza valorea proprietatii data ca si parametru. Valoare este preluata din fisierul de configuratii
     * si este de tip String
     */
    public static String loadValues(String keyName) {
         return ConfigManager.getKeys(keyName);
    }
}
