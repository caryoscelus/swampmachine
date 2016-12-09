package net.kiberion.audio;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@SuppressWarnings("serial")
public class ChannelControlEvent extends ApplicationEvent {

    @Getter
    private final String channelId;
    @Getter
    private final float duration;
    @Getter
    private final float targetVolume;

    public ChannelControlEvent(String channelId, float duration, float targetVolume, Object source) {
        super(source);
        this.channelId = channelId;
        this.duration = duration;
        this.targetVolume = targetVolume;
    }

}
