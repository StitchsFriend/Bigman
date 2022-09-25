
package bigman.commands;

import bigman.JDACommands.ExecuteArgs;
import bigman.JDACommands.ICommand;
import bigman.JDACommands.JDACommands;
import bigman.music.PlayerManager;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.internal.requests.restaction.operator.FlatMapErrorRestAction;

import javax.swing.plaf.ListUI;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class PlayCommand implements ICommand {

    @Override
    public void execute(ExecuteArgs event) {
        TextChannel textChannel= event.getTextChannel();

        //  if user in voice channel
        if(event.getMemberVoiceState().inAudioChannel())
        {
            textChannel.sendMessage("got it").queue();
            //AudioManager audioManager = event.getGuild().getAudioManager();
           // VoiceChannel memberChanel = (VoiceChannel) event.getMember().getVoiceState().getChannel();
           // audioManager.openAudioConnection(memberChanel);
            return;
        }
        //  if user  not in voice channel
        if(!event.getMemberVoiceState().inAudioChannel())
        {
            textChannel.sendMessage("You need to be in a voice chanel for the command to work.").queue();
            return;

        }

/*
        if(!event.getSelfVoiceState().inAudioChannel())
        {
            //final AudioManager audioManager = event.getGuild().getAudioManager();
           // final  VoiceChannel memberChanel = (VoiceChannel) event.getMember().getVoiceState().getChannel();
            textChannel.sendMessage("got it").queue();
            AudioManager audioManager = event.getGuild().getAudioManager();
            VoiceChannel memberChanel = (VoiceChannel) event.getMember().getVoiceState().getChannel();
            audioManager.openAudioConnection(memberChanel);
            textChannel.sendMessage("connected").queue();
        }

 */
        //AudioManager audioManager = event.getGuild().getAudioManager();
       //VoiceChannel memberChanel = (VoiceChannel) event.getMember().getVoiceState().getChannel();
      //  audioManager.openAudioConnection(memberChanel);

        String link = String.join(" ",event.getArgs());

        if(!isUrl(link))
        {
            link = "ytsearch:" + link + " audio"; //"official music video"
        }

        PlayerManager.getINSTANCE().loadAndPlay(event.getTextChannel(), link);
    }

    @Override
    public String getName() {
        return "b!g play";
    }

    @Override
    public String helpMessage() {
        return null;
    }

    @Override
    public boolean needOwner() {
        return false;
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


