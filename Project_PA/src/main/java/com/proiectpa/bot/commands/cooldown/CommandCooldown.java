package com.proiectpa.bot.commands.cooldown;

import net.dv8tion.jda.api.entities.Member;
import java.util.HashMap;
import java.util.Map;

/**
 * Clasa are rolul de a adauga un sistem de cooldown al comenzilor si de a nu putea face spam pe canalele de text
 */
public class CommandCooldown {
    private static final Map<Member, Long> commandsCooldown = new HashMap<>();

    private CommandCooldown() {}

    /**
     * Metoda verifica daca un anumit user mai are sau nu de asteptat pana sa trimita urmatoarea comanda.
     *
     * @param member Este membrul pentru care se calculeaza daca i-a trecut perioada de timp pentru a da urmatoarea comanda
     * @return Este returanta o variabila de tipul boolean care e true daca userul poate sa trimita o noua comanda, sau
     * false daca userul inca mai are de asteptat
     */
    public static boolean memberCooldown(Member member) {
        if(commandsCooldown.containsKey(member) && commandsCooldown.get(member) > System.currentTimeMillis()) {
            return false;
        } else {
            commandsCooldown.put(member, System.currentTimeMillis() + (10 * 1000));
            return true;
        }
    }
}
