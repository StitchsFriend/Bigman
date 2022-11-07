
package bigman.commands;

import bigman.JDACommands.ExecuteArgs;
import bigman.JDACommands.ICommand;
import bigman.music.*;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
public class Play implements ICommand {

    @Override
    public void execute(ExecuteArgs event) {

        // member not in vc
        if(!event.getMemberVoiceState().inAudioChannel())
        {
            event.getTextChannel().sendMessage("you need to be in vc for this to work").queue();
            return;
        }
        //  bot not in VC
        if(!event.getSelfVoiceState().inAudioChannel()) {

           final AudioManager audioManager = event.getGuild().getAudioManager();
           final  VoiceChannel memberChanel = (VoiceChannel) event.getMemberVoiceState().getChannel();
            audioManager.openAudioConnection(memberChanel);

        }

        String link = String.join(" ",event.getArgs());
        if(!isUrl(link))
        {
            link = "ytsearch:" + link;
        }
        PlayerManager.getINSTANCE().loadAndPlay(event.getTextChannel(), link);
        return;
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

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String helpMessage() {
        return "this command is to play music.";
    }

    @Override
    public boolean needOwner() {
        return false;
    }

}


