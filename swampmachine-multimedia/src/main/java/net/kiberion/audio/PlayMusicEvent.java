package net.kiberion.audio;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@SuppressWarnings("serial")
public class PlayMusicEvent extends ApplicationEvent{

    @Getter
    private final String musicId;
    @Getter
    private final String channelId;
    
    public PlayMusicEvent(String musicId, String channelId, Object source) {
        super(source);
        this.musicId = musicId;
        this.channelId = channelId;
    }

}
