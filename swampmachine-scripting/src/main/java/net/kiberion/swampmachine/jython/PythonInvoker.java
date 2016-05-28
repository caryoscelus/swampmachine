package net.kiberion.swampmachine.jython;

import java.io.Reader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.python.core.PyCode;
import org.python.google.common.collect.ImmutableSet;
import org.python.util.PythonInterpreter;

import lombok.Getter;
import net.kiberion.swampmachine.scripting.AbstractScriptParser;

/**
 * 
 * @author kibertoad
 *
 */
public class PythonInvoker extends AbstractScriptParser<PythonScript> {

    private static final Logger log = LogManager.getLogger();
    private boolean isInitted;

    private Set<String> extensions = ImmutableSet.of("py");

    @Getter
    private List<PythonScript> compiledScripts = new ArrayList<>();

    @Override
    protected PythonScript parseScript(Reader reader) {
        try (PythonInterpreter interp = new PythonInterpreter()) {
            PyCode compiledCode = interp.compile(reader);
            PythonScript activeScript = new PythonScript(compiledCode);
            compiledScripts.add(activeScript);
            return activeScript;
        }
    }

    @Override
    protected PythonScript parseScript(String script) {
        try (PythonInterpreter interp = new PythonInterpreter()) {
            PyCode compiledCode = interp.compile(script);
            PythonScript activeScript = new PythonScript(compiledCode);
            compiledScripts.add(activeScript);
            return activeScript;
        }
    }

    public void init() {
        PythonInitter.init();
        isInitted = true;
    }
    
    @Override
    public List<PythonScript> parseScriptsFromPath(Path directory) {
        if (!isInitted) {
            init();
        }
        return super.parseScriptsFromPath(directory);
    }

    @Override
    protected Set<String> getScriptExtensions() {
        return extensions;
    }

}
