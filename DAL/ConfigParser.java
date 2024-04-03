package DAL;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum ConfigParser {
    
    INSTANCE;
    private InputStream inputStream;
    private Properties prop;
    
    ConfigParser() {
        try {
            prop = new Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new ExceptionInInitializerError(e.getMessage());
            }
        }
    }

    public String getProperty(String propertyName) {
        return prop.getProperty(propertyName);
    }
}
