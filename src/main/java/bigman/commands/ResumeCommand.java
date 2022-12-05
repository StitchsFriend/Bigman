package bigman.commands;

import bigman.JDACommands.ExecuteArgs;
import bigman.JDACommands.ICommand;
import bigman.music.GuildMusicManager;
import bigman.music.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ResumeCommand implements ICommand {
    public static final Emoji Pause = Emoji.fromUnicode("U+23F8");
    @Override
    public void execute(ExecuteArgs event) {
        //check bot voice state
        if (!event.getSelfVoiceState().inAudioChannel()) {
            event.getTextChannel().sendMessage("I need to be in a voice channel for this to work").queue();
            return;
        }
        //check user voice state
        if (!event.getMemberVoiceState().inAudioChannel()) {
            event.getTextChannel().sendMessage("You need to be in a voice channel for this command to work").queue();
            return;
        }
        // check bot and user in same voice channel
        if (!event.getMemberVoiceState().getChannel().equals(event.getSelfVoiceState().getChannel())) {
            event.getTextChannel().sendMessage("You need to be in the same voice channel as me for this to work").queue();
            return;
        }

        final GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(event.getGuild());
        musicManager.scheduler.audioPlayer.setPaused(false);
        event.getTextChannel().sendMessage("resumed success!").queue();
    }

    @Override
    public String getName() {
        return "resume";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}