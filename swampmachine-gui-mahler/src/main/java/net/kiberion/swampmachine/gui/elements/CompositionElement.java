package net.kiberion.swampmachine.gui.elements;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.entityblocks.api.IdHolderBlock;

@JsonDeserialize(using = CompositionElementDeserializer.class)
public class CompositionElement implements IdHolderBlock{

    @Getter
    @Setter
    private String type;
    
    @Getter
    @Setter
    private String id;
    
    @Getter
    @Setter
    private Position position;
    
    @Getter
    private Map<String, Object> properties = new HashMap<>();
    
    @Getter
    private CompositionElement parent;

    @Override
    public String toString() {
        return type+": "+id;
    }
}
