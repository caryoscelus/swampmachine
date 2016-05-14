package net.kiberion.swampmachine.loaders;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kiberion.swampmachine.assets.loaders.api.POJOLoader;
import net.kiberion.swampmachine.assets.loaders.api.SyncAssetLoader;
import net.kiberion.swampmachine.assets.util.LoadOnStartup;
import net.kiberion.swampmachine.entities.modelinfo.CreatureModelInfo;
import net.kiberion.swampmachine.registries.CommonModelInfoRegistry;
import net.kiberion.swampmachine.utils.MapUtils;

@LoadOnStartup
@Component
public class CommonModelInfoLoader extends AbstractLoader implements SyncAssetLoader {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private CommonModelInfoRegistry modelInfoRegistry;

    
    @Override
    public int getPriority() {
        return 50;
    }

    protected void loadCreatureModelInfo() {

        try {
            if (fileExists("model-creature/")) {
                log.info("Loading creatures from: "+getPathToAssets().resolve("model-creature/").toString());
                
                POJOLoader<CreatureModelInfo> creatureLoader = new POJOLoader<>(
                        getPathToAssets().resolve("model-creature/"), CreatureModelInfo.class, "creatures");
                List<CreatureModelInfo> creatures = creatureLoader.loadList();

                MapUtils.putAll(modelInfoRegistry.getCreatures(), creatures);
                log.info("Done loading stuff.");
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error while loading: ", e);
        }
    }

    @Override
    public void load() {
        loadCreatureModelInfo();
    }

}