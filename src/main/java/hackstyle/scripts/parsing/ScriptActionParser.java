package hackstyle.scripts.parsing;

import hackstyle.scripts.ScriptAction;
import hackstyle.scripts.ScriptActionSettings;
import hackstyle.scripts.VariableStream;
import hackstyle.scripts.exceptions.InvalidScriptActionException;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ScriptActionParser {

    private static final String SCRIPT_ACTIONS_PACKAGE = "hackstyle.scripts.actions";

    private final Map<String, ScriptAction.Creator> scriptActionCreators;

    public ScriptActionParser() {
        this.scriptActionCreators = getAllScriptActions();
    }

    private static Map<String, ScriptAction.Creator> getAllScriptActions() {
        final Map<String, ScriptAction.Creator> scriptActions = new HashMap<>();

        final Reflections reflections = new Reflections(SCRIPT_ACTIONS_PACKAGE);
        final Set<Class<? extends ScriptAction>> actions = reflections.getSubTypesOf(ScriptAction.class);

        try {
            for (Class<? extends ScriptAction> action : actions) {
                final ScriptActionSettings settings = action.getAnnotation(ScriptActionSettings.class);
                final Constructor<? extends ScriptAction> constructor =
                        action.getDeclaredConstructor(VariableStream.class);
                scriptActions.put(settings.keyword(), variables -> {
                    try {
                        return constructor.newInstance(variables);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return scriptActions;
    }

    public ScriptAction parse(String actionName, VariableStream variables) throws InvalidScriptActionException {
        final ScriptAction.Creator creator = scriptActionCreators.get(actionName);
        if (creator != null) {
            return creator.create(variables);
        }
        throw new InvalidScriptActionException("Cannot parse action: " + actionName);
    }

}
