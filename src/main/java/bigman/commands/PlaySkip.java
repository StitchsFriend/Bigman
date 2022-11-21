package bigman.commands;

import bigman.JDACommands.ExecuteArgs;
import bigman.JDACommands.ICommand;
import bigman.music.GuildMusicManager;
import bigman.music.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import java.net.MalformedURLException;
import java.net.URL;

public class PlaySkip implements ICommand {
    @Override
    public void execute(ExecuteArgs event) {

        if(!event.getMemberVoiceState().inAudioChannel())
        {
            event.getTextChannel().sendMessage("you need to be in voice chanel for this to work").queue();
            return;
        }
        // get new song
        String[] input = event.getMessage().getContentRaw().split(" ", 3);
        String link ;
        link = input[2];

        final AudioManager audioManager = event.getGuild().getAudioManager();
        final VoiceChannel memberChanel = (VoiceChannel) event.getMemberVoiceState().getChannel();
        audioManager.openAudioConnection(memberChanel);


            final GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(event.getGuild());
            final AudioPlayer audioPlayer = musicManager.audioPlayer;

           //add a song to the front of the queue

        if(!isUrl(link))
        {
            link = "ytsearch:" + link;
        }
        PlayerManager.getINSTANCE().loadAndPlay(event.getTextChannel(),link,true);


    }
    public boolean isUrl(String url)
    {
        try
        {
            new URL(url);
            return true;
        }  catch ( MalformedURLException e) {
            return false;
        }
    }
    @Override
    public String getName() {
        return "playskip";
    }

    @Override
    public String helpMessage() {
        return null;
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
