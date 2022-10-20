package com.proiectpa.bot.commands.commands.staff;

import com.proiectpa.bot.Main;
import com.proiectpa.bot.commands.interfaces.DiscordCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

/**
 * Clasa implementeaza comanda '!restart' pe care un membru staff o poate executa pentru a restarta BOT-ul
 */
public class RestartCommand implements DiscordCommand {
    private final JDA jda = Main.getJda();

    /**
     * Metoda are rolul de a restarta BOT-ul si pentru a incarca din fisierul de configuratii noile
     * proprietati. Inainte de a face asta trimite pe toate canalele de text un mesaj de avertizare
     * pentru toti userii ca BOT-ul urmeaza sa se inchida pentru o scurta perioada
     * Tipul comenzii asteptate: !restart
     *
     * @param arguments Reprezinta argumentele care sunt primite impreuna cu comanda data
     * @param guild Este serverul de pe care este trimisa comanda
     * @param member Tine minte membrul din server care a trimis comanda
     * @param textChannel Tine minte canalul text de pe care a fost trimisa comanda
     * @param message Tine minte mesajul complet al comenzii
     */
    @Override
    public void executeCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message) {
        if(textChannel.getId().equals("979863719603503164")) {
            if(member.hasPermission(Permission.ADMINISTRATOR)) {
                guild.getTextChannelById("979853596235559004")
                        .sendMessage("🆘 Botul se restarteaza. Asteptati cateva secunde pana la " +
                                "urmatoarea comanda!").queue();
                guild.getTextChannelById("980184271610322994")
                        .sendMessage("🆘 Botul se restarteaza. Asteptati cateva secunde pana la " +
                                "urmatoarea comanda!").queue();
                guild.getTextChannelById("980184637110382603")
                        .sendMessage("🆘 Botul se restarteaza. Asteptati cateva secunde pana la " +
                                "urmatoarea comanda!").queue();
                textChannel.sendMessage("🆘 Botul se restarteaza. Asteapta cateva secunde pana la " +
                        "urmatoarea comanda!").queue();
                jda.getPresence().setStatus(OnlineStatus.OFFLINE);
                jda.shutdown();
                Main.main(null);
            }
        }
    }
}
