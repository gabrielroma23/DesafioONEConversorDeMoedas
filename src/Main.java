import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import moedas.ConversaoDeValores;
import moedas.Moeda;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        Gson gson = new GsonBuilder().create();
        boolean continuarConsulta = true;

        System.out.println("Bem vindo ao sistema de conversão de moedas");
        ConsultaMoeda consultaMoeda = new ConsultaMoeda();

        while (continuarConsulta) {
            System.out.println("Insira a moeda de origem (ex: USD, BRL, ARS, COP, CLP, BOB):");
            String moedaOrigem = leitura.next().toUpperCase();



            consultaMoeda.requisicaoParaConsulta(moedaOrigem);

            System.out.println("Insira o valor que deseja converter:");
            double valor;
            try {
                valor = leitura.nextDouble();
            } catch (Exception e) {
                System.out.println("Valor inválido. Por favor, insira um número.");
                leitura.next(); // Limpar a entrada inválida
                continue;
            }

            System.out.println("Insira a moeda de destino (ex: USD, BRL, ARS, COP, CLP, BOB):");
            String moedaDestino = leitura.next().toUpperCase();


            try {
                double valorConvertido = consultaMoeda.converterMoeda(moedaOrigem, valor, moedaDestino);
                System.out.printf("Valor convertido: %.2f %s%n", valorConvertido, moedaDestino);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Deseja consultar outra moeda? (s/n):");
            String resposta = leitura.next().toLowerCase();
            if (!resposta.equals("s")) {
                continuarConsulta = false;
            }
        }
    }
}
