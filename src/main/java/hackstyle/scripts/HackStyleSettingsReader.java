package hackstyle.scripts;

import hackstyle.ErrorMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(HackStyleSettings.class);
            final Marshaller marshaller = jaxbContext.createMarshaller();
            final File file = new File(path);

            final OutputStream out = new FileOutputStream(file);
            final DOMResult domResult = new DOMResult();
            marshaller.marshal(settings, domResult);

            final Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new DOMSource(domResult.getNode()), new StreamResult(out));
        } catch (IOException | TransformerException e) {
            ErrorMessage.createErrorFile(e);
        }
    }

}
