package proyecto.erp;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class Encriptacion {

    public String hashPassword(String password) throws Exception {
        // Obtener una instancia de MessageDigest para SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Calcular el hash de la contraseña
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        // Convertir el hash a Base64 para que sea seguro almacenarlo como una cadena
        return Base64.getEncoder().encodeToString(hash);
    }

}
