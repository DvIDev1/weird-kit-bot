package de.Blackside.bot;

import de.Blackside.bot.Fun.Commands.MemeCommand;
import de.Blackside.bot.Fun.Commands.RPSCommand;
import de.Blackside.bot.Moderation.ChatSecurity.Events.BannedWords;
import de.Blackside.bot.Moderation.Commands.Ban;
import de.Blackside.bot.Moderation.Commands.Clear;
import de.Blackside.bot.Moderation.Commands.Kick;
import de.Blackside.bot.Moderation.Commands.Mute;
import de.Blackside.bot.Other.Commands.VoteCommand;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class WKB {

    public static JDA jda;

    public static String prefix = "wk!";

    public static WKB wkb;

    public static void main(String[] args) throws LoginException {
        new WKB();
    }

    public WKB() throws LoginException {
        jda = new JDABuilder(AccountType.BOT).setToken("NzM2NzAwMDQ2ODI0ODMzMDU1.XxynYg.UI-3InIbA2OV3XwnG5VBZ8l-5_Q").build();
        jda.getPresence().setActivity(Activity.watching("Animes"));
        jda.setAutoReconnect(true);
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        jda.addEventListener(new Clear());
        jda.addEventListener(new Mute());
        jda.addEventListener(new Ban());
        jda.addEventListener(new Kick());
        jda.addEventListener(new VoteCommand());
        jda.addEventListener(new MemeCommand());
        jda.addEventListener(new BannedWords());
        jda.addEventListener(new RPSCommand());
    }
}
