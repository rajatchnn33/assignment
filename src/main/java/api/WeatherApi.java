package api;

import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class WeatherApi  extends ApiClient{
    private final String baseUrl="http://api.openweathermap.org/data/2.5";

    public Response getWeatherDetailsByCityInCelsius(String city)
    {
        Response response = null;
        WebTarget endpoint = getClient().target(baseUrl).path("weather").queryParam("q",city).queryParam("appid","7fe67bf08c80ded756e598d6f8fedaea").queryParam("units","metric");
        try {
            response = endpoint.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
