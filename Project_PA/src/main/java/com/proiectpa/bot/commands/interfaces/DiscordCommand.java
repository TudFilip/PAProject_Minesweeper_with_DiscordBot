package com.proiectpa.bot.commands.interfaces;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

/**
 * Interfata pentru a defini metoda folosita de fiecare comanda pentru a descrie ce face
 */
public interface DiscordCommand {
    /**
     * @param arguments Reprezinta argumentele care sunt primite impreuna cu comanda data
     * @param guild Este serverul de pe care este trimisa comanda
     * @param member Tine minte membrul din server care a trimis comanda
     * @param textChannel Tine minte canalul text de pe care a fost trimisa comanda
     * @param message Tine minte mesajul complet al comenzii
     */
    void executeCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message);
}
