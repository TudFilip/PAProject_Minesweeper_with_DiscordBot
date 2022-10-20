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
 * Clasa implementeaza comanda '!getusermaxscore' pe care un user o poate rula si vizualiza timpul cel mai bun obtinut
 * de un user specificat in comanda in rezolvarea jocului
 */
public class GetUserMaxScoreCommand implements DiscordCommand {
    /**
     * Metoda are rolul de a returna scorul maxim al unui user din baza de date a jocului. Raspunsul este
     * returnat intr-un mesaj de tipul embeded pe canalul text unde a fost rulata comanda.
     * Tipul comenzii asteptate: !getusermaxscore
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
                Integer userScore = DiscordDAO.getUserMaxScore(username);
                if (userScore != 0) {
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setColor(Color.ORANGE);
                    embedBuilder.setTitle("Scorul maxim al userului _" + username + "_ este");
                    embedBuilder.setDescription(Integer.toString(userScore));
                    textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
                } else {
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setColor(Color.ORANGE);
                    embedBuilder.setTitle("Userul dat nu exista!");
                    textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
                }

                member.getUser().openPrivateChannel().queue(privateChannel -> {
                    privateChannel.sendMessage("Comanda **_!getusermaxscore_** a fost executata cu succes!").queue();
                });
            } else {
                textChannel.sendMessage("Introdu corect comanda: **!getusermaxscore <nume user din joc>**").queue();
            }
        } else {
            textChannel.sendMessage("Mai ai de asteptat pana sa introduci o noua comanda!").queue();
        }
    }
}
