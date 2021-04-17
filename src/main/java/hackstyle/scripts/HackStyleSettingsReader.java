package hackstyle.scripts;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;

public final class HackStyleSettingsReader {

    private HackStyleSettingsReader() {
        throw new AssertionError("Cannot create instance of " + getClass().getSimpleName());
    }

    public static HackStyleSettings read(String path) throws JAXBException, FileNotFoundException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(HackStyleSettings.class);
        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        final File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("Cannot file file " + path);
        }
        return (HackStyleSettings) jaxbUnmarshaller.unmarshal(file);
    }

}
