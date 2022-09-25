
package bigman.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import javax.annotation.Nonnull;

public class JoinCommand extends ListenerAdapter {
    // only listening to guild message
    public String botPrefix = "b!g ";

    //@Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        // Good practise to ignore bots.
        if (event.getAuthor().isBot()) {
            return;
        }
        // The member who sent the message
        User author = event.getAuthor();
        // Gets the raw message content and binds it to a local variable.
        Message message = event.getMessage();
        // So we don't have to access event.getChannel() every time.
        MessageChannelUnion channel = event.getChannel();


        // Checks if the command is b!g join.
        if (message.getContentRaw().equals(botPrefix + "join")) {

            // Creates a variable equal to the channel that the user is in.
            VoiceChannel connectedChannel = (VoiceChannel) event.getMember().getVoiceState().getChannel();
            // Checks if the bot has permissions.
            if (!event.getGuild().getSelfMember().hasPermission((GuildChannel) channel, Permission.VOICE_CONNECT)) {
                // bot does not have permission to join any voice channel.
                event.getChannel().sendMessage("I do not have permissions to join a voice channel!").queue();
                return;
            }
            if(event.getMember().getVoiceState().inAudioChannel())
            {

                event.getChannel().sendMessage("got it").queue();
                // Gets the audio manager.
                AudioManager audioManager = event.getGuild().getAudioManager();

                // Connects to the channel.
                audioManager.openAudioConnection(connectedChannel);

                // notice someone/something connecting.
                channel.sendMessage("Connected to the voice channel!").queue();
                return;
                // audioManager.openAudioConnection(memberChanel);
            }


            // Checks if they are in a channel -- not being in a channel means that the variable = null.
            if (connectedChannel == null) {
                channel.sendMessage("you not in a voice channel.").queue();
                return;
            }
            // checking the user if is with bot at the same channel
/*              if(!connectedChannel.equals(event.getGuild().getSelfMember().getVoiceState()))
                {
                    channel.sendMessage("you need to be in the same voice chanel as me for this to work").queue();
                    return;
                }
 */

            // Gets the audio manager.
            AudioManager audioManager = event.getGuild().getAudioManager();

            // Connects to the channel.
            audioManager.openAudioConnection(connectedChannel);

            // notice someone/something connecting.
            channel.sendMessage("Connected to the voice channel!").queue();

        }

        // bot leave voice channel when alone
            /*
            else if (event.getGuild().getSelfMember().)
            {
                // Gets the channel in which the bot is currently connected.
                VoiceChannel connectedChannel = (VoiceChannel) event.getGuild().getSelfMember().getVoiceState().getChannel();
                // Checks if the bot is connected to a voice channel.
                if (connectedChannel == null) {
                    // Get slightly fed up at the user.
                    channel.sendMessage("I am not connected to a voice channel!").queue();
                    return;
                }
                // Disconnect from the channel.
                event.getGuild().getAudioManager().closeAudioConnection();
                // Notify the user.
                channel.sendMessage("Disconnected from the voice channel!").queue();

            }
            */
    }
}



