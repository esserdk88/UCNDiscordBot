package UCNDiscordBot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PlayerCount {
    // get all players
    public static String getPlayers(MessageReceivedEvent event) {
        return "CS:GO: " + getGameCount(event, "CS:GO") + "\n" +
                "theHunter: " + getGameCount(event, "theHunter: Call of the wild");
    }

    private static int getGameCount(MessageReceivedEvent event, String game) {
        // count how many have role of game
        int count = 0;
        for (int i = 0; i < event.getGuild().getMembers().size(); i++) {
            if (event.getGuild().getMembers().get(i).getRoles()
                    .contains(event.getGuild().getRolesByName(game, true).get(0))) {
                count++;
            }
        }
        return count;
    }
}
