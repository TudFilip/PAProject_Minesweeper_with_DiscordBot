package com.proiectpa.bot.events;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * Clasa are rolul de reactiona in momentul in care un utilizator reactioneaza la un mesaj dat si
 * de a ii oferi rolul corespunzator emojiului pe care acesta il apasa
 */
public class RoleReactionAddEvent extends ListenerAdapter {
    /**
     * Este suprascrisa o metoda din clasa ListenerAdapter pentru a inregistra si a da rolul cerut
     * celor care reactioneaza la mesajul pus pe canalul 'welcome' de pe server.
     *
     * @param event Este evenimentul care se petrece in momentul in care se apasa pe o reactie a unui mesaj
     */
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        if(event.isFromGuild() && !event.getMember().getUser().isBot() && event.getMessageId().equals("980180139168124938")) {
            if(event.getReactionEmote().getName().equals("1️⃣")) {
                event.getReaction().removeReaction(event.getUser()).queue();
                event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById("980168034419343421")).queue();
            } else
                if(event.getReactionEmote().getName().equals("2️⃣")) {
                    event.getReaction().removeReaction(event.getUser()).queue();
                    event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById("980183563628609617")).queue();
                } else
                    if(event.getReactionEmote().getName().equals("3️⃣")) {
                        event.getReaction().removeReaction(event.getUser()).queue();
                        event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById("980183602040016967")).queue();
                    }
        }
    }
}
