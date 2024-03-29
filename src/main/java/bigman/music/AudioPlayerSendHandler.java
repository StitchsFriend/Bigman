package bigman.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;
import java.nio.*;

public class AudioPlayerSendHandler implements AudioSendHandler {
    private final AudioPlayer audioPlayer;
    private  final ByteBuffer buffer;
    private final MutableAudioFrame frame;

    public AudioPlayerSendHandler(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
        this.buffer = ByteBuffer.allocate(1024);
        this.frame = new MutableAudioFrame();
        this.frame.setBuffer((buffer));
    }


    @Override
    public boolean canProvide() {
        return this.audioPlayer.provide(this.frame);
    }


    @Override
    public ByteBuffer provide20MsAudio() {
        //final Buffer buffer=((Buffer) this.buffer).flip();
        return this.buffer.flip();
    }

    @Override
    public boolean isOpus() {
        return true;
    }
}
