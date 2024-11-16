package com.example.projet_boleto;

import java.time.LocalDate;


public class LeituraRetornoBancoBrasil extends ProcessarBoletos{
    @Override
    protected Boleto processarLinhaArquivo(String[] vetor) {
        final var boleto = new Boleto();
        boleto.setId(Integer.parseInt(vetor[0]));
        boleto.setCodBanco(vetor[1]);
        boleto.setDataVencimento(LocalDate.parse(vetor[2], FORMATO_DATA));
        boleto.setDataPagamento(LocalDate.parse(vetor[3], FORMATO_DATA).atTime(0, 0, 0));
        boleto.setCpfCliente(vetor[4]);
        boleto.setValor(Double.parseDouble(vetor[5]));
        boleto.setMulta(Double.parseDouble(vetor[6]));
        boleto.setJuros(Double.parseDouble(vetor[7]));
        return boleto;
    }
}
