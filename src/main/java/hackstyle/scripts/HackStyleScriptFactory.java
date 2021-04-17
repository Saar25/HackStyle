package hackstyle.scripts;

import hackstyle.scripts.exceptions.ScriptParsingException;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;

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

        final Reflections reflections = new Reflections(script, new FieldAnnotationsScanner());
        final Set<Field> fields = reflections.getFieldsAnnotatedWith(ScriptParameter.class);

        for (Field field : fields) {
            try {
                if (field.getName().equals("title")) {
                    field.setAccessible(true);
                    field.set(script, settings.title);
                }
                if (field.getName().equals("indicator")) {
                    field.setAccessible(true);
                    field.set(script, settings.indicator);
                }
                if (field.getName().equals("delay")) {
                    field.setAccessible(true);
                    field.set(script, settings.delay);
                }
                if (field.getName().equals("text")) {
                    field.setAccessible(true);
                    field.set(script, settings.text);
                }
            } catch (IllegalAccessException e) {
                throw new ScriptParsingException(e);
            }
        }

        return script;
    }

}
