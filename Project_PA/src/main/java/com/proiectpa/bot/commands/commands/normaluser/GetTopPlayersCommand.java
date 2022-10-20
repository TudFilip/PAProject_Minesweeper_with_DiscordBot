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
 * Clasa implementeaza comanda '!gettopplayers' pe care un user o poate rula si vizualiza lista cu primii 3 useri care
 * au obtinut cel mai bun timp in rezolvarea jocului
 */
public class GetTopPlayersCommand implements DiscordCommand {
    /**
     * Metoda are rolul de a returna lista primii 3 useri din baza de date a jocului care au obtinut cel mai bun timp
     * in rezolvarea jocului. Lista este returnata intr-un mesaj de tipul embeded pe canalul text unde a fost
     * rulata comanda.
     * Tipul comenzii asteptate: !gettopplayers
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
            EmbedBuilder embedBuilder = new EmbedBuilder();

            embedBuilder.setColor(Color.BLUE);
            embedBuilder.setDescription(DiscordDAO.getTopUsers());
            embedBuilder.setTitle("PRIMELE 3 SCORURI DIN TOATE TIMPURILE");

            textChannel.sendMessageEmbeds(embedBuilder.build()).queue();

            member.getUser().openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessage("Comanda **_!gettopplayers_** a fost executata cu succes!").queue();
            });
        } else {
            textChannel.sendMessage("Mai ai de asteptat pana sa introduci o noua comanda!").queue();
        }
    }
}
