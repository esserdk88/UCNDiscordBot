package UCNDiscordBot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

// This class is a listener for messages
public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // Check if the message is from a bot
        if (event.getAuthor().isBot()) {
            return;
        }

        // Check if the message is "!ping"
        if (event.getMessage().getContentDisplay().equals("!ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        }

        // Check if the message is "!giveRole" and arguments
        if (event.getMessage().getContentDisplay().startsWith("!give")) {
            String[] args = event.getMessage().getContentDisplay().split(" ");
            if (args.length == 2) {
                GiveRole.giveRole(event, args[1]);
            } else {
                event.getChannel().sendMessage("Invalid arguments").queue();
            }
        }

        // Check if the message is "!remove" and arguments
        if (event.getMessage().getContentDisplay().startsWith("!remove")) {
            String[] args = event.getMessage().getContentDisplay().split(" ");
            if (args.length == 2) {
                RemoveRole.removeRole(event, args[1]);
            } else {
                event.getChannel().sendMessage("Invalid arguments").queue();
            }
        }

    }
}