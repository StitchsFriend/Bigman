package bigman;
import bigman.JDACommands.ExecuteArgs;
import bigmanTest.jdacommands.ExecuteArgs2;
import bigman.JDACommands.ICommand;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class logger  extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        MessageChannelUnion channel = event.getChannel();
        // Check whether the message was sent in a guild / server
        if (event.isFromGuild()) {

            // This is a message from a server
            System.out.printf("[%s] [%#s] %#s: %s\n",
                    event.getGuild().getName(), // The name of the server the user sent the message in, this is generally referred to as "guild" in the API
                    event.getChannel(), // The %#s makes use of the channel name and displays as something like #general
                    event.getAuthor(),  // The %#s makes use of User#getAsTag which results in something like ExampleUser#1234
                    event.getMessage().getContentDisplay());// This removes any unwanted mention syntax and converts it to a readable string
        }

        // Using specialization, you can check concrete types of the channel union
        if (channel.getType() == ChannelType.TEXT) {
            System.out.println("The channel topic is: " + channel.asTextChannel().getTopic());
        }

        if (channel.getType().isThread()) {
            System.out.println("This thread is part of channel #" +
                    channel.asThreadChannel()  // Cast the channel union to thread
                            .getParentChannel() // Get the parent of that thread, which is the channel it was created in (like forum or text channel)
                            .getName()          // And then print out the name of that channel
            );

        }
    }
}