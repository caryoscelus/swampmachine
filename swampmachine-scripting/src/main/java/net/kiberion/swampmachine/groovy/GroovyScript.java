package net.kiberion.swampmachine.groovy;

import java.io.Reader;
import java.util.HashMap;

import org.apache.commons.lang3.Validate;

import groovy.lang.GroovyShell;
import groovy.lang.Script;
import net.kiberion.swampmachine.scripting.SwampBinding;
import net.kiberion.swampmachine.scripting.SwampScript;
import net.kiberion.swampmachine.scripting.SwampScriptInvokationResult;

/**
 * This class is thread-safe
 * @author kibertoad
 *
 */
public class GroovyScript implements SwampScript {

    private Script compiledScript;

    public GroovyScript() {
    }

    public GroovyScript(String script) {
        GroovyShell shell = new GroovyShell();
        compiledScript = shell.parse(script);
    }

    public GroovyScript(Reader script) {
        GroovyShell shell = new GroovyShell();
        compiledScript = shell.parse(script);
    }

    @Override
    public SwampScriptInvokationResult invoke(SwampBinding binding) {
        GroovyInvokationResult result = new GroovyInvokationResult();
        Validate.notNull(binding, "Binding cannot be null.");
        try {
            Script invokableScript = compiledScript.getClass().newInstance();
            invokableScript.setBinding((GroovyBinding) binding);
            invokableScript.run();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        result.setVariables(new HashMap<>(binding.getVariableMap()));

        return result;
    }

}
