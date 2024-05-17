import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionApi {

    public Monedas buscaMonedas(String busqueda) throws IOException, InterruptedException {
        String url = "https://v6.exchangerate-api.com/v6/eba2e5508ca1e164b1298db7/latest/" + busqueda;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        Gson gson = new Gson();
        return gson.fromJson(responseBody, Monedas.class);
    }
}
