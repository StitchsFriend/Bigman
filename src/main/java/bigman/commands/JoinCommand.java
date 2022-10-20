
package bigman.commands;


import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;



import javax.annotation.Nonnull;

public class JoinCommand extends ListenerAdapter {
    public String botPrefix = "b!g ";
    public void onMessageReceived(@Nonnull MessageReceivedEvent event)
    {

        TextChannel textChannel = event.getChannel().asTextChannel();
        VoiceChannel connectedChannel = (VoiceChannel) event.getMember().getVoiceState().getChannel();
        Message message = event.getMessage();

        GuildVoiceState botVoiceState = event.getGuild().getSelfMember().getVoiceState();
        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();

        if (message.getContentRaw().equals(botPrefix + "join"))
        {
            if (connectedChannel == null)
            {
                textChannel.sendMessage("you not in a vc.").queue();
                return;
            }
            if(!memberVoiceState.equals(botVoiceState.getChannel()))
            {
                textChannel.sendMessage("you need to be in same vc as bot for this to work").queue();
                return;
            }
            if(event.getMember().getVoiceState().inAudioChannel())
            {
            textChannel.sendMessage("got it").queue();
            // Gets the audio manager.
            AudioManager audioManager = event.getGuild().getAudioManager();

            // Connects to the channel.
            audioManager.openAudioConnection(connectedChannel);

            // notice someone/something connecting.
            textChannel.sendMessage("Connected to the voice channel!").queue();
            return;
            // audioManager.openAudioConnection(memberChanel);
        }
            // member and bot not in same vc

        // Gets the audio manager.
        AudioManager audioManager = event.getGuild().getAudioManager();

        // Connects to the channel.
        audioManager.openAudioConnection(connectedChannel);

        // notice someone/something connecting.
        textChannel.sendMessage("Connected to the voice channel!").queue();

    }

    }
}



