package bigman.commands;

import bigman.JDACommands.ExecuteArgs;
import bigman.JDACommands.ICommand;
import bigman.music.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import javax.annotation.Nonnull;
import javax.lang.model.element.ElementVisitor;
import java.net.URI;
import java.net.URISyntaxException;

public class PlayCommand extends ListenerAdapter {
    public String botPrefix = "b!g ";
    private  String[] args;
    public void onMessageReceived(@Nonnull MessageReceivedEvent event)
    {

        TextChannel textChannel = event.getChannel().asTextChannel();
        VoiceChannel connectedChannel = (VoiceChannel) event.getMember().getVoiceState().getChannel();
        Message message = event.getMessage();

        GuildVoiceState botVoiceState = event.getGuild().getSelfMember().getVoiceState();
        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();


        if (message.getContentRaw().startsWith(botPrefix + "play"))
        {
            if (connectedChannel == null)
            {
                textChannel.sendMessage("you not in a vc.").queue();
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
            /*
            if(!memberVoiceState.equals(botVoiceState.getChannel()))
            {
                textChannel.sendMessage("you need to be in same vc as bot for this to work").queue();
                return;
            }
            */
            // member and bot not in same vc

            // Gets the audio manager.
            AudioManager audioManager = event.getGuild().getAudioManager();

            // Connects to the channel.
            audioManager.openAudioConnection(connectedChannel);

            // notice someone/something connecting.
            textChannel.sendMessage("Connected to the voice channel!").queue();

            String link = String.join(" ",event.getMessage().getContentRaw());

            if(!isUrl(link))
            {
                textChannel.sendMessage("got the link").queue();
                link = "ytsearch:" + link + " audio";
            }
            PlayerManager.getINSTANCE().loadAndPlay(event.getChannel().asTextChannel(), link);

        }


    }

    public boolean isUrl(String url)
    {
        try
        {
            new URI(url);
            return true;
        } catch (URISyntaxException e)
        {
            return false;
        }
    }
}

