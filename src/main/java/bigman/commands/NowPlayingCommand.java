package bigman.commands;

import bigman.JDACommands.ExecuteArgs;
import bigman.JDACommands.ICommand;
import bigman.music.GuildMusicManager;
import bigman.music.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class NowPlayingCommand implements ICommand {
    @Override
    public void execute(ExecuteArgs event) {
        if (!event.getSelfVoiceState().inAudioChannel()) {
            event.getTextChannel().sendMessage("I need to be in a voice channel for this to work").queue();
            return ;
        }
        if (!event.getMemberVoiceState().inAudioChannel()) {
            event.getTextChannel().sendMessage("You need to be in a voice channel for this command to work").queue();
            return ;
        }
        if (!event.getMemberVoiceState().getChannel().equals(event.getSelfVoiceState().getChannel())) {
            event.getTextChannel().sendMessage("You need to be in the same voice channel as me for this to work").queue();
            return ;
        }
        final GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(event.getGuild());
        final AudioPlayer audioPlayer = musicManager.audioPlayer;
        final AudioTrack audioTrack = audioPlayer.getPlayingTrack();

        if (audioTrack == null) {
            event.getTextChannel().sendMessage("There is no track playing currently").queue();
            return;
        }

        final AudioTrackInfo info = audioTrack.getInfo();

        event.getTextChannel().sendMessageFormat("Now playing `%s` by `%s` (Link: <%s>)", info.title, info.author, info.uri).queue();
    }

    @Override
    public String getName() {
        return "nowplaying";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}

