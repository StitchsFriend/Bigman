package bigman.commands;

import bigman.music.GuildMusicManager;
import bigman.music.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SkipCommand extends ListenerAdapter {
    public String botPrefix = "b!g ";

    public void onMessageReceived(MessageReceivedEvent event)
    {
        TextChannel textChannel = event.getChannel().asTextChannel();
        GuildVoiceState botVoiceState = event.getGuild().getSelfMember().getVoiceState();
        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();

        Message message= event.getMessage();

        if (message.getContentRaw().startsWith(botPrefix + "skip"))
        {
            if (!botVoiceState.inAudioChannel()) {
                textChannel.sendMessage("I need to be in a voice channel for this to work").queue();

            }
            if (!memberVoiceState.inAudioChannel()) {
                textChannel.sendMessage("You need to be in a voice channel for this command to work").queue();

            }
            if (!memberVoiceState.getChannel().equals(botVoiceState.getChannel())) {
                textChannel.sendMessage("You need to be in the same voice channel as me for this to work").queue();

            }
            final GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(event.getGuild());
            final AudioPlayer audioPlayer = musicManager.audioPlayer;
            musicManager.scheduler.nextTrack();
            textChannel.sendMessage("Skipped the current track").queue();

            if(audioPlayer.getPlayingTrack()==null)
            {
                textChannel.sendMessage("There is no track playing currently").queue();

            }
        }

    }
}
