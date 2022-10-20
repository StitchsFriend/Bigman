package bigmanTest.commands;
import bigman.music.PlayerManager;
import bigmanTest.jdacommands.executeargs;
import bigmanTest.jdacommands.IcommandTest;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.net.URI;
import java.net.URISyntaxException;

public class playcommand implements IcommandTest {
    @Override
    public void execute(executeargs event) {
        GuildVoiceState botVoiceState = event.getGuild().getSelfMember().getVoiceState();
        TextChannel textChannel = event.getTextChannel();

        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();
        //  bot not in VC
        if(!botVoiceState.inAudioChannel())
        {
            try{
                AudioManager audioManager = event.getGuild().getAudioManager();
                VoiceChannel memberChanel = (VoiceChannel) event.getMember().getVoiceState().getChannel();
                audioManager.openAudioConnection(memberChanel);
                String link = String.join(" ",event.getArgs());
                if(!isUrl(link))
                {
                    link = "ytsearch:" + link;
                }
                PlayerManager.getINSTANCE().loadAndPlay(textChannel, link);
                return;
            }
            catch (Exception e)
            {
                event.getTextChannel().sendMessage("Could not join your voice channel, please try").queue();
                return;
            }


        }
        // member not in vc
        if(!memberVoiceState.inAudioChannel())
        {
            textChannel.sendMessage("you need to be in vc for this to work").queue();
            return;
        }
        // member and bot not in same vc
        if(!memberVoiceState.getChannel().equals(botVoiceState.getChannel()))
        {
            textChannel.sendMessage("you need to be in same vc as bot for this to work").queue();
            return;
        }
        //textChannel.sendMessage("got it").queue();

        //textChannel.sendMessage("connected").queue();

        String link = String.join(" ",event.getArgs());

        if(!isUrl(link))
        {
            link = "ytsearch:" + link;
            //link = "ytsearch:" + link + " audio"; //"official music video"
        }

        PlayerManager.getINSTANCE().loadAndPlay(textChannel, link);

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
