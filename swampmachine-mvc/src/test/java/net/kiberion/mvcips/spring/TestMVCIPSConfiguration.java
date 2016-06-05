package net.kiberion.mvcips.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.kiberion.swampmachine.mvcips.spring.CommonMVCIPSConfiguration;
import net.kiberion.swampmachine.mvcips.states.GameState;
import net.kiberion.swampmachine.mvcips.states.annotations.LoadingState;
import net.kiberion.swampmachine.mvcips.states.annotations.NewGameState;
import net.kiberion.swampmachine.mvcips.states.annotations.StartingState;
import net.kiberion.swampmachine.mvcips.states.annotations.State;
import net.kiberion.swampmachine.mvcips.view.StateView;

@Configuration
@Import({ CommonMVCIPSConfiguration.class })
public class TestMVCIPSConfiguration {

    public static final String LOADING_STATE_ID = "loading";
    public static final String STARTING_STATE_ID = "starting";
    public static final String NEWGAME_STATE_ID = "newgame";
    public static final String FOURTH_STATE_ID = "4";

    @Bean
    public GameState state1() {
        return new TestLoadingState();
    }

    @Bean
    public GameState state2() {
        return new TestStartingState();
    }

    @Bean
    public GameState state3() {
        return new TestNewGameState();
    }

    @Bean
    public GameState state4() {
        return new ThirdState();
    }
    
    ///////////////
    // Test states//
    ///////////////

    @State(id = FOURTH_STATE_ID)
    public static class ThirdState extends GameState {

        public ThirdState() {
            super (null);
        }
        
        @Override
        public StateView getView() {
            return null;
        }

    }

    @LoadingState
    @State(id = LOADING_STATE_ID)
    public static class TestLoadingState extends GameState {

        public TestLoadingState() {
            super (null);
        }

        @Override
        public StateView getView() {
            return null;
        }

    }

    @StartingState
    @State(id = STARTING_STATE_ID)
    public static class TestStartingState extends GameState {

        public TestStartingState() {
            super (null);
        }
        
        @Override
        public StateView getView() {
            return null;
        }

    }

    @NewGameState
    @State(id = NEWGAME_STATE_ID)
    public static class TestNewGameState extends GameState {

        public TestNewGameState() {
            super (null);
        }
        
        @Override
        public StateView getView() {
            return null;
        }

    }
    
}
