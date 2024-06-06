package moedas;

public class Moeda {
    private Double bobRate;
    private Double usdRate;
    private Double brlRate;
    private Double arsRate;
    private Double copRate;
    private  Double clpRate;

    public Moeda(ConversaoDeValores conversaoDeValores) {
        this.brlRate = conversaoDeValores.getConversionRates().get("BRL");
        this.arsRate = conversaoDeValores.getConversionRates().get("ARS");
        this.copRate = conversaoDeValores.getConversionRates().get("COP");
        this.usdRate = conversaoDeValores.getConversionRates().get("USD");
        this.clpRate = conversaoDeValores.getConversionRates().get("CLP");
        this.bobRate = conversaoDeValores.getConversionRates().get("BOB");
    }

    @Override
    public String toString() {
        return "cota em real (BRL) = " + brlRate +
                ", cota em Peso Argentino (ARS) = " + arsRate +
                ", cota em Peso Colombiano (COP) = " + copRate +
                ", cota em Dólar (USD) = " + usdRate;
    }
}
