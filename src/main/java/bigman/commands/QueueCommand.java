package bigman.commands;

import bigman.JDACommands.ExecuteArgs;
import bigman.JDACommands.ICommand;
import bigman.music.GuildMusicManager;
import bigman.music.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class QueueCommand  implements ICommand {
    @Override
    public void execute(ExecuteArgs event) {
            GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(event.getGuild());
            BlockingQueue<AudioTrack> queue = musicManager.scheduler.queue;
            EmbedBuilder embedBuilder = new EmbedBuilder();
            if (queue.isEmpty()) {
                event.getTextChannel().sendMessage("The queue is currently empty").queue();
                return;
            }
            //looking the smaller #of track in queue, if have 3 track in queue it will play 3, if 4o track in queue it will play 5.
            int trackCount = Math.min(queue.size(), 5);
            // conver queue to array list to get each track index
            List<AudioTrack> trackList = new ArrayList<>(queue);
            // keep appending this meassage  not need to creast a new builder later
            embedBuilder.setTitle("Current Queue:");

            for (int i = 0; i < trackCount; i++) {
                AudioTrack track = trackList.get(i);
                AudioTrackInfo info = track.getInfo();
                embedBuilder.appendDescription((i+1)+". " + " "+info.title+" "+formatTime(track.getDuration())+"\n");

            }
            if (trackList.size() > trackCount) {
                embedBuilder.appendDescription("And " + (trackList.size() -trackCount) + " more...");

            }
        event.getTextChannel().sendMessageEmbeds(embedBuilder.build()).queue();
        }

    public static String formatTime(long duration)
    {
        if(duration == Long.MAX_VALUE)
            return "LIVE";
        long seconds = Math.round(duration/1000.0);
        long hours = seconds/(60*60);
        seconds %= 60*60;
        long minutes = seconds/60;
        seconds %= 60;
        return (hours>0 ? hours+":" : "") + (minutes<10 ? "0"+minutes : minutes) + ":" + (seconds<10 ? "0"+seconds : seconds);
    }

    @Override
    public String getName() {
        return "queue";
    }
    @Override
    public boolean needOwner() {
        return false;
    }
}


