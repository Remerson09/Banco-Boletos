# Princípios SOLID

## 1. Princípio da Responsabilidade Única (SRP)
**Problema:** A classe `LeituraRetornoBancoBrasil` faz muitas coisas ao mesmo tempo: ela lê o arquivo, processa os dados e instancia objetos `Boleto`.

**Solução:** Separar as responsabilidades em classes diferentes. Por exemplo, podemos ter uma classe separada para leitura de arquivos e outra para processamento dos dados.

```java
// Classe responsável por ler o arquivo
public class LeitorArquivo {
    public List<String[]> lerArquivo(String caminhoArquivo) throws IOException {
        List<String[]> linhas = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(Paths.get(caminhoArquivo));
        String linha;
        while ((linha = reader.readLine()) != null) {
            linhas.add(linha.split(","));
        }
        return linhas;
    }
}

// Classe responsável por processar os dados específicos do Banco do Brasil
public class ProcessadorBancoBrasil implements ProcessadorBoleto {
    @Override
    public Boleto processarLinha(String[] vetor) {
        final var boleto = new Boleto();
        boleto.setId(Integer.parseInt(vetor[0]));
        boleto.setCodBanco(vetor[1]);
        boleto.setDataVencimento(LocalDate.parse(vetor[2], FORMATO_DATA));
        boleto.setDataPagamento(LocalDate.parse(vetor[3], FORMATO_DATA).atTime(0, 0, 0));
        boleto.setCpfCliente(vetor[4]));
        boleto.setValor(Double.parseDouble(vetor[5]));
        boleto.setMulta(Double.parseDouble(vetor[6]));
        boleto.setJuros(Double.parseDouble(vetor[7]));
        return boleto;
    }
}
## 2. Princípio Aberto-Fechado (OCP)
**Problema:** A classe LeituraRetornoBancoBrasil precisa ser modificada para cada novo tipo de processamento de boletos, o que viola o princípio OCP.

## Solução: Utilizar o padrão Strategy para permitir a extensão do comportamento sem modificar a classe existente.

java
public interface ProcessadorBoleto {
    Boleto processarLinha(String[] vetor);
}

public class LeitorArquivoComStrategy {
    private ProcessadorBoleto processadorBoleto;

    public LeitorArquivoComStrategy(ProcessadorBoleto processadorBoleto) {
        this.processadorBoleto = processadorBoleto;
    }

    public List<Boleto> lerBoletos(String caminhoArquivo) throws IOException {
        List<Boleto> boletos = new ArrayList<>();
        List<String[]> linhas = new LeitorArquivo().lerArquivo(caminhoArquivo);
        for (String[] linha : linhas) {
            boletos.add(processadorBoleto.processarLinha(linha));
        }
        return boletos;
    }
}
## 3. Princípio da Substituição de Liskov (LSP)
Problema: A classe LeituraRetornoBancoBrasil pode não ser substituível por outras classes derivadas se elas não implementarem corretamente o comportamento esperado.

**Solução:** Garantir que as subclasses implementem a interface e o comportamento esperado sem mudanças no comportamento da superclasse.

4. Princípio da Segregação de Interface (ISP)
Problema: A classe LeituraRetornoBancoBrasil pode depender de métodos que ela não usa, violando o ISP.

Solução: Criar interfaces mais específicas e garantir que as classes implementem apenas as interfaces necessárias.
