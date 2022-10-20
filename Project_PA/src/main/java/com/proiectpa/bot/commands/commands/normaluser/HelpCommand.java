package com.proiectpa.bot.commands.commands.normaluser;

import com.proiectpa.bot.commands.cooldown.CommandCooldown;
import com.proiectpa.bot.commands.interfaces.DiscordCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

/**
 * Clasa implementeaza comanda '!help' pe care un user o poate rula si vizualiza lista cu toate posibilele comenzi
 * pe care le poate trimite catre BOT
 */
public class HelpCommand implements DiscordCommand {
    /**
     * Metoda are rolul de a returna lista cu toate posibilele comenzi pe care un user obisnuit le poate trimite
     * catre BOT. Lista este returnata intr-un mesaj de tipul embeded pe canalul text unde a fost rulata comanda.
     * Tipul comenzii asteptate: !help
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

            embedBuilder.setTitle("**Comenzile disponibile**");
            embedBuilder.setAuthor("Staff-ul Minesweeper");
            embedBuilder.setFooter("Pentru mai multe informatii luati legatura in privat cu staff-ul");
            embedBuilder.setDescription("**!checkifuserexists <user din joc>** -> verifica daca user-ul este inregistrat in joc\n" +
                    "**!getplayers** -> returneaza o lista cu toti userii inregistrati in joc\n" +
                    "**!gettopplayers** -> returneaza o lista cu primele 3 scoruri inregistrate in joc\n" +
                    "**!getusermaxscore <user din joc>** -> returneaza scorul maxim al user-ului\n" +
                    "**!help** -> returneaza lista cu comenzile disponibile");

            textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
        } else {
            textChannel.sendMessage("Mai ai de asteptat pana sa introduci o noua comanda!").queue();
        }
    }
}
