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
    // set player manager to be singleton only one instance, bc a bot only have one player
    private static PlayerManager INSTANCE;
    // maps guild id and guild music manager
    private final Map<Long,GuildMusicManager> musicManagers;
    private final AudioPlayerManager audioPlayerManager;

    //contracture
    public PlayerManager()
    { // assigned music manager & audio play manager
        this.musicManagers= new HashMap<>();
        this.audioPlayerManager= new DefaultAudioPlayerManager();
        // configure it use remote & local source
        AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
        AudioSourceManagers.registerLocalSource(this.audioPlayerManager);

    }
    // instance is not assigned, only assigned when we need it
    public static PlayerManager getINSTANCE()
    {
        if(INSTANCE == null)
        {
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }
    // get music manager form a guild
    public GuildMusicManager getMusicManager(Guild guild)
    {
        //  is going to be a lavaPlayer expression
        return this.musicManagers.computeIfAbsent(guild.getIdLong(),(guildId)->{
        final GuildMusicManager guildMusicManager= new GuildMusicManager(this.audioPlayerManager);
        guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());
        return guildMusicManager;

        });
    }
    /**
     * Loading audio tracks
     * @param textChannel TextChannel, user input command channel
     * @param trackURL String, user input command content, url or keywords
     * @param playskip boolean, check if is play skip command
     */
    public void loadAndPlay(TextChannel textChannel, String trackURL, boolean playskip)
    {
        // get music manager
       final GuildMusicManager musicManager=this.getMusicManager(textChannel.getGuild());
        this.audioPlayerManager.loadItemOrdered(musicManager, trackURL, new AudioLoadResultHandler()
        {
            @Override
            //if user pasted an url - single track
            public void trackLoaded(AudioTrack audioTrack)
            {
                if(playskip)
                {
                    musicManager.scheduler.queue.addFirst(audioTrack);
                    textChannel.sendMessage("play skip to: ")
                            .addContent("`"+audioTrack.getInfo().title+"`"+" by "+audioTrack.getInfo().author).queue();
                    musicManager.scheduler.nextTrack();
                }
             else
                {
                    musicManager.scheduler.queue(audioTrack);
                    textChannel.sendMessage("Adding to queue: **")
                        .addContent(audioTrack.getInfo().title+"** by ** "+audioTrack.getInfo().author)
                        .addContent("'**").queue();

                }
            }
            @Override
            // if user is search by keyword - multiple tracks
            // getting first audio source of all the tracks that user searched
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
                            .addContent(tracks.get(0).getInfo().title +"** by ** "+ tracks.get(0).getInfo().author)
                            .addContent("**").queue();
                    //.addContent(String.valueOf(tracks.size())+ audioPlaylist.getName())
                }
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
