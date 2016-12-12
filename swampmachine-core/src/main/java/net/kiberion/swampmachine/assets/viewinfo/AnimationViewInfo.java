package net.kiberion.swampmachine.assets.viewinfo;

import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

/**
 * @author kibertoad
 */
public class AnimationViewInfo extends CommonModelEntityDescriptor {
    
    // CHECK THE PATH
    private final String PATH_PREFIX = "";
    private final String PATH_SUFFIX = ".jpg";

    /**
     * columns are not columns, but total frame count
     */
    public int columns;

    public int rows; // IGNORED

    public float frameDuration;
    public Animation animation;

    @Override
    public void setImage(String id) {
        animation = getAnimation(id, columns, frameDuration);
    }

    public Animation getAnimation(String image, int frameCount, float frameDuration) {
        TextureRegion[] frames = new TextureRegion[frameCount];

        for (int i = 0; i < frameCount; ++i) {
            frames[i] =
                new TextureRegion(
                    new Texture(
                        Gdx.files.internal(
                            PATH_PREFIX+"/"+image+String.format("%04d", i)+PATH_SUFFIX
                        )
                    )
                );
        }

        Animation animation = new Animation(frameDuration, frames);
        return animation;
    }

}
