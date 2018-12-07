import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

// Código inpirado no: https://stackoverflow.com/questions/4773778/creating-zip-archive-in-java

public class CriaCompacta {
    boolean CriaArquivo(String nome){
        try {
            
        } catch (Exception naoPudeCriar) {
            System.out.println("Não consegui criar o arquivo por alguma causa!");
            return false;
        }
    }

    boolean CompactaArquivo(String enderecoArquivo, String nomeArquivoDentro){
        // Entrada, o endereço do arquivo, o qual usaremos para exportar o arquivo zipado
        try {
            FileInputStream entrada = new FileInputStream(enderecoArquivo);

            ZipOutputStream exportar = new ZipOutputStream(new FileOutputStream(enderecoArquivo));

            // Para nomear o arquivo dentro do arquivo Zip
            exportar.putNextEntry(new ZipEntry(nomeArquivoDentro));

            // Usando buffer pra gravar o arquivo efetivamente (não gravamos de uma vez só)
            byte[] buffer = new byte[1024];

            int contador;

            while(contador = in.read(buffer) > 0){
                exportar.write(buffer, 0, contador);
            } // Vai preenchendo o arquivo enquanto houver buffer

            exportar.close();
            entrada.close();

        } catch (Exception naoPudeCompactar){
            System.out.println("Não consegui compactar o arquivo por alguma causa!")
        }
        
    }
}
