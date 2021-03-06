package net.kiberion.swampmachine.blueprints.common.state;

import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.gui.view.StateView;
import net.kiberion.swampmachine.mvcips.input.GenericInputAdapter;
import net.kiberion.swampmachine.mvcips.states.GameState;
import net.kiberion.swampmachine.mvcips.states.annotations.State;

@State (id = CommonMainMenuState.MAIN_MENU_STATE_ID)
public class CommonMainMenuState extends GameState{

    public static final String MAIN_MENU_STATE_ID = "mainmenu";
    
    @Autowired
    private CommonMainMenuView view;
    
    public CommonMainMenuState() {
        this.setInput(new GenericInputAdapter());
    }
    
    @Override
    public StateView getView() {
        return view;
    }

}
