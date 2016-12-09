package net.kiberion.audio;

import java.util.Map;
import java.util.HashMap;

import net.kiberion.swampmachine.api.common.RealtimeUpdatable;

/**
 * Channel container
 * @author caryoscelus
 */
public class Mixer implements RealtimeUpdatable {
    /**
     * Returns a channel with a given name, possibly creating it.
     */
    public Channel getChannel(String name) {
        Channel channel = channels.get(name);
        if (channel == null) {
            channel = new Channel();
            channels.put(name, channel);
        }
        return channel;
    }
    
    @Override
    public void update(float delta) {
        for (Channel channel : channels.values()) {
            channel.update(delta);
        }
    }
    
    private Map<String, Channel> channels = new HashMap<>();
}
