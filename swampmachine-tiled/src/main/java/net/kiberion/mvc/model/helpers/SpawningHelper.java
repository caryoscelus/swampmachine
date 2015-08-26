package net.kiberion.mvc.model.helpers;

import java.util.List;

import com.google.inject.Singleton;

import net.kiberion.entities.map.api.Position;
import net.kiberion.entities.map.impl.PositionAspect;
import net.kiberion.tiled.aspects.api.CollidableAspect;
import net.kiberion.tiled.model.TiledMapInfo;
import net.kiberion.utils.Dice;

@Singleton
public class SpawningHelper {

    /**
     * Returns position on map that is not a wall and not occupied by a creature
     * @return
     */
    public Position getFreePosition (TiledMapInfo mapInfo, List<? extends CollidableAspect> collidableObjects) {
        Position result = new PositionAspect();
        
        int x = Dice.getRandomValue(0, mapInfo.getMapWidth());
        int y = Dice.getRandomValue(0, mapInfo.getMapHeight());
        
        return result;
    }
    
}
