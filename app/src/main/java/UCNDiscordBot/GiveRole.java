package UCNDiscordBot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class GiveRole {
    public static void giveRole(MessageReceivedEvent event, String role) {
        // Check if the user has the role
        if (event.getMember().getRoles().contains(event.getGuild().getRolesByName(role, true).get(0))) {
            event.getChannel().sendMessage("You already have the role").queue();
        } else {
            event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRolesByName(role, true).get(0))
                    .queue();
        }
    }
}
