package de.Blackside.bot.Moderation.ChatSecurity.Events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BannedWords extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        
        String[] bannedwords  = {
            "huso" , "nigga" , "test"
        };

        List<String[]> bannedwordList = new ArrayList<>();

        bannedwordList.add(bannedwords);

        if(event.getMessage().getContentRaw().equalsIgnoreCase(Arrays.toString(bannedwords))) {
            event.getMessage().delete().complete();
        }

    }
    
}
