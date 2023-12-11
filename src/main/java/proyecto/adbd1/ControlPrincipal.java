package proyecto.adbd1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;

import static proyecto.adbd1.Conexion.ConexionDB.getConnection;

public class ControlPrincipal {
    public TextField usuarioText;
    public ComboBox<String> comboFiltro;
    public Label textError;
    public ImageView imgLogo;
    public TableView<Libro> tablaLibros;
    public TableColumn<Libro, String> columTitulo;
    public TableColumn<Libro, String> columAutor;
    public TableColumn<Libro, Date> columAnoPub;
    public TableColumn<Libro, Integer> columCantidad;
    public Button btnAnadir;
    public Button btnBorrar;
    public TextField tituloText;
    public TextField cantidadText;
    public TextField autorText;
    public DatePicker fechaText;
    public Button btnAceptar;
    public Button btnCancelar;

    private ObservableList<Libro> librosData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Configurar las columnas de la TableView
        columTitulo.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());
        columAutor.setCellValueFactory(cellData -> cellData.getValue().autorProperty());
        columAnoPub.setCellFactory(column -> new TableCell<Libro, Date>() {
            private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);

                if(empty) {
                    setText(null);
                }
                else {
                    this.setText(format.format(item));
                }
            }
        });
        columAnoPub.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columCantidad.setCellValueFactory(cellData -> cellData.getValue().cantidadDisponibleProperty().asObject());

        cargarDatosLibros();

        btnAnadir.setOnAction(this::btnAnadirPulsado);
        btnBorrar.setOnAction(event -> btnBorrarPulsado());


        // Añadir los datos a la TableView
        tablaLibros.setItems(librosData);
    }

    private void btnBorrarPulsado() {
        return;
    }

    private void btnAnadirPulsado(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("anadirLibro.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setMinWidth(600);
            stage.setMinHeight(400);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        btnAceptar.setOnAction(this::btnAceptarPulsado);
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

    private void cargarDatosLibros() {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM libros";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int idLibro = resultSet.getInt("idLibro");
                        String titulo = resultSet.getString("libros.titulo");
                        String autor = resultSet.getString("libros.autor");
                        Date fecha = resultSet.getDate("libros.aniopublicación");
                        int cantidadDisponible = resultSet.getInt("libros.cantidadDisponible");

                        librosData.add(new Libro(idLibro, autor,titulo, fecha, cantidadDisponible));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
