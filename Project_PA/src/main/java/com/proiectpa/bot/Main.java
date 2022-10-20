package com.proiectpa.bot;

import com.proiectpa.bot.commands.CommandManager;
import com.proiectpa.bot.config.ConfigFile;
import com.proiectpa.bot.config.ConfigValues;
import com.proiectpa.bot.events.RoleReactionAddEvent;
import com.proiectpa.bot.events.TicketReactionAddEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

import javax.security.auth.login.LoginException;

/**
 * Clasa principala a BOT-ului de Discord creat, clasa in care se afla metoda care initializeaza crearea
 * si rularea acestuia
 *
 * @author Tudor
 */
public class Main {
    private static JDA jda;
    private static JDABuilder jdaBuilder;
    private static final String BOT_TOKEN = ConfigValues.loadValues("token");

    /**
     * Metoda main care are rolul de a porni si a initializa BOT-ul de Discord
     *
     * @param args Argumentele primite la initializarea aplicatiei, in cazul de fata nu se primesc parametri
     */
    public static void main(String[] args) {
        ConfigFile.loadFile();
        jdaBuilder = JDABuilder.createDefault(BOT_TOKEN);
        jdaBuilder.setStatus(OnlineStatus.ONLINE);

        try {
            jda = jdaBuilder.build();
        } catch (LoginException exception) {
            exception.printStackTrace();
        }

        receiveCommands();
        receiveEvents();
    }

    /**
     * Metoda este folosita pentru a vedea cu usurinta ce comenzi sunt adaugate si acceptate de catre BOT
     * In cazul de fata este introdusa o singura comanda, commandManager, deoarece este o clasa ce mosteneste
     * clasa ListenerAdapter, clasa ce sta la baza tuturor posibilelor comenzi
     */
    private static void receiveCommands(){
        CommandManager commandManager = new CommandManager();
        jda.addEventListener(commandManager);
    }

    /**
     * Metoda este folosita pentru a vedea cu usurinta ce evenimente sunt adaugate si acceptate de catre BOT
     * In cazul de fata sunt introduse 2 evenimente, cel care inregistreaza reactiile primite la mesajul de pe
     * canalul 'welcome', pentru a primit rol, cat si cel care inregistreaza reactiile primite la mesajul de pe
     * canalul 'trimitere-ticket', pentru a primit un ticket din partea BOT-ului
     */
    private static void receiveEvents() {
        TicketReactionAddEvent ticketReactionAddEvent = new TicketReactionAddEvent();
        jda.addEventListener(ticketReactionAddEvent);
        RoleReactionAddEvent roleReactionAddEvent = new RoleReactionAddEvent();
        jda.addEventListener(roleReactionAddEvent);
    }

    /**
     * Metoda are rolul de a returna variabila jda
     *
     * @return jda variabila jda declara in clasa Main a botului
     */
    public static JDA getJda() {
        return jda;
    }
}
