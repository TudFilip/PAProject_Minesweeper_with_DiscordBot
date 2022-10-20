package com.proiectpa.bot.config;

import com.proiectpa.bot.Main;

import java.io.*;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

/**
 * Clasa are o singura metoda, loadFile, folosita pentru a incarca din memorie sau/si a crea fisierul de configuratii
 * al aplicatiei. Este o clasa cu constructor privat, deci nu poate fi instantiata
 */
public class ConfigFile {
    private ConfigFile() {}

    /**
     * Metoda are rolul de a crea si a incarca in fisierele aplicatiei fisierul de configuratii cu detaliile despre
     * tokenul folosit pentru bot, username-ul de la baza de date si parola folosita la aceasta. Fisierul de
     * configuratii este denumit 'config.yml'
     */
    public static void loadFile() {
        String filename = "config.yml";
        ClassLoader classLoader = Main.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(filename)) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

            File file = new File(filename);
            if(!file.exists()){
                file.createNewFile();
                BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
                buffer.write(result);
                buffer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
