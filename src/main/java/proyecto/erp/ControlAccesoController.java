package proyecto.erp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static proyecto.erp.Conexion.ConexionDB.getConnection;

public class ControlAccesoController {

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passwordTextField;


    @FXML
    private Button altaNuevoButton;

    @FXML
    private Button aceptarButton;

    @FXML
    private Button salirButton;
    @FXML
    void aceptar(ActionEvent event) {
        String username = userTextField.getText();

        Encriptacion encriptacion = new Encriptacion();
        String password = null;
        try {
            password = encriptacion.hashPassword(passwordTextField.getText());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Realizar la validación en la base de datos
        if (validarCredenciales(username, password)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("princ.fxml"));
            Parent root = null;
            try {
                root = loader.load();

                ControlPrincipal controller = loader.getController();
                // Puedes llamar a los métodos de MainAppController aquí si es necesario

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setMinWidth(800);
                stage.setMinHeight(600);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Credenciales inválidas, muestra un mensaje de error
            mostrarMensaje("Credenciales incorrectas");
        }
    }
    @FXML
    void altaNuevo(ActionEvent event) {
        String nuevoUsername = userTextField.getText();
        String nuevaPassword = passwordTextField.getText();
        // Verificar si los campos no están vacíos
        if (nuevoUsername.isEmpty() || nuevaPassword.isEmpty()) {
            mostrarMensaje("Por favor, ingrese un usuario y una contraseña.");
            return;
        }

        if (registrarNuevoUsuario(nuevoUsername, nuevaPassword)) {
            mostrarMensaje("Usuario registrado exitosamente.");
        } else {
            mostrarMensaje("Error al registrar el usuario.");
        }
        // Limpiar los campos de texto después de realizar el registro
        userTextField.clear();
        passwordTextField.clear();
    }
    @FXML
    void salir(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }


    private boolean validarCredenciales(String username, String password) {

        Encriptacion encriptacion = new Encriptacion();

        try (Connection connection = getConnection()) {

            // Consulta SQL para verificar las credenciales
            String query = "SELECT * FROM usuario WHERE usuario.usuario = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);


                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Mover el cursor a la primera fila
                    if (resultSet.next()) {
                        // Si hay algún resultado, las credenciales son válidas
                        if (encriptacion.hashPassword(resultSet.getString("contrasena")).equals(password)){
                            return true;
                        }
                    } else {
                        // Manejar la situación cuando no hay ninguna fila en el ResultSet
                        // Por ejemplo, podrías lanzar una excepción o devolver false
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Manejo básico de errores
        }
        return false;
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private boolean registrarNuevoUsuario(String nuevoUsername, String nuevaPassword) {

        try (Connection connection = getConnection()) {
            // Consulta SQL para insertar un nuevo usuario
            String query = "INSERT INTO usuario (usuario.usuario, usuario.contraseña) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nuevoUsername);
                preparedStatement.setString(2, nuevaPassword);

                // Ejecutar la inserción
                int filasAfectadas = preparedStatement.executeUpdate();

                // Si se insertó correctamente, se considera un éxito
                return filasAfectadas > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Manejo básico de errores
        }

}
}
