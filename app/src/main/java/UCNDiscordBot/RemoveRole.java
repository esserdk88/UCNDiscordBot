package UCNDiscordBot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class RemoveRole {
    public static void removeRole(MessageReceivedEvent event, String role) {
        // Check if the user has the role
        if (event.getMember().getRoles().contains(event.getGuild().getRolesByName(role, true).get(0))) {
            event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRolesByName(role, true).get(0))
                    .queue();
        } else {
            event.getChannel().sendMessage("You don't have the role").queue();
        }
    }
}
