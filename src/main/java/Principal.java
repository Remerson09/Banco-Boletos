import com.example.projet_boleto.LeituraRetornoBancoBrasil;
import com.example.projet_boleto.LeituraRetornoBradesco;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

/**F
 * Classe para ver o funcionamento da leitura de boletos.
 *
 * @author Manoel Campos da Silva Filho
 */
public class Principal {
    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        // Processadores para cada banco
        final var processadorBancoBrasil = new LeituraRetornoBancoBrasil();
        final var processadorBradesco = new LeituraRetornoBradesco();

        URI caminhoArquivoBancoBrasil = Principal.class.getResource("/arquivo-banco-brasil-1.csv").toURI();
        System.out.println("Lendo arquivo Banco do Brasil: " + caminhoArquivoBancoBrasil + "\n");
        processadorBancoBrasil.processar(caminhoArquivoBancoBrasil);

        URI caminhoArquivoBradesco = Principal.class.getResource("/arquivo-bradesco-1.csv").toURI();
        System.out.println("Lendo arquivo Bradesco: " + caminhoArquivoBradesco + "\n");
        processadorBradesco.processar(caminhoArquivoBradesco);
    }
}
