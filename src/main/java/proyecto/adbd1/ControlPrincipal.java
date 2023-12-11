package proyecto.adbd1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public Button añadirBotón;
    public Button botónBorrar;

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

        // Añadir los datos a la TableView
        tablaLibros.setItems(librosData);
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
