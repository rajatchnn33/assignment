package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.Response;
import model.WeatherFromApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class TestUtils {
    protected Iterator<Object[]> readDataFile() throws IOException
    {
        String fileName=System.getProperty("user.dir")+"/src/test/resources/data.csv";
        BufferedReader csvFile ;
        File file = new File(fileName);
        csvFile = new BufferedReader(new FileReader(file));
        String line ;
        ArrayList<Object[]> data = new ArrayList<>();
        while ((line = csvFile.readLine()) != null)
        {
            String[] temp = line.trim().split(",");
            List<Object> arrray = new ArrayList<>();
            Collections.addAll(arrray, temp);
            data.add(arrray.toArray());
        }
        csvFile.close();
        return data.iterator();
    }



    public <T> T parseResponse(Response response, TypeReference<T> type) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        String data = response.readEntity(String.class);
        return objectMapper.readValue(data, type);
    }
    public  boolean compareTemperatureFromUiAndApi(HashMap<String, String> uiTemperature, WeatherFromApi apiTemperature,String variation) throws TemperatureVariationException {
        BigDecimal uiTemp= BigDecimal.valueOf(Double.parseDouble(uiTemperature.get("Temp in Degrees")));
        BigDecimal apiTemp= BigDecimal.valueOf(Double.valueOf(apiTemperature.getTemperature().get("temp")));
        String[] range=variation.split("-");
        BigDecimal lowerRange= BigDecimal.valueOf(Double.parseDouble(range[0]));
        BigDecimal higherRange= BigDecimal.valueOf(Double.parseDouble(range[1]));
        BigDecimal difference=uiTemp.subtract(apiTemp).abs().setScale(2, RoundingMode.HALF_UP);
        boolean isTrue=isBetween(difference,lowerRange,higherRange);
        if(!isTrue)
        {
            throw new TemperatureVariationException(difference);
        }
        return true;

    }
    private boolean isBetween(BigDecimal diff,BigDecimal lowRange,BigDecimal highRange)

    {
        return (diff.compareTo(lowRange)>0 && diff.compareTo(highRange)<0);
    }
}
