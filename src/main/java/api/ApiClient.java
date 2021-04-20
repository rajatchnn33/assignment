package api;


import com.fasterxml.jackson.core.util.JacksonFeature;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Feature;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

public class ApiClient {
    private Client client;
    private Feature feature;
    protected Feature loggingFeature = null;
    public ApiClient()
    {
        setClient(ClientBuilder.newBuilder().build());
        client.property(ClientProperties.CONNECT_TIMEOUT, 100000);
        client.property(ClientProperties.READ_TIMEOUT, 1000000);
        client.register(JacksonFeature.class);
        client.register(MultiPartFeature.class);
    }
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Feature getFeature() {
        return feature;
    }

    public Feature setFeature(Feature feature) {
        return this.feature = feature;
    }
}
