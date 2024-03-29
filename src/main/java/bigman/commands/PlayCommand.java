package bigman.commands;

import bigman.music.PlayerManager;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import java.net.MalformedURLException;
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

        String []comContext = event.getMessage().getContentRaw().split(" ");
        String com =comContext[0]+" "+comContext[1];

        if (com.equals(botPrefix + "play"))
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
            String[] input = event.getMessage().getContentRaw().split(" ", 3);
            String link ;
            link = input[2];

            if(!isUrl(link))
            {
                link = "ytsearch:" + link ;

            }
            PlayerManager.getINSTANCE().loadAndPlay(textChannel, link,false);
        }

    }

    public boolean isUrl(String url)
    {
        try
        {
            new URL(url);
            return true;
        }
        catch (MalformedURLException e) {
          return false;
        }
    }
}

