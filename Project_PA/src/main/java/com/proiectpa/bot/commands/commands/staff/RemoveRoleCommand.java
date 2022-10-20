package com.proiectpa.bot.commands.commands.staff;

import com.proiectpa.bot.commands.interfaces.DiscordCommand;
import net.dv8tion.jda.api.entities.*;

/**
 * Clasa implementeaza comanda '!removerole' pe care un membru staff o poate executa asupra unui user al serverului
 */
public class RemoveRoleCommand implements DiscordCommand {
    /**
     * Metoda are rolul de a scoate un rol primit in comanda pentru un user dat.
     * Tipul comenzii asteptate: !removerole user rolID
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
            if(arguments.length == 3) {
                Member target = message.getMentions().getMembers().get(0);
                if(target != null) {
                    Role role = guild.getRoleById(arguments[2]);
                    if(role != null) {
                        guild.removeRoleFromMember(target, role).queue();
                        textChannel.sendMessage("Rol eliminat cu succes!").queue();
                    }
                }
            } else {
                textChannel.sendMessage("Introdu corect comanda: **!removetrole <user> <roleID>**").queue();
            }
        }
    }
}
