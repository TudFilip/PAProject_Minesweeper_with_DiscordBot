package com.proiectpa.bot.commands.commands.staff;

import com.proiectpa.bot.commands.interfaces.DiscordCommand;
import net.dv8tion.jda.api.entities.*;

/**
 * Clasa implementeaza comanda '!unban' pe care un membru staff o poate executa asupra unui user al serverului
 */
public class UnbanCommand implements DiscordCommand {
    /**
     * Metoda are rolul de a scoate banul pentru userul primit in comanda.
     * Tipul comenzii asteptate: !unban user
     *
     * @param arguments Reprezinta argumentele care sunt primite impreuna cu comanda data
     * @param guild Este serverul de pe care este trimisa comanda
     * @param member Tine minte membrul din server care a trimis comanda
     * @param textChannel Tine minte canalul text de pe care a fost trimisa comanda
     * @param message Tine minte mesajul complet al comenzii
     */
    @Override
    public void executeCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message) {
        if (textChannel.getId().equals("979863719603503164")) {
            if (arguments.length == 2) {
                String id = arguments[1];
                guild.unban(UserSnowflake.fromId(id)).queue();
            } else {
                textChannel.sendMessage("Introdu corect comanda: **!unban <userID>**").queue();
            }
        }
    }
}
