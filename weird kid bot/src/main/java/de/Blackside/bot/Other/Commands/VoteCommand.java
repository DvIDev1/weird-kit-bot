package de.Blackside.bot.Other.Commands;

import de.Blackside.bot.WKB;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VoteCommand extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(WKB.prefix + "vote")) {
            event.getChannel().sendMessage("https://top.gg/servers/640633325836304394/vote").complete();
        }

    }

}
