package de.Blackside.bot.Moderation.Commands;

import de.Blackside.bot.WKB;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class Kick extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(WKB.prefix + "kick")) {
            if (args.length == 2) {
                if(!event.getMessage().getMentionedMembers().isEmpty()) {
                    if(Objects.requireNonNull(event.getMember()).hasPermission(Permission.KICK_MEMBERS)) {
                        Member member = event.getMessage().getMentionedMembers().get(0);
                        if(!member.hasPermission(Permission.ADMINISTRATOR)) {
                            if(member != event.getJDA().getSelfUser()) {
                                event.getGuild().kick(member).complete();

                                EmbedBuilder success = new EmbedBuilder();
                                success.setColor(0x22ff2a);
                                success.setTitle("✅ Successfully Kicked " + member.getUser().getName());
                                event.getChannel().sendMessage(success.build()).queue();
                                event.getChannel().sendMessage("https://tenor.com/view/charlotte-window-kick-anime-kick-charlotte-kick-off-building-gif-17562086").queue();
                            }
                        }
                    }
                }
            }
        }

    }

}
