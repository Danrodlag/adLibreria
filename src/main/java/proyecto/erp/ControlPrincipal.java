package proyecto.erp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import static proyecto.erp.Conexion.ConexionDB.getConnection;

public class ControlPrincipal {
    public ComboBox<String> comboFiltro;
    public Label textError;
    public ImageView imgLogo;
    public Button btnAnadir;
    public Button btnBorrar;
    public Tab tabCliente;
    public TableView tablaClientes;
    public Tab tabProductos;
    public TableView tablaProductos;
    public Tab tabProveedores;
    public TableView tablaProveedores;
    public TableColumn columDni;
    public TextField buscarTxt;
    public TabPane tabprincipal;

    public TableColumn columNombreCli;
    public TableColumn columDireccionCli;
    public TableColumn columContactoCli;
    public TableColumn columNombreProd;
    public TableColumn columDescripcionProd;
    public TableColumn columPrecio;
    public TableColumn columCantidad;
    public TableColumn columIdProveedor;
    public TableColumn columNombreProve;
    public TableColumn columDireccionProve;
    public TableColumn columContactoProve;
    public TableColumn columFacturacion;
    public Button btnGenRep;

    @FXML
    private void initialize() {
        cargarDatos();
        ejecutarCargaPeriodica();

        btnAnadir.setOnAction(this::btnAnadirPulsado);
        btnBorrar.setOnAction(event -> btnBorrarPulsado());
    }

    private void btnBorrarPulsado() {
        Cliente cliente = tablaLibros.getSelectionModel().getSelectedItem();
        borrarLibro(cliente);
        cargarDatosLibros();
    }

    private void borrarLibro(Cliente cliente) {

        String query = "DELETE FROM libros WHERE idLibro = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, cliente.getIdCliente());
            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void btnAnadirPulsado(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("anadirLibro.fxml"));

        try {
            Parent root = loader.load();
            ControlAnadir controlAnadir = new ControlAnadir();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setMinWidth(300);
            stage.setMinHeight(400);

            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cargarDatosLibros();
    }

    public void ejecutarCargaPeriodica() {
        // Crea un objeto Runnable que llama a cargarDatosLibros()
        Runnable tarea = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    cargarDatosLibros();
                    try {
                        // Espera un segundo antes de la próxima llamada
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // En caso de interrupción, termina el bucle
                        break;
                    }
                }
            }
        };

        // Crea e inicia un hilo con la tarea definida
        Thread hilo = new Thread(tarea);
        hilo.start();
    }

    private void cargarDatosClientes() {



        tablaClientes.getItems().clear();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM clientes";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int idCliente = resultSet.getInt("id_cliente");
                        String nombre = resultSet.getString("nombre");
                        String direccion = resultSet.getString("direccion");
                        String contacto = resultSet.getString("contacto");
                        String dni = resultSet.getString("dni");
                        tablaClientes.getItems().add(new Cliente(idCliente,nombre, direccion, contacto, dni));

                    }
            }
            tablaLibros.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
