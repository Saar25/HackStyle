package hackstyle.scripts;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class HackStyleSettingsReader {

    private HackStyleSettingsReader() {
        throw new AssertionError("Cannot create instance of " + getClass().getSimpleName());
    }

    public static HackStyleSettings read(String path, String defaultsFile) throws JAXBException, IOException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(HackStyleSettings.class);
        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        final File file = new File(path);
        if (!file.exists()) {
                try (final InputStream stream = HackStyleSettingsReader.class
                        .getResourceAsStream(defaultsFile)) {
                    Files.copy(stream, file.toPath());
                } catch (IOException e) {
                    throw new FileNotFoundException("Cannot open file " + path);
                }
        }
        return (HackStyleSettings) jaxbUnmarshaller.unmarshal(file);
    }

}
