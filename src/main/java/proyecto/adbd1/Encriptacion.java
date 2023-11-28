package proyecto.adbd1;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encriptacion {

    private static String encriptar(String texto, SecretKey clave) throws Exception {
        // Obtener una instancia de Cipher con el algoritmo AES
        Cipher cipher = Cipher.getInstance("AES");

        // Configurar el Cipher para encriptar
        cipher.init(Cipher.ENCRYPT_MODE, clave);

        // Encriptar el texto
        byte[] textoEnBytes = texto.getBytes(StandardCharsets.UTF_8);
        byte[] textoEncriptado = cipher.doFinal(textoEnBytes);
        System.out.println("Longitud de los bytes encriptados: " + textoEncriptado.length);

        // Convertir el resultado en Base64 para que sea legible
        return Base64.getEncoder().encodeToString(textoEncriptado);
    }

    private static String desencriptar(String textoEncriptado, SecretKey clave) throws Exception {
        // Obtener una instancia de Cipher con el algoritmo AES
        Cipher cipher = Cipher.getInstance("AES");

        // Configurar el Cipher para desencriptar
        cipher.init(Cipher.DECRYPT_MODE, clave);

        // Decodificar el texto encriptado desde Base64
        byte[] textoEncriptadoEnBytes = Base64.getDecoder().decode(textoEncriptado);

        // Desencriptar el texto
        byte[] textoDesencriptadoEnBytes = cipher.doFinal(textoEncriptadoEnBytes);
        System.out.println("Longitud de los bytes desencriptados: " + textoEncriptadoEnBytes.length);
        // Convertir el resultado de bytes a String
        return new String(textoDesencriptadoEnBytes, StandardCharsets.UTF_8);
    }
    public static SecretKey callKey(){
        String clave = "_B35,[k9q5{I**X'URH`:QPH;@.v:£0";
        byte[] claveBytes = clave.getBytes(StandardCharsets.UTF_8);

        // Crear una instancia de SecretKey utilizando la clave constante
        SecretKey secretKey = new SecretKeySpec(claveBytes, "AES");
        return secretKey;
    }
    public String encriptarPassword(String StrAEncriptar){
        try {
            return encriptar(StrAEncriptar, callKey());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String desencriptarPassword(String StrADesencriptar){
        try {
            return desencriptar(StrADesencriptar, callKey());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
