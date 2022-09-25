package bigman.events;
/* when a member is join the server, send this event */


import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.*;

public class EventJoin extends ListenerAdapter {

    @Override
    // if the member joined the server "CM465 Capstone",bot will send a welcome message at text channel.
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event)
    {
        if(event.getGuild().getId().equals("1012107000290746458"))
        {
            return;
        }
        event.getGuild().getTextChannelById("1012107000290746463").sendMessage("Welcome to the Server " + event.getMember().getAsMention()+"!").queue();

    }
}
