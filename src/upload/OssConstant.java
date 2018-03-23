package upload;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum OssConstant {
    code,key,server,downloadURL;

    private String value;

    public String getValue(){
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    static {
        init();
    }

    public static void init() {
        InputStream inputStream = OssConstant.class.getClassLoader().getResourceAsStream("fileupload.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            code.setValue(properties.getProperty("oss.code"));
            key.setValue(properties.getProperty("oss.key"));
            server.setValue(properties.getProperty("oss.serverUrl"));
            downloadURL.setValue(properties.getProperty("oss.downloadUrl"));
        } catch (IOException e) {
            Logger.getLogger(OssConstant.class).error("load fileupload.properties failed", e);
            e.printStackTrace();
        }
    }



}
