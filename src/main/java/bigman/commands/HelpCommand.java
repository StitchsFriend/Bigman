package bigman.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class HelpCommand extends ListenerAdapter {
    public String botPrefix = "b!g ";

    public void onMessageReceived(@Nonnull MessageReceivedEvent event)
    {
        TextChannel textChannel = event.getChannel().asTextChannel();
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("BigMan Help").setColor(0x7289DA);

        if (event.getMessage().getContentRaw().equals(botPrefix + "help"))
        {
            textChannel.sendMessageEmbeds(info.setDescription("list of command")
                    .addField("**Music commands**","\n`play <Keyword OR url>`" + " add a song to the queue and play it"
                            +"\n `skip`"+" skip the currently playing track"
                            +"\n `pause`"+ " pause the currently playing track"
                            +"\n `resume`" + " resumes the currently paused track"
                            +"\n `playskip`"+ " skips the currently playing track and plays the linked track"
                            +"\n `queue`"+" displays the queue of the current tracks in the playlist"
                            +"\n `nowplaying`"+" view the currently playing song"
                            +"\n `stop`" + " stop the current song and clears the entire music queue"
                            +"\n **Moderation Commands**"
                            +"\n `role`" + " add or remove role(s) for a member"
                            +"\n **Fun Commands**"
                            +"\n `roll <# of rolls>d<sides of dice>`" +" roll a selected number of dice"
                            +"\n `fortune`" +" test your fortune. Results may vary. This is random, for real."
                            +"\n **e-Sport Commands**"
                            +"\n `eAdd`" + " Big Man will post updates of eSports"
                            +"\n `eDelete`"+" Big Man will delete eSport posting channel and relate Webhook"
                            ,false).build()).queue();


        }
    }


}