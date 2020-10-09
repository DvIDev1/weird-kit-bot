package de.Blackside.bot.Fun.Commands;

import de.Blackside.bot.WKB;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class RPSCommand extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {


        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(WKB.prefix + "RPS")) {

            if(args.length == 2) {
                String[] ropasi = {
                        "rock" , "paper" , "scissors"
                };

                Random random = new Random();
                int rndm = random.nextInt(3);
                switch (rndm) {
                    case 0:
                        setHand("rock");
                        break;
                    case 1:
                        setHand("paper");
                        break;
                    case 2:
                        setHand("scissors");
                        break;
                }

                try {
                    String gameSt = game(args[1] , event.getChannel() , event.getMessage());
                    if(gameSt.equals("won")) {
                        event.getChannel().sendMessage("you won!").complete();
                    }else if(gameSt.equals("lost")){
                        event.getChannel().sendMessage("you lost!").complete();
                    }else {
                        event.getChannel().sendMessage("its a draw!").complete();
                    }
                }catch (IllegalStateException e) {
                    event.getMessage().delete().complete();
                    EmbedBuilder error = new EmbedBuilder();
                    error.setColor(0xff3923);
                    error.setTitle("🔴 Incorrect syntax Use");
                    error.setDescription("Use wk!rps [rock|paper|scissors]");
                    event.getChannel().sendMessage(error.build()).queue();
                }

            }

        }

    }

    private String hand;

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public String game(String text , TextChannel channel , Message message) {
        String status = "none";
        switch (text) {
            case "rock":
                channel.sendMessage(":bricks:").complete();
                switch (getHand()) {
                    case "paper":
                        channel.sendMessage(":roll_of_paper: ").complete();
                        status = "lost";
                        break;
                    case "scissors":
                        channel.sendMessage(":scissors:").complete();
                        status = "won";
                        break;
                    case "rock":
                        channel.sendMessage(":bricks:").complete();
                        status = "draw";
                        break;
                }
                break;
            case "paper":
                channel.sendMessage(":roll_of_paper: ").complete();
                switch (getHand()) {
                    case "scissors":
                        channel.sendMessage(":scissors:").complete();
                        status = "lost";
                        break;
                    case "rock":
                        channel.sendMessage(":bricks:").complete();
                        status = "won";
                        break;
                    case "paper":
                        channel.sendMessage(":roll_of_paper: ").complete();
                        status = "draw";
                        break;
                }
                break;
            case "scissors":
                channel.sendMessage(":scissors:").complete();
                switch (getHand()) {
                    case "rock":
                        channel.sendMessage(":bricks:").complete();
                        status = "lost";
                        break;
                    case "paper":
                        channel.sendMessage(":roll_of_paper: ").complete();
                        status = "won";
                        break;
                    case "scissors":
                        channel.sendMessage(":scissors:").complete();
                        status = "draw";
                        break;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + text);
        }
        return status;
    }
}
