package net.kiberion.swampmachine.blueprints.common.state;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.gui.elements.SwampTextButton;
import net.kiberion.swampmachine.gui.managers.GuiManager.PositionCode;
import net.kiberion.swampmachine.gui.view.AbstractStateView;
import net.kiberion.swampmachine.mvcips.states.util.StateRegistry;

public class CommonMainMenuView extends AbstractStateView<Object> {

    @Autowired
    private StateRegistry stateRegistry;

    @Override
    public void initGUIElements() {
        super.initGUIElements();

        Table table = getGuiManager().addTable(PositionCode.CENTER);
        SwampTextButton<MetadataHolderBlock> button = new SwampTextButton<>("New game");
        button.addInvokable(getInvokablesFactory().createStateChangeInvokable(stateRegistry.getNewGameState().getId()));

        table.add(button);
    }

    // Do not autowire
    @Override
    public void setModel(Object model) {
        super.setModel(model);
    }
}
