package net.kiberion.swampmachine.loaders;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.assets.GameConfig;
import net.kiberion.swampmachine.assets.loaders.api.SyncLoader;
import net.kiberion.swampmachine.assets.loaders.impl.POJOYamlLoader;
import net.kiberion.swampmachine.assets.readers.ReaderHelper;
import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;
import net.kiberion.swampmachine.utils.MapUtils;

/**
 * 
 * 
 * @author kibertoad
 *
 */
public abstract class AbstractLoader implements SyncLoader {

    private static final Logger log = LogManager.getLogger();

    @Setter
    @Getter
    private int priority = 100;

    @Autowired
    @Getter
    private final ReaderHelper readerHelper;

    @Autowired
    private GameConfig config;

    public AbstractLoader() {
        readerHelper = new ReaderHelper();
    }

    protected boolean fileExists(String directoryName) {
        return readerHelper.fileExists(directoryName);
    }

    public abstract <T extends EntityModelDescriptor> Map<String, T> getTargetMap();

    public abstract String getLoadDirectory();

    public abstract String getLoadFileExtension();

    public abstract Class<? extends EntityModelDescriptor> getEntityClass();

    @Override
    public void load() {
        loadDataNodes(getTargetMap(), getLoadDirectory(), getLoadFileExtension(), getEntityClass());
    }

    protected Path getEntityDirectory(String loadDirectory) {
        return config.getPathToResources().resolve(loadDirectory);
    }
    
    protected <T extends EntityModelDescriptor> void loadDataNodes(Map<String, T> targetMap, String loadDirectory,
            String loadExtension, Class<T> clazz) {
        try {
            if (fileExists(loadDirectory)) {
                Path entityDirectory = getEntityDirectory(loadDirectory);
                log.info("Loading entities from: " + entityDirectory.toString());
                POJOYamlLoader<T> entityLoader = new POJOYamlLoader<>(entityDirectory, clazz, loadExtension);
                List<T> entities = entityLoader.loadList();

                MapUtils.putAllEntities(targetMap, entities);
                log.info("Loaded " + entities.size() + " " + getEntityClass().getSimpleName() + " entities.");
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error while loading: ", e);
        }
    }
    
}
