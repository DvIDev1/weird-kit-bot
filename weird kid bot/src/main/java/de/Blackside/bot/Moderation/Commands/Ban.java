package de.Blackside.bot.Moderation.Commands;

import de.Blackside.bot.WKB;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Objects;

public class Ban extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(WKB.prefix + "ban")) {
            if (args.length == 4) {
                if(!event.getMessage().getMentionedMembers().isEmpty()) {
                    if(Objects.requireNonNull(event.getMember()).hasPermission(Permission.BAN_MEMBERS)) {
                        Member member = event.getMessage().getMentionedMembers().get(0);
                        if(!member.hasPermission(Permission.ADMINISTRATOR)) {
                            if(member != event.getJDA().getSelfUser()) {

                                EmbedBuilder succes = new EmbedBuilder();
                                succes.setTitle("[Ban]" + member.getUser().getName() + member.getUser().getAsTag());
                                succes.setColor(Color.RED);

                                Objects.requireNonNull(event.getGuild().getTextChannelById("709824130861760573")).sendMessage(succes.build());

                                event.getGuild().ban(member.getUser() , Integer.parseInt(args[2])).complete();

                                EmbedBuilder success = new EmbedBuilder();
                                success.setColor(0x22ff2a);
                                success.setTitle("✅ Successfully Banned " + member.getUser().getName());
                                event.getChannel().sendMessage(success.build()).queue();
                                event.getChannel().sendMessage("https://tenor.com/view/sao-liz-lisbeth-anime-ban-gif-14368031").queue();
                            }
                        }
                    }
                }
            }
        }

    }

}
