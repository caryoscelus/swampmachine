package net.kiberion.audio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

import com.badlogic.gdx.audio.Music;

public class AudioService {

    private static final Logger log = LogManager.getLogger();
    
    private Mixer mixer;
    
    @Autowired
    private SoundRegistry soundRegistry;
    
    @Autowired
    public void setMixer(Mixer mixer) {
        this.mixer = mixer;
    }
    
    @EventListener
    public void playSound (PlaySoundEvent event) {
        log.info("Play sound");
        mixer.getChannel("sound").playSound(soundRegistry.getSoundEntries().get(event.getSoundId()));
    }
    
    @EventListener
    public void playMusic (PlayMusicEvent event) {
        log.info("Play music");
        Channel channel = mixer.getChannel(event.getChannelId());
        Music music = soundRegistry.getMusicEntries().get(event.getMusicId());
        channel.playMusic(music);
    }
    
    @EventListener
    public void channelControl(ChannelControlEvent e) {
        log.info("Channel control");
        Channel channel = mixer.getChannel(e.getChannelId());
        channel.fade(e.getDuration(), e.getTargetVolume());
    }

}
