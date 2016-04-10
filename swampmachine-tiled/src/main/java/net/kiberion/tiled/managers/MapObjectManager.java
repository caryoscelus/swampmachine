package net.kiberion.tiled.managers;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;

import net.kiberion.aspects.api.MetadataHolderAspect;
import net.kiberion.aspects.api.PositionHolderAspect;
import net.kiberion.assets.UiManager;
import net.kiberion.entities.map.api.Position;
import net.kiberion.entities.map.impl.PositionAspect;
import net.kiberion.tiled.model.TiledMapInfo;

public abstract class MapObjectManager {

    public static final String OBJECT_LAYER_NAME = "objects";
    
	protected static final Logger log = LogManager.getLogger();
    protected Map<MetadataHolderAspect, TextureMapObject> textureMapObjectsMap = new HashMap<>();
    private TiledMapInfo mapInfo;
    

	public TiledMap getMap() {
		return mapInfo.getMap();
	}

	public void setMapInfo(TiledMapInfo map) {
		this.mapInfo = map;
	}

	protected TiledMapInfo getMapInfo() {
		return mapInfo;
	}    
	
	
	// Tiled "Object layer" named "objects" should be created for this to work
    public TextureMapObject addMapObject(MetadataHolderAspect entityModel, Position position, String imageCode) {
        return addMapObject(entityModel, position, UiManager.instance().getImage(imageCode));
    }
    
    public void removeMapObject (MetadataHolderAspect entityModel) {
        MapLayer objectLayer = getMap().getLayers().get(OBJECT_LAYER_NAME);
        TextureMapObject tmo = textureMapObjectsMap.get(entityModel);
        objectLayer.getObjects().remove(tmo);
        textureMapObjectsMap.remove(entityModel);
    }

    // Tiled "Object layer" named "objects" should be created for this to work
    /**
     * 
     * @param entityModel
     * @param position Model position
     * @param image
     */
    public TextureMapObject addMapObject(MetadataHolderAspect entityModel, Position position, TextureRegion image) {
        MapLayer objectLayer = getMap().getLayers().get(OBJECT_LAYER_NAME);

        TextureMapObject tmo = new TextureMapObject(image);
        Position screenPosition = getPositionForModelEntity (entityModel, position);
        tmo.setX(screenPosition.getX());
        tmo.setY(screenPosition.getY());
        objectLayer.getObjects().add(tmo);
        textureMapObjectsMap.put(entityModel, tmo);
        
        return tmo;
    }
    
    public Position getPositionForModelEntity (MetadataHolderAspect entityModel, Position position) {
        return position;
    }

    public void updateTextureMapObjectPosition(MetadataHolderAspect entityModel) {
    	updateTextureMapObjectPosition (entityModel, ((PositionHolderAspect)entityModel).getPositionAspect());
    }
    
    public void updateTextureMapObjectPosition(MetadataHolderAspect entityModel, PositionAspect position) {
    	TextureMapObject tmo = this.textureMapObjectsMap.get(entityModel);
        Position screenPosition = getPositionForModelEntity (entityModel, position);
        tmo.setX(screenPosition.getX());
        tmo.setY(screenPosition.getY());
        
        log.debug("New model position: "+position);
        log.debug(String.format("New image position: %.2f/%.2f", tmo.getX(), tmo.getY()));
        //log.debug(String.format("Camera position: %.2f/%.2f", getCamera().getOrthoCam().position.x, getCamera().getOrthoCam().position.y));
    }	
}
