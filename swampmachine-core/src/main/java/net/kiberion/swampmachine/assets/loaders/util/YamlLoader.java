/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.swampmachine.assets.loaders.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.kiberion.swampmachine.assets.AssetProvider;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.assets.readers.AbstractFileReader;
import net.kiberion.swampmachine.assets.readers.GDXFileReader;
import net.kiberion.swampmachine.assets.readers.SimpleFileReader;
import net.kiberion.swampmachine.utils.MapUtils;

/**
 * @author kibertoad
 */
public class YamlLoader {

    private static final Logger log = LogManager.getLogger();

    public Yaml yaml = new Yaml();
    public Iterable<Object> dataYamls;
    public Map<String, Object> asMap;
    public List<Object> list;

    private AbstractFileReader fileReader;

    public YamlLoader() {
        if (Gdx.app != null) {
            fileReader = new GDXFileReader(AssetProvider.getPathToAssets());
        } else {
            fileReader = new SimpleFileReader(AssetProvider.getPathToAssets());
        }
    }

    public void openFile(String name) {
        openFile(Paths.get(name));
    }

    public void openFile(Path name) {
        try (InputStream is = fileReader.getFileAsStream(name)){
            dataYamls = yaml.loadAll(is);
        } catch (IOException e) {
            log.error("IO exception: ", e);
        }
    }

    public Integer getInteger(String byKey) {
        return (Integer) asMap.get(byKey);
    }


    //This entity is somewhat too specific to be loaded from a general purpose loader. Consider using some pluggable delegation classes instead
    @Deprecated
    public Animation getAnimation(String image, int FRAME_COLS, int FRAME_ROWS, float frameDuration) {
        TextureAtlas.AtlasRegion atlasRegion = getImage("image");

        TextureRegion[][] tmp = atlasRegion.split(atlasRegion.getRegionWidth() / FRAME_COLS,
                atlasRegion.getRegionHeight() / FRAME_ROWS);

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        Animation walkAnimation = new Animation(frameDuration, walkFrames);

        return walkAnimation;
    }

    //This entity is somewhat too specific to be loaded from a general purpose loader. Consider using some pluggable delegation classes instead
    @Deprecated
    public TextureAtlas.AtlasRegion getImage(String byKey) {
        String imageName = getString(byKey);

        if (imageName != null && imageName != "BLANK") {
            TextureAtlas.AtlasRegion region = UiManager.instance().atlas().findRegion(imageName);

            if (region == null) {
                log.warn("No image in atlas: " + imageName);
            }

            return region;
        } else {
            return null;
        }
    }

    public boolean hasKey(String byKey) {
        return asMap.get(byKey) != null;
    }

    public Float getFloat(String byKey) {
        Double d = (Double) asMap.get(byKey);
        return d.floatValue();
    }

    public boolean getBoolean(String byKey) {
        return (Boolean) asMap.get(byKey);
    }

    public String getString(String byKey) {
        return (String) asMap.get(byKey);
    }

    @SuppressWarnings("unchecked")
    public List<Object> getList(String byKey) {
        list = (List<Object>) asMap.get(byKey);
        return list;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getMap(String byKey) {
        return (Map<String, Object>) asMap.get(byKey);
    }

    /**
     * Set the node to be used by get* methods
     */
    @SuppressWarnings("unchecked")
    public void setNextYamlNode(Object o) {
        if (o == null) {
            log.error("Null node for parsing!");
        }
        asMap = (Map<String, Object>) o;
    }

    public List<Integer> getIntegerListSafe(String key) {
        return MapUtils.getIntegerListSafe(asMap, key);
    }
}
