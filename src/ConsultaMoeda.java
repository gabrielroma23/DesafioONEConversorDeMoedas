import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import moedas.ConversaoDeValores;
import moedas.Moeda;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ConsultaMoeda {
    private Map<String, Double> taxasDeCambio;

    public ConsultaMoeda() {
        this.taxasDeCambio = new HashMap<>();
    }

    public void requisicaoParaConsulta(String moeda) {
        URI enderecoDaAPI = URI.create("https://v6.exchangerate-api.com/v6/fdeeede5cee0dbfeff3a1d18/latest/" + moeda);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(enderecoDaAPI)
                .build();

        Gson gson = new GsonBuilder().create();

        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            ConversaoDeValores cotaConversaoDeValores = gson.fromJson(json, ConversaoDeValores.class);
            taxasDeCambio = cotaConversaoDeValores.getConversionRates();

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro na consulta da moeda: " + e.getMessage());
        }
    }

    public double converterMoeda(String moedaOrigem, double valor, String moedaDestino) {
        if (taxasDeCambio == null || !taxasDeCambio.containsKey(moedaDestino)) {
            throw new IllegalArgumentException("Moeda de destino não disponível.");
        }
        double taxaOrigemParaUSD = taxasDeCambio.getOrDefault(moedaOrigem, 1.0);
        double taxaDestinoParaUSD = taxasDeCambio.get(moedaDestino);
        return (valor / taxaOrigemParaUSD) * taxaDestinoParaUSD;
    }

    public boolean isMoedaValida(String moeda) {
        return taxasDeCambio != null && taxasDeCambio.containsKey(moeda);
    }
}
