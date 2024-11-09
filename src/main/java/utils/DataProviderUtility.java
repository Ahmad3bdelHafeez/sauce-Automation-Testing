package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.util.Map;

public class DataProviderUtility {
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String>[] data = mapper.readValue(new File("src/test/resources/data/loginData.json"), Map[].class);
        Object[][] dataArray = new Object[data.length][1];
        for (int i = 0; i < data.length; i++) {
            dataArray[i][0] = data[i];
        }
        return dataArray;
    }
}
