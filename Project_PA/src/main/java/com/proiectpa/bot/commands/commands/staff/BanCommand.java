package com.proiectpa.bot.commands.commands.staff;

import com.proiectpa.bot.commands.interfaces.DiscordCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.exceptions.ErrorHandler;
import net.dv8tion.jda.api.requests.ErrorResponse;

/**
 * Clasa implementeaza comanda '!ban' pe care un membru staff o poate executa asupra unui user al serverului
 */
public class BanCommand implements DiscordCommand {
    /**
     * Metoda are rolul de a bana de pe server pentru 3 zile userul primit in comanda.
     * Tipul comenzii asteptate: !ban user motiv
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
            if(arguments.length >= 3) {
                Member target = message.getMentions().getMembers().get(0);
                if(target != null) {
                    StringBuilder reason = new StringBuilder("");
                    for(int i = 2; i < arguments.length; i++)
                        reason.append(arguments[i]).append(" ");

                    if(member.hasPermission(Permission.BAN_MEMBERS)){
                        target.getUser().openPrivateChannel()
                                .flatMap(privateChannel -> privateChannel.sendMessage("Ne pare rau, dar ati fost banat pentru 3 zile din serverul **Minesweeper - PA Project**\n" +
                                        "Motivul: " + reason.toString() + "\n" +
                                        "**_AVERTISMENT!_** La urmatoarea abatere veti fi dat afara!"))
                                .queue(null, new ErrorHandler().handle(ErrorResponse.CANNOT_SEND_TO_USER,
                                        ex -> System.out.println("Nu s-a putut trimite mesajul")));

                        target.ban(3, reason.toString()).queue();
                    }
                }
            } else {
                textChannel.sendMessage("Introdu corect comanda: **!ban <user> <reason>**").queue();
            }
        }
    }
}
