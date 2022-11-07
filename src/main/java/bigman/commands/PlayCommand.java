package bigman.commands;

import bigman.music.PlayerManager;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class PlayCommand extends ListenerAdapter {
    public String botPrefix = "b!g ";
    public void onMessageReceived(MessageReceivedEvent event)
    {
        TextChannel textChannel = event.getChannel().asTextChannel();
        VoiceChannel connectedChannel = (VoiceChannel) event.getMember().getVoiceState().getChannel();
        Message message= event.getMessage();

        GuildVoiceState botVoiceState = event.getGuild().getSelfMember().getVoiceState();
        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();

        if (!event.getMessage().getContentRaw().startsWith("b!g play")) return;
        if (event.getAuthor().isBot()) return;

        Guild guild = event.getGuild();
        AudioManager manager = guild.getAudioManager();

        if (message.getContentRaw().startsWith(botPrefix + "play"))
        {
            if(!memberVoiceState.inAudioChannel())
            {
                textChannel.sendMessage("you need to be in vc for this to work").queue();
                return;
            }

            if(!botVoiceState.inAudioChannel())
            {
               final AudioManager audioManager = event.getGuild().getAudioManager();
                audioManager.openAudioConnection(connectedChannel);
                textChannel.sendMessage("Connected to the voice channel!").queue();

            }
            // get user input "b!g play url"
           String input = event.getMessage().getContentRaw().toString();
            // replace to link1 = "url"
           String link1 = input.replace("b!g play","");

            PlayerManager.getINSTANCE().loadAndPlay(textChannel, link1);

            if(!isUrl(link1))
            {
                textChannel.sendMessage("got it").queue();
                link1 = "ytsearch:" + link1 ;
                PlayerManager.getINSTANCE().loadAndPlay(textChannel, link1);
            }

        }

    }

    public boolean isUrl(String url)
    {
        try
        {
            new URI(url);
            return true;
        }
        catch (URISyntaxException e) {
          return false;
        }
    }
}

