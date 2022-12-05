
package bigman.commands;

import bigman.JDACommands.ExecuteArgs;
import bigmanTest.jdacommands.ExecuteArgs2;
import bigman.JDACommands.ICommand;
import bigman.music.*;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import java.net.MalformedURLException;
import java.net.URL;

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
        // get user input command
        String[] input = event.getMessage().getContentRaw().split(" ", 3);
        String link ;
        link = input[2];

        // checking input is url or search by keywords
        if(!isUrl(link))
        {
            link = "ytsearch:" + link;

        }
        //  pass variable to playerManager class to load song and play
        PlayerManager.getINSTANCE().loadAndPlay(event.getTextChannel(), link,false);
        return;
    }
    public boolean isUrl(String url)
    {
        try
        {
            new URL(url);
            return true;
        }  catch (MalformedURLException e) {
           return false;
        }
    }
    @Override // get command name
    public String getName() {
        return "play";
    }


    @Override
    public boolean needOwner() {
        return false;
    }

}


