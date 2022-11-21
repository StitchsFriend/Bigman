package bigman.music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager {

    private static PlayerManager INSTANCE;
    private final Map<Long,GuildMusicManager> musicManagers;
    private final AudioPlayerManager audioPlayerManager;

    //constuature
    public PlayerManager()
    {
        this.musicManagers= new HashMap<>();
        this.audioPlayerManager= new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
        AudioSourceManagers.registerLocalSource(this.audioPlayerManager);

    }
    public static PlayerManager getINSTANCE()
    {
        if(INSTANCE == null)
        {
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }
    public GuildMusicManager getMusicManager(Guild guild)
    {
        //  is going to be a lavaPlayer expression
        return this.musicManagers.computeIfAbsent(guild.getIdLong(),(guildId)->{
        final GuildMusicManager guildMusicManager= new GuildMusicManager(this.audioPlayerManager);
        guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());
        return guildMusicManager;

        });
    }
    // bot get
    public  void loadAndPlay(TextChannel textChannel, String trackURL, boolean playskip)
    {
       final GuildMusicManager musicManager=this.getMusicManager(textChannel.getGuild());

        this.audioPlayerManager.loadItemOrdered(musicManager, trackURL, new AudioLoadResultHandler() {
            @Override
            //if user pasted a url
            public void trackLoaded(AudioTrack audioTrack)
            {
                if(playskip)
                {
                    musicManager.scheduler.queue.addFirst(audioTrack);
                    textChannel.sendMessage("play skip to: ")
                            .addContent("`"+audioTrack.getInfo().title+"`"+" by "+audioTrack.getInfo().author)
                            .queue();
                    musicManager.scheduler.nextTrack();
                }
             else
                {
                    musicManager.scheduler.queue(audioTrack);
                    textChannel.sendMessage("Adding to queue: **")
                        .addContent(audioTrack.getInfo().title)
                        .addContent("'** by ** '")
                        .addContent(audioTrack.getInfo().author)
                        .addContent("'**")
                        .queue();

                }
            }

            @Override
            // if user is search by keyword
            // getting first track of all the tracks that user searched
            public void playlistLoaded(AudioPlaylist audioPlaylist) {
                final List<AudioTrack> tracks = audioPlaylist.getTracks();
                if(playskip)
                {
                    musicManager.scheduler.queue.addFirst(tracks.get(0));
                    textChannel.sendMessage("play skip to: ")
                            .addContent("`"+tracks.get(0).getInfo().title+"`"+" by "+tracks.get(0).getInfo().author)
                            .queue();
                    musicManager.scheduler.nextTrack();
                }
                else
                {
                    musicManager.scheduler.queue(tracks.get(0));
                    textChannel.sendMessage("Adding to queue **")
                            .addContent(tracks.get(0).getInfo().title)
                            //.addContent(String.valueOf(tracks.size()))
                            .addContent("`by`")
                            .addContent(tracks.get(0).getInfo().author)
                            //.addContent(audioPlaylist.getName())
                            .addContent("**")
                            .queue();
                }
                    /*
                    for(final AudioTrack track : tracks)
                    {
                        musicManager.scheduler.queue(track);
                    }

                     */

            }

            @Override
            public void noMatches() {

            }

            @Override
            public void loadFailed(FriendlyException e) {

            }
        });
    }

}
