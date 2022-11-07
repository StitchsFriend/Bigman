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
                    .addField("**Music commands**","",true)
                    .addField("`b!g play`","usa with link or search terms",true)
                    .addField("`b!g skip`","skips the currently playing track",true)
                    .addField("`b!g queue`","displays the track queue",true)
                    .addField("`b!g nowplaying`","displays currently playing track and its link",true)
                    .addField("`b!g stop`","stopped player and cleared the queue",true)

                    .addField("**Fun Commands**","",true)
                    .addField("`b!g fortune`","test your fortune. Results may vary. This is random, for real.",true)
                    .build()).queue();
        }
    }


}