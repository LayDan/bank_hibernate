package application.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ValuteConfing {
    public String getGlobalValute() {

        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/valute.properties");
            property.load(fis);

            return property.getProperty("valute");

        } catch (IOException ignored) {

        }
        return null;
    }
}
