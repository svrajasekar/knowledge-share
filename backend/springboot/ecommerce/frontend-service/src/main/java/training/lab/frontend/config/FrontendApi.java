package training.lab.frontend.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Component
public class FrontendApi {
    public WebClient getWebClient(String apiEndPoint) {
        WebClient client = WebClient.builder()
                .baseUrl(apiEndPoint)
                .defaultCookie("test", "true")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", apiEndPoint))
                .build();
        return client;
    }
}
