import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    private String propertiesLocation = "config";
    private static final String PROPERTIES_FILE = "app.properties";
    private Properties loadedProperties = new Properties();

    public void loadProperties() {
        try {
            String propertiesPath = propertiesLocation + "/" + PROPERTIES_FILE;
            FileInputStream propertiesStream = new FileInputStream(propertiesPath);
            loadedProperties.load(propertiesStream);
        } catch(IOException e) {
            // No properties files means all defaults are loaded
        }
    }
}
