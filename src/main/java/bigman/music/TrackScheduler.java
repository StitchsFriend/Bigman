package bigman.music;
// use Lavaplayer
import com.sedmelluq.discord.lavaplayer.player.*;
import com.sedmelluq.discord.lavaplayer.player.event.*;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.concurrent.*;

public class TrackScheduler extends AudioEventAdapter{

    public final AudioPlayer audioPlayer;
    public final LinkedBlockingDeque<AudioTrack> queue;
    public boolean repeating = false;
    private int count =0;

    //
    public TrackScheduler(AudioPlayer audioPlayer)
    {
        this.audioPlayer=audioPlayer;
        this.queue=new LinkedBlockingDeque<>();
    }
    /**
     * Adds a song to the end of the queue and starts playing it if no other song is queued.
     *
     * @param track AudioTrack to add to the queue
     */
    public void queue(AudioTrack track)
    {
        if(!this.audioPlayer.startTrack(track,true))
        {
            this.queue.offer(track);
        }
    }

    public void nextTrack()
    {
        this.audioPlayer.startTrack(this.queue.poll(),false);
        if(repeating){repeating = false;}
    }

// when the track is going to end, this method is going to get called from audio event adapter
    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason)
    {
        if(endReason.mayStartNext)
        {
            if(this.repeating)
            {
                this.audioPlayer.startTrack(track.makeClone(),false);
            }
            nextTrack();
        }
    }
}


