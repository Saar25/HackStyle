package hackstyle.scripts;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "hackstyle")
@XmlAccessorType(XmlAccessType.FIELD)
public class HackStyleSettings {

    @XmlElementWrapper(name = "values")
    @XmlElement(name = "value")
    public List<Value> values;

    @XmlElementWrapper(name = "hackstyle-scripts")
    @XmlElement(name = "hackstyle-script")
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

    @XmlRootElement(name = "hackstyle-script")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Script {

        @XmlElement(name = "runner", required = true)
        public String runner;

        @XmlElement(name = "title", required = true)
        public String title;

        @XmlElement(name = "indicator", required = true)
        public String indicator;

        @XmlElement(name = "text", defaultValue = "")
        public String text;

        @XmlElement(name = "delay", defaultValue = "")
        public String delay;
    }

}
