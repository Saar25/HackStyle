package hackstyle.scripts.parsing;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public final class HackStyleSettingsReader {

    private HackStyleSettingsReader() {
        throw new AssertionError("Cannot create instance of " + getClass().getSimpleName());
    }

    public static HackStyleSettings read(String path) throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(HackStyleSettings.class);
        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (HackStyleSettings) jaxbUnmarshaller.unmarshal(new File(path));
    }

}
