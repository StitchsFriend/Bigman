package bigman.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager {
    public final AudioPlayer audioPlayer; // use play tracks
    public final TrackScheduler scheduler;//
    private final AudioPlayerSendHandler sendHandler;

    // create a new audio player, assign a track scheduler and sending handler
    // that we can use in our player manager
    public GuildMusicManager(AudioPlayerManager manager)
    {
        this.audioPlayer=manager.createPlayer();
        this.scheduler=new TrackScheduler(this.audioPlayer);
        this.audioPlayer.addListener(this.scheduler);
        this.sendHandler=new AudioPlayerSendHandler(this.audioPlayer);
    }
    public AudioPlayerSendHandler getSendHandler()
    {
        return this.sendHandler;

    }
}
