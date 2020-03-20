package hackstyle.scripts.parsing;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "hackstyle")
@XmlAccessorType(XmlAccessType.FIELD)
public class HackStyleSettings {

    @XmlElementWrapper(name = "values")
    @XmlElement(name = "value")
    public List<Value> values;

    @XmlElementWrapper(name = "scripts")
    @XmlElement(name = "script")
    public List<Script> scripts;

    @Override
    public String toString() {
        return "HackStyleFile{" +
                "\n\tvalues=" + values +
                ", \n\tscripts=" + scripts +
                "\n}";
    }

    @XmlRootElement(name = "value")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Value {

        @XmlElement(name = "name")
        private String name;

        @XmlElement(name = "content")
        private String content;

        @Override
        public String toString() {
            return "Value{" +
                    "name='" + name + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    @XmlRootElement(name = "script")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Script {

        @XmlElement(name = "name")
        private String name;

        @XmlElement(name = "indicator")
        private String indicator;

        @XmlElement(name = "code")
        private String code;

        @Override
        public String toString() {
            return "Script{" +
                    "name='" + name + '\'' +
                    ", indicator='" + indicator + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }
    }

}
