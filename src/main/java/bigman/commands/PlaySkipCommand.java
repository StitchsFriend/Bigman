package bigman.commands;

import bigman.music.GuildMusicManager;
import bigman.music.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import java.net.MalformedURLException;
import java.net.URL;

public class PlaySkipCommand extends ListenerAdapter {
    public String botPrefix = "b!g ";

    public void onMessageReceived(MessageReceivedEvent event)
    {
        TextChannel textChannel = event.getChannel().asTextChannel();
        VoiceChannel connectedChannel = (VoiceChannel) event.getMember().getVoiceState().getChannel();
        GuildVoiceState botVoiceState = event.getGuild().getSelfMember().getVoiceState();
        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();
        Message message = event.getMessage();




        if (message.getContentRaw().startsWith(botPrefix + "playskip"))
        {
            if(!memberVoiceState.inAudioChannel())
            {
                textChannel.sendMessage("you need to be in vc for this to work").queue();
                return;
            }

            final AudioManager audioManager = event.getGuild().getAudioManager();
            audioManager.openAudioConnection(connectedChannel);

            String[] input = event.getMessage().getContentRaw().split(" ", 3);
            String link ;
            link = input[2];

            if(!isUrl(link))
            {
                link = "ytsearch:" + link ;

            }
            PlayerManager.getINSTANCE().loadAndPlay(textChannel, link,true);
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

