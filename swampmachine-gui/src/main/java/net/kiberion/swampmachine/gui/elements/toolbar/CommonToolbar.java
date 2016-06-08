package net.kiberion.swampmachine.gui.elements.toolbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.kiberion.entities.common.api.ParametrizedRecalculable;
import net.kiberion.swampmachine.entityblocks.api.EntityProvider;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.gui.view.AbstractStateView;
import net.kiberion.swampmachine.utils.MapUtils;

public class CommonToolbar<T extends MetadataHolderBlock, M extends EntityProvider<T>> extends AbstractStateView<M>
        implements ParametrizedRecalculable<T> {

    @Autowired
    private M model;

    private final Map<String, CommonToolbarCell<T>> cellMap = new LinkedHashMap<>();
    private final Table cellTable = new Table();

    protected CommonToolbarCell<T> initCell(T entity) {
        return new CommonToolbarCell<>(entity);
    }

    protected void addEntity(T entity) {
        CommonToolbarCell<T> cell = initCell(entity);
        cellMap.put(entity.getId(), cell);
        cellTable.addActor(cell);

        cell.setX(300 + (100 * cellMap.size()));
        cell.setY(10);
    }

    protected void initialFill() {
        for (T entity : model.getAllEntities()) {
            addEntity(entity);
        }
    }

    @Override
    public void update() {
        Collection<CommonToolbarCell<T>> removedCells = new ArrayList<>(cellMap.values());
        for (T entity : model.getAllEntities()) {
            CommonToolbarCell<T> cell = cellMap.get(entity.getId());
            if (internalUpdate(entity)) {
                removedCells.remove(cell);
            }
        }
        MapUtils.removeAll(cellMap, removedCells);
        super.update();
    }

    @Override
    public void update(T updatedEntity) {
        internalUpdate(null);
    }

    // returns true if entity existed
    protected boolean internalUpdate(T updatedEntity) {
        CommonToolbarCell<T> cell = cellMap.get(updatedEntity.getId());
        if (cell != null) {
            cell.update(updatedEntity);
            return true;
        } else {
            addEntity(updatedEntity);
            return false;
        }
    }

}
