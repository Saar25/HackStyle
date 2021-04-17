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

        @XmlElement(name = "name", required = true)
        public String name;

        @XmlElement(name = "runner", required = true)
        public String runner;

        @XmlElement(name = "title", required = true)
        public String title;

        @XmlElement(name = "indicator", required = true)
        public String indicator;

        @XmlElement(name = "text")
        public String text;

        @XmlElement(name = "speed")
        public String speed;

        @XmlElement(name = "code")
        public String code;

        @Override
        public String toString() {
            return "Script{" +
                    "name='" + name + '\'' +
                    ", title='" + title + '\'' +
                    ", indicator='" + indicator + '\'' +
                    ", text='" + text + '\'' +
                    ", speed='" + speed + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }
    }

}
