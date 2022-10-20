package com.proiectpa.bot.commands;

import com.proiectpa.bot.commands.commands.normaluser.*;
import com.proiectpa.bot.commands.commands.staff.*;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * Clasa are rolul de a gestiona comenzile primite de la useri
 */
public class CommandManager extends ListenerAdapter {
    private final KickCommand kickCommand;
    private final BanCommand banCommand;
    private final UnbanCommand unbanCommand;
    private final GetAllPlayersCommand getAllPlayersCommand;
    private final GetTopPlayersCommand getTopPlayersCommand;
    private final GetUserMaxScoreCommand getUserMaxScoreCommand;
    private final CheckIfUserExistsCommand checkIfUserExistsCommand;
    private final HelpCommand helpCommand;
    private final SetRoleCommand setRoleCommand;
    private final RemoveRoleCommand removeRoleCommand;
    private final RestartCommand restartCommand;

    /**
     * Constructorul are rolul de a initializa fiecare posibila comanda pe care un utilizator o poate trimite
     */
    public CommandManager() {
        this.kickCommand = new KickCommand();
        this.banCommand = new BanCommand();
        this.unbanCommand = new UnbanCommand();
        this.getAllPlayersCommand = new GetAllPlayersCommand();
        this.getTopPlayersCommand = new GetTopPlayersCommand();
        this.getUserMaxScoreCommand = new GetUserMaxScoreCommand();
        this.checkIfUserExistsCommand = new CheckIfUserExistsCommand();
        this.helpCommand = new HelpCommand();
        this.setRoleCommand = new SetRoleCommand();
        this.removeRoleCommand = new RemoveRoleCommand();
        this.restartCommand = new RestartCommand();
    }

    /**
     * Metoda are rolul de a gestiona si a selecta comanda corespunzatoare ce trebuie executata, comanda ce este
     * primita prin cadrul evenimentului dat ca parametru
     *
     * @param event Este evenimentul care inregistreaza ca un mesaj a fost trimis pe unul dintre canalele text ale
     *              serverului
     */
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(!event.getMember().getUser().isBot() && event.isFromGuild()) {
            String[] arguments = event.getMessage().getContentRaw().split(" ");
            Guild guild = event.getGuild();
            Member member = event.getMember();
            TextChannel textChannel = event.getTextChannel();
            Message message = event.getMessage();

            switch (arguments[0]){
                case "!kick": // !kick <user> <motiv>
                    kickCommand.executeCommand(arguments,guild,member,textChannel,message);
                    break;
                case "!ban": // !ban <user> <motiv>
                    banCommand.executeCommand(arguments,guild,member,textChannel,message);
                    break;
                case "!unban": // !unban <user>
                    unbanCommand.executeCommand(arguments,guild,member,textChannel,message);
                    break;
                case "!getplayers": // !getplayers
                    getAllPlayersCommand.executeCommand(arguments,guild,member,textChannel,message);
                    break;
                case "!gettopplayers": // !gettoplayers
                    getTopPlayersCommand.executeCommand(arguments,guild,member,textChannel,message);
                    break;
                case "!getusermaxscore": // !getusermaxscore <user din joc>
                    getUserMaxScoreCommand.executeCommand(arguments,guild,member,textChannel,message);
                    break;
                case "!checkifuserexists": // !checkifuserexists <user din joc>
                    checkIfUserExistsCommand.executeCommand(arguments,guild,member,textChannel,message);
                    break;
                case "!help": // !help
                    helpCommand.executeCommand(arguments,guild,member,textChannel,message);
                    break;
                case "!setrole": // !setrole <user> <roleID>
                    setRoleCommand.executeCommand(arguments,guild,member,textChannel,message);
                    break;
                case "!removerole": // !removerole <user> <roleID>
                    removeRoleCommand.executeCommand(arguments,guild,member,textChannel,message);
                    break;
                case "!restart": // !restart
                    restartCommand.executeCommand(arguments,guild,member,textChannel,message);
                    break;
            }
        }
    }
}
