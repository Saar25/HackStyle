package hackstyle.scripts;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
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

    public static void save(String path, HackStyleSettings settings) throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(HackStyleSettings.class);
        final Marshaller marshaller = jaxbContext.createMarshaller();

        final File file = new File(path);
        marshaller.marshal(settings, file);
    }

}
