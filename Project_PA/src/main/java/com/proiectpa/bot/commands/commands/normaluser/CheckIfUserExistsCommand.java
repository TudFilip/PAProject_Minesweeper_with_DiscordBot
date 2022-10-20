package com.proiectpa.bot.commands.commands.normaluser;

import com.proiectpa.bot.commands.cooldown.CommandCooldown;
import com.proiectpa.bot.commands.interfaces.DiscordCommand;
import com.proiectpa.database.DiscordDAO;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

/**
 * Clasa implementeaza comanda '!checkifuserexists' pe care un user o poate rula si vizualiza daca userul primit ca
 * parametru exista sau nu in baza de date a jocului
 */
public class CheckIfUserExistsCommand implements DiscordCommand {
    /**
     * Metoda are rolul de a verifica daca username-ul primit ca si parametru exista in baza de date, iar in ambele
     * cazuri sa returneze un mesaj de tipul embeded pe canalul unde a fost trimisa comanda.
     * Tipul comenzii asteptate: !checkifuserexists username din joc
     *
     * @param arguments Reprezinta argumentele care sunt primite impreuna cu comanda data
     * @param guild Este serverul de pe care este trimisa comanda
     * @param member Tine minte membrul din server care a trimis comanda
     * @param textChannel Tine minte canalul text de pe care a fost trimisa comanda
     * @param message Tine minte mesajul complet al comenzii
     */
    @Override
    public void executeCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message) {
        if(CommandCooldown.memberCooldown(member)) {
            if (arguments.length == 2) {
                String username = arguments[1];
                Integer response = DiscordDAO.checkIfUserExists(username);
                if (response != 0) {
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setColor(Color.MAGENTA);
                    embedBuilder.setTitle("Userul cu numele _" + username + "_ exista!");
                    textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
                } else {
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setColor(Color.MAGENTA);
                    embedBuilder.setTitle("Userul cu numele _" + username + "_ nu exista!");
                    textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
                }

                member.getUser().openPrivateChannel().queue(privateChannel -> {
                    privateChannel.sendMessage("Comanda **_!checkifuserexists_** a fost executata cu succes!").queue();
                });
            } else {
                textChannel.sendMessage("Introdu corect comanda: **!checkifuserexists <nume user din joc>**").queue();
            }
        } else {
            textChannel.sendMessage("Mai ai de asteptat pana sa introduci o noua comanda!").queue();
        }
    }
}
