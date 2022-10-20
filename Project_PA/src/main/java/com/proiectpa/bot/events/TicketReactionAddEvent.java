package com.proiectpa.bot.events;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.exceptions.ErrorHandler;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.ErrorResponse;
import org.jetbrains.annotations.NotNull;

/**
 * Clasa are rolul de a inregistra reactiile pe care le dau utilizatorii la un mesaj cu scopul
 * de a primi un mesaj privat de la BOT, mesaj intitulat drept 'ticket'
 */
public class TicketReactionAddEvent extends ListenerAdapter {
    /**
     * Metoda este suprascrisa, ea apartinand clasei ListenerAdapter, ea inregistrand reactiile pe care le
     * dau userii la mesajul de pe canalul 'trimitere-tickete' si trimitand la fiecare user cate un mesaj
     * privat in care ii atentioneaza ca ticketul lor a fost inregistrat cu succes
     *
     * @param event Este evenimentul care se petrece in momentul in care se apasa pe o reactie a unui mesaj
     */
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        if(event.isFromGuild() && !event.getMember().getUser().isBot()) {
            if (event.getMessageId().equals("979897062093111336")) {
                event.getReaction().removeReaction(event.getUser()).queue();
                event.getMember().getUser().openPrivateChannel()
                        .flatMap(privateChannel -> privateChannel.sendMessage("Am primit ticketul tau!"))
                        .queue(null, new ErrorHandler().handle(ErrorResponse.CANNOT_SEND_TO_USER,
                                ex -> System.out.println("Nu s-a putut trimite ticketul")));
            }
        }
    }
}
