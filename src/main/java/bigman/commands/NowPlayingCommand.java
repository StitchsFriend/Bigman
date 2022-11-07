package bigman.commands;

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

public class NowPlayingCommand extends ListenerAdapter {
    public String botPrefix = "b!g ";

    public void onMessageReceived(MessageReceivedEvent event)
    {
        TextChannel textChannel = event.getChannel().asTextChannel();
        GuildVoiceState botVoiceState = event.getGuild().getSelfMember().getVoiceState();
        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();

        Message message= event.getMessage();

        if (message.getContentRaw().startsWith(botPrefix + "nowplaying"))
        {
            if (!botVoiceState.inAudioChannel()) {
                textChannel.sendMessage("I need to be in a voice channel for this to work").queue();
                return ;
            }
            if (!memberVoiceState.inAudioChannel()) {
                textChannel.sendMessage("You need to be in a voice channel for this command to work").queue();
                return ;
            }
            if (!memberVoiceState.getChannel().equals(botVoiceState.getChannel())) {
                textChannel.sendMessage("You need to be in the same voice channel as me for this to work").queue();
                return ;
            }
            final GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(event.getGuild());
            final AudioPlayer audioPlayer = musicManager.audioPlayer;
            final AudioTrack audioTrack = audioPlayer.getPlayingTrack();

            if (audioTrack == null) {
                textChannel.sendMessage("There is no track playing currently").queue();
                return;
            }

            final AudioTrackInfo info = audioTrack.getInfo();

            textChannel.sendMessageFormat("Now playing `%s` by `%s` (Link: <%s>)", info.title, info.author, info.uri).queue();
        }


    }
}

