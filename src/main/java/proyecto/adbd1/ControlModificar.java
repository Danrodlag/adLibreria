package proyecto.adbd1;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static proyecto.adbd1.Conexion.ConexionDB.getConnection;

public class ControlModificar {

    public TextField tituloText = new TextField();
    public TextField cantidadText = new TextField();
    public TextField autorText = new TextField();
    public DatePicker fechaText = new DatePicker();
    public Button btnAceptar;
    public Button btnCancelar;
    public int idLibro;

    @FXML
    public void initialize(){

        btnAceptar.setOnAction(this::btnAceptarPulsado);
        btnCancelar.setOnAction(event -> {
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();
        });
    }

    public ControlModificar(Libro libro) {
        tituloText.setPromptText(libro.getTitulo());
        autorText.setPromptText(libro.getAutor());
        cantidadText.setPromptText(String.valueOf(libro.getCantidadDisponible()));
        fechaText.setPromptText(String.valueOf(libro.getFecha().toLocalDate()));
        this.idLibro = libro.getIdLibro();
    }

    private void btnAceptarPulsado(ActionEvent event) {
        // Obtener los valores de los campos de texto y el selector de fecha
        String titulo = tituloText.getText();
        String autor = autorText.getText();
        String cantidadStr = cantidadText.getText();
        LocalDate fecha = fechaText.getValue();

        // Validar los datos ingresados
        if (titulo.isEmpty() || autor.isEmpty() || cantidadStr.isEmpty() || fecha == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de validación");
            alert.setHeaderText("Datos de entrada vacíos");
            alert.setContentText("Por favor, complete todos los campos.");
            alert.showAndWait();
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de validación");
            alert.setHeaderText("Datos de Cantidad, inválidos");
            alert.setContentText("Por favor, escriba un número válido.");
            alert.showAndWait();
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
        String query = "UPDATE libros SET titulo = ?, autor = ?, anioPublicación = ?, cantidadDisponible = ? WHERE idLibro = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, titulo);
            preparedStatement.setString(2, autor);
            preparedStatement.setDate(3, Date.valueOf(fecha));
            preparedStatement.setInt(4, cantidad);
            preparedStatement.setInt(5, idLibro);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Actualización exitosa!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}