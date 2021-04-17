package hackstyle.scripts;

import hackstyle.scripts.exceptions.ScriptParsingException;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class HackStyleScriptFactory {

    private static final String SCRIPTS_PACKAGE = "hackstyle.scripts";

    private final Map<String, Supplier<HackStyleScript>> SCRIPTS_MAP = buildScriptMap();

    private Map<String, Supplier<HackStyleScript>> buildScriptMap() {
        final Map<String, Supplier<HackStyleScript>> scriptsMap = new HashMap<>();

        final Reflections reflections = new Reflections(HackStyleScriptFactory.SCRIPTS_PACKAGE);
        final Set<Class<? extends HackStyleScript>> scriptClasses = reflections.getSubTypesOf(HackStyleScript.class);

        for (Class<? extends HackStyleScript> scriptClass : scriptClasses) {
            final Script scriptAnnotation = scriptClass.getAnnotation(Script.class);

            try {
                final Constructor<? extends HackStyleScript> constructor =
                        scriptClass.getDeclaredConstructor();

                final HackStyleScript script = constructScript(constructor);

                if (script != null) {
                    scriptsMap.put(scriptAnnotation.name(), () -> script);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return scriptsMap;
    }

    private <T extends HackStyleScript> T constructScript(Constructor<T> constructor) {
        try {
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public HackStyleScript createScript(HackStyleSettings.Script settings) throws ScriptParsingException {
        final Supplier<HackStyleScript> supplier = SCRIPTS_MAP.get(settings.runner);
        final HackStyleScript script = supplier != null ? supplier.get() : new NoopScript();

        for (Field f : script.getClass().getFields()) {
            final ScriptParameter annotation = f.getAnnotation(ScriptParameter.class);
            if (annotation != null) {
                try {
                    f.setAccessible(true);
                    switch (annotation.value()) {
                        case "title":
                            f.set(script, settings.title);
                            break;
                        case "indicator":
                            f.set(script, settings.indicator);
                            break;
                        case "delay":
                            f.set(script, settings.delay);
                            break;
                        case "text":
                            f.set(script, settings.text);
                            break;
                    }
                } catch (IllegalAccessException e) {
                    throw new ScriptParsingException(e);
                }
            }
        }

        return script;
    }

}
