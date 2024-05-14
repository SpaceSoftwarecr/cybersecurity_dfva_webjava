package dfva_webjava.java.demo.data;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentSign {
 
    private final Map<String, String> properties ;

    public DocumentSign() {
        this.properties = new HashMap<>();
    }

    @JsonAnySetter
    public void addProperty(String key, String value) {
        properties.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }
}
