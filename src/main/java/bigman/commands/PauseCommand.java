package bigman.commands;

import bigman.music.GuildMusicManager;
import bigman.music.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PauseCommand extends ListenerAdapter {
    public String botPrefix = "b!g ";
    public static final Emoji Pause = Emoji.fromUnicode("U+23F8");
    public void onMessageReceived(MessageReceivedEvent event) {
        TextChannel textChannel = event.getChannel().asTextChannel();
        GuildVoiceState botVoiceState = event.getGuild().getSelfMember().getVoiceState();
        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();

        Message message = event.getMessage();

        if (message.getContentRaw().startsWith(botPrefix + "pause"))
        {
            //check bot voice state
            if (!botVoiceState.inAudioChannel()) {
                textChannel.sendMessage("I need to be in a voice channel for this to work").queue();
                return ;
            }
            //check user voice state
            if (!memberVoiceState.inAudioChannel()) {
                textChannel.sendMessage("You need to be in a voice channel for this command to work").queue();
                return ;
            }
            // check bot and user in same voice channel
            if (!memberVoiceState.getChannel().equals(botVoiceState.getChannel())) {
                textChannel.sendMessage("You need to be in the same voice channel as me for this to work").queue();
                return;
            }

            final GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(event.getGuild());
            musicManager.scheduler.audioPlayer.setPaused(true);
            textChannel.sendMessage("Successfully paused").queue();

        }
    }
}
