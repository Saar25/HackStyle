package hackstyle.scripts;

import hackstyle.scripts.parsing.HackStyleSettings;
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

    private static final Map<String, Supplier<HackStyleScript>> SCRIPTS_MAP = buildScriptMap();

    private static Map<String, Supplier<HackStyleScript>> buildScriptMap() {
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

    private static <T extends HackStyleScript> T constructScript(Constructor<T> constructor) {
        try {
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HackStyleScript createScript(HackStyleSettings.Script settings) {
        final HackStyleScript script = SCRIPTS_MAP.get(settings.name).get();

        final Reflections reflections = new Reflections(script);
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
                if (field.getName().equals("speed")) {
                    field.setAccessible(true);
                    field.set(script, settings.speed);
                }
                if (field.getName().equals("text")) {
                    field.setAccessible(true);
                    field.set(script, settings.text);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return script;
    }

}
