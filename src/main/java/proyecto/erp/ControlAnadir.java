package proyecto.erp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static proyecto.erp.Conexion.ConexionDB.getConnection;

public class ControlAnadir {

    public TextField tituloText;
    public TextField cantidadText;
    public TextField autorText;
    public DatePicker fechaText;
    public Button btnAceptar;
    public Button btnCancelar;

    @FXML
    public void initialize(){

        btnAceptar.setOnAction(this::btnAceptarPulsado);
        btnCancelar.setOnAction(event -> {
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();
        });
    }
    private void btnAceptarPulsado(ActionEvent event) {
        // Obtener los valores de los campos de texto y el selector de fecha
        String titulo = tituloText.getText();
        String autor = autorText.getText();
        String cantidadStr = cantidadText.getText();
        LocalDate fecha = fechaText.getValue();

        // Validar los datos ingresados
        if (titulo.isEmpty() || autor.isEmpty() || cantidadStr.isEmpty() || fecha == null) {
            // Mostrar algún mensaje de error
            System.out.println("Por favor, completa todos los campos.");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
        } catch (NumberFormatException e) {
            // Mostrar algún mensaje de error
            System.out.println("La cantidad debe ser un número entero.");
            return;
        }

        insertarLibroEnBD(titulo, autor,fecha, cantidad);

        tituloText.clear();
        autorText.clear();
        cantidadText.clear();
        fechaText.setValue(null);

        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    private void insertarLibroEnBD(String titulo, String autor, LocalDate fecha, int cantidad) {
        String query = "INSERT INTO libros (titulo, autor, anioPublicación, cantidadDisponible) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, titulo);
            preparedStatement.setString(2, autor);
            preparedStatement.setDate(3, Date.valueOf(fecha));
            preparedStatement.setInt(4, cantidad);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Inserción exitosa!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
