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
    public static class Value {

        @XmlElement(name = "name")
        public String name;

        @XmlElement(name = "content")
        public String content;

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
    public static class Script {

        @XmlElement(name = "name")
        public String name;

        @XmlElement(name = "indicator")
        public String indicator;

        @XmlElement(name = "code")
        public String code;

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
