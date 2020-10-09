package de.Blackside.bot.Fun.Commands;

import de.Blackside.bot.WKB;
import jdk.nashorn.internal.parser.JSONParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MemeCommand extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(WKB.prefix + "meme")) {
            String title = "";
            String imageurl = "";
            String postlink = "";
            boolean isNsfw = false;
            boolean isSpoiler = false;
            try {
                do {
                    URL memeurl = new URL("https://meme-api.herokuapp.com/gimme");
                    BufferedReader bf = new BufferedReader(new InputStreamReader(memeurl.openConnection().getInputStream()));
                    String input = bf.readLine();
                    title = input.substring(input.indexOf("\"title\":") + "\"title\":\"".length(), input.indexOf("\",\"url\":"));
                    imageurl = input.substring(input.indexOf("\"url\":\"") + "\"url\":\"".length(), input.indexOf("\",\"nsfw\":"));
                    postlink = input.substring(input.indexOf("\"postLink\":\"") + "\"postLink\":\"".length(), input.indexOf("\",\"subreddit\":"));

                    isNsfw = input.substring(input.indexOf("\"nsfw\":") + "\"nsfw\":".length(), input.indexOf(",\"spoiler\":")).equalsIgnoreCase("true");
                    isSpoiler = input.substring(input.indexOf(",\"spoiler\":") + ",\"spoiler\":".length(), input.indexOf("}")).equalsIgnoreCase("true");
                    //If you are ok with both spoilers and nsfw memes then replace lines 31-45 with "break;"
                } while (isNsfw || isSpoiler);
                event.getChannel().sendMessage(new EmbedBuilder()
                        .setTitle(title , postlink)
                        .setImage(imageurl)
                        .build()).queue();
            } catch(Exception e) {
                event.getChannel().sendMessage("**Something went wrong!** Please try again later!").queue();
            }
        }
    }
}
