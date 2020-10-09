package de.Blackside.bot.Moderation.Commands;

import de.Blackside.bot.WKB;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Mute extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(WKB.prefix + "mute")) {
            event.getMessage().delete().complete();
            if(Objects.requireNonNull(event.getMember()).hasPermission(Permission.MANAGE_ROLES)) {
                if (!event.getMessage().getMentionedMembers().isEmpty()) {
                    if (args.length == 2) {
                        Member member = event.getMessage().getMentionedMembers().get(0);
                        Role role = event.getGuild().getRoleById("704315386807582840");

                        assert member != null;
                        if (!member.getRoles().contains(role)) {
                            EmbedBuilder success = new EmbedBuilder();
                            success.setColor(0x22ff2a);
                            success.setTitle("✅ Successfully Muted " + member.getUser().getName());
                            event.getChannel().sendMessage(success.build()).queue();

                            assert role != null;
                            event.getGuild().addRoleToMember(member, role).complete();
                        } else {
                            EmbedBuilder success = new EmbedBuilder();
                            success.setColor(0x22ff2a);
                            success.setTitle("✅ Successfully Unmuted " + member.getUser().getName());
                            event.getChannel().sendMessage(success.build()).queue();

                            assert role != null;
                            event.getGuild().removeRoleFromMember(member, role).complete();
                        }
                    } else if (args.length == 3) {
                        Member member = event.getMessage().getMentionedMembers().get(0);
                        Role role = event.getGuild().getRoleById("704315386807582840");

                        EmbedBuilder success = new EmbedBuilder();
                        success.setColor(0x22ff2a);
                        success.setTitle("✅ Successfully Muted " + member.getUser().getName() + " for " + args[2] + " seconds");
                        event.getChannel().sendMessage(success.build()).queue();
                        assert role != null;
                        event.getGuild().addRoleToMember(member, role).complete();

                        new Timer().schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        EmbedBuilder success = new EmbedBuilder();
                                        success.setColor(0x22ff2a);
                                        success.setTitle("✅ Successfully Unmuted " + member.getUser().getName());
                                        event.getChannel().sendMessage(success.build()).queue();

                                        event.getGuild().removeRoleFromMember(member, role).complete();
                                    }
                                }
                                , Integer.parseInt(args[2]) * 1000);

                    } else {
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(0xff3923);
                        error.setTitle("🔴 Incorrect syntax Use");
                        error.setDescription("Use wk!mute [Member] [time{optional}]");
                        event.getChannel().sendMessage(error.build()).queue();
                    }
                }
            }
        }

    }

}


