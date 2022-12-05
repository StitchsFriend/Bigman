package bigman.commands;

import bigman.JDACommands.ExecuteArgs;
import bigman.JDACommands.ICommand;
import bigman.music.GuildMusicManager;
import bigman.music.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SkipCommand implements ICommand {
    @Override
    public void execute(ExecuteArgs event) {
        if (!event.getSelfVoiceState().inAudioChannel()) {
            event.getTextChannel().sendMessage("I need to be in a voice channel for this to work").queue();

        }
        if (!event.getMemberVoiceState().inAudioChannel()) {
            event.getTextChannel().sendMessage("You need to be in a voice channel for this command to work").queue();

        }
        if (!event.getMemberVoiceState().getChannel().equals(event.getSelfVoiceState().getChannel())) {
            event.getTextChannel().sendMessage("You need to be in the same voice channel as me for this to work").queue();

        }
        final GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(event.getGuild());
        final AudioPlayer audioPlayer = musicManager.audioPlayer;
        musicManager.scheduler.nextTrack();
        event.getTextChannel().sendMessage("Skipped the current track").queue();
        if(audioPlayer.getPlayingTrack()==null)
        {
            event.getTextChannel().sendMessage("Hey, here is end of the playlist, no track in the queue").queue();
        }
    }

    @Override
    public String getName() {
        return "skip";
    }


    @Override
    public boolean needOwner() {
        return false;
    }
}
