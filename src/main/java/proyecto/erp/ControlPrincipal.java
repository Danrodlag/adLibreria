package proyecto.erp;

import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;
import java.awt.*;

import net.sf.jasperreports.engine.*;

import static proyecto.erp.Conexion.ConexionDB.getConnection;

public class ControlPrincipal {
    public ComboBox<String> comboFiltro;
    public ImageView imgLogo;
    public Button btnAnadir;
    public Tab tabCliente;
    public TableView<Cliente> tablaClientes;
    public Tab tabProductos;
    public TableView<Producto> tablaProductos;
    public Tab tabProveedores;
    public TableView<Proveedor> tablaProveedores;
    public TableColumn<Cliente, String> columDni;
    public TextField buscarTxt;
    public TabPane tabprincipal;

    public TableColumn<Cliente, String>  columNombreCli;
    public TableColumn<Cliente, String>  columDireccionCli;
    public TableColumn<Cliente, String>  columContactoCli;
    public TableColumn<Producto, String>  columNombreProd;
    public TableColumn<Producto, String> columDescripcionProd;
    public TableColumn<Producto, Integer> columPrecio;
    public TableColumn<Producto, Integer> columCantidad;
    public TableColumn<Proveedor, Integer> columIdProveedor;
    public TableColumn<Proveedor, String> columNombreProve;
    public TableColumn<Proveedor, String> columDireccionProve;
    public TableColumn<Proveedor, String> columContactoProve;
    public Button btnGenRep;
    public Tab tabVentas;
    public TableView<Venta> tablaVentas;
    public Tab tabPedido;
    public TableView<Pedido> tablaPedido;
    public TableColumn<Venta, Integer> columNumCliente;
    public TableColumn<Venta, Date> columFechaVenta;
    public TableColumn<Venta, Float> columTotalVenta;
    public TableColumn<Venta, Integer> columProducto;
    public TableColumn<Venta, Integer>  columCantidadVenta;
    public TableColumn<Pedido, Integer> columNumPedido;
    public TableColumn<Pedido, Integer> columNumProv;
    public TableColumn<Pedido, Integer> columNumProducto;
    public TableColumn<Pedido, Integer> columCantidadPedido;

    @FXML
    private void initialize() {
        // Para la tabla de clientes
        columDni.setCellValueFactory(cellData -> cellData.getValue().dniProperty());
        columNombreCli.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columDireccionCli.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        columContactoCli.setCellValueFactory(cellData -> cellData.getValue().contactoProperty());

// Para la tabla de productos
        columNombreProd.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columDescripcionProd.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());
       columPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadStock"));

        columIdProveedor.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));
// Para la tabla de proveedores
        columNombreProve.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columDireccionProve.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        columContactoProve.setCellValueFactory(cellData -> cellData.getValue().contactoProperty());

// Para la tabla de ventas
        columNumCliente.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));
        columFechaVenta.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columTotalVenta.setCellValueFactory(new PropertyValueFactory<>("total"));
        columProducto.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
        columCantidadVenta.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

// Para la tabla de pedidos
        columNumPedido.setCellValueFactory(new PropertyValueFactory<>("id_pedido"));
        columNumProv.setCellValueFactory(new PropertyValueFactory<>("id_proveedor"));
        columNumProducto.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
        columCantidadPedido.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        cargarDatos();

        btnAnadir.setOnAction(this::btnAnadirPulsado);
        btnGenRep.setOnAction(this::btnGenRepPulsado);
    }

    private void btnGenRepPulsado(ActionEvent event) {
        try {
            generarHTMLFactura();
            generarHTMLInventario();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void btnBorrarPulsado() {
        /*
        Cliente cliente = tablaLibros.getSelectionModel().getSelectedItem();
        borrarLibro(cliente);
        cargarDatosLibros();*/
    }

    private void borrarLibro(Cliente cliente) {
/*
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
*/

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
        //cargarDatosLibros();
    }
/*
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
*/

    public void cargarDatos(){
        cargarDatosPedido();
        cargarDatosProductos();
        cargarDatosProveedores();
        cargarDatosVentas();
        cargarDatosClientes();
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
                        tablaClientes.getItems().add(new Cliente(idCliente, nombre, direccion, contacto, dni));

                    }
                }

            }
            tablaClientes.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void cargarDatosProductos() {
        tablaProductos.getItems().clear();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM productos";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int idProducto = resultSet.getInt("id_producto");
                        String nombre = resultSet.getString("nombre");
                        String descripcion = resultSet.getString("descripcion");
                        float precio = resultSet.getFloat("precio");
                        int cantidadStock = resultSet.getInt("cantidad_stock");
                        int idProveedor = resultSet.getInt("id_proveedor");
                        tablaProductos.getItems().add(new Producto(idProducto, nombre, descripcion, precio, cantidadStock, idProveedor));

                    }
                }
            }
            tablaProductos.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void cargarDatosProveedores() {
        tablaProveedores.getItems().clear();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM proveedores";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int idProveedor = resultSet.getInt("id_proveedor");
                        String nombre = resultSet.getString("nombre");
                        String direccion = resultSet.getString("direccion");
                        String contacto = resultSet.getString("contacto");
                        tablaProveedores.getItems().add(new Proveedor(idProveedor, nombre, direccion, contacto));

                    }
                }
            }
            tablaProveedores.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarDatosVentas() {
        tablaVentas.getItems().clear();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM ventas";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id_venta = resultSet.getInt("id_venta");
                        Date fecha = resultSet.getDate("fecha");
                        int id_cliente = resultSet.getInt("id_cliente");
                        float total = resultSet.getFloat("total");
                        int id_producto = resultSet.getInt("id_producto");
                        int cantidad = resultSet.getInt("cantidad");
                        tablaVentas.getItems().add(new Venta(id_venta, fecha, id_cliente, total, id_producto, cantidad));

                    }
                }
            }
            tablaVentas.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarDatosPedido(){
        tablaPedido.getItems().clear();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM pedidos";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id_pedido = resultSet.getInt("id_pedido");
                        int id_proveedor = resultSet.getInt("id_proveedor");
                        int id_producto = resultSet.getInt("id_producto");
                        int cantidad = resultSet.getInt("cantidad");
                        tablaPedido.getItems().add(new Pedido(id_pedido, id_proveedor, id_producto, cantidad));

                    }
                }
            }
            tablaVentas.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void generarHTMLInventario() throws SQLException {
        Connection connection = getConnection();
        try {
            String jasperFile = "/InventarioActual.jrxml";
            InputStream jasperStream = getClass().getResourceAsStream(jasperFile);
            Map<String, Object> parameters = new HashMap<>();
            String logoPath = "LogoEmpresaLibros.jpg";
            URL logoUrl = getClass().getResource(logoPath);
            if (logoUrl != null) {
                parameters.put("logoPath", logoUrl.toExternalForm());
            } else {
                System.err.println("No se pudo cargar la URL del archivo de imagen: " + logoPath);
            }
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
            String htmlFile = "InventarioActual.html";
            JasperExportManager.exportReportToHtmlFile(jasperPrint, htmlFile);
            Desktop.getDesktop().browse(new File(htmlFile).toURI());
        } catch (JRException | IOException e) {
            e.printStackTrace();
            System.err.println("Error al generar el inventario actual: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void generarHTMLFactura() throws SQLException {
        Connection connection = getConnection();
        try {
            String jasperFile = "/FacturasVentas.jrxml";
            InputStream jasperStream = getClass().getResourceAsStream(jasperFile);
            Map<String, Object> parameters = new HashMap<>();
            String logoPath = "/image/logo.png";
            URL logoUrl = getClass().getResource(logoPath);
            if (logoUrl != null) {
                parameters.put("logoPath", logoUrl.toExternalForm());
            } else {
                System.err.println("No se pudo cargar la URL del archivo de imagen: " + logoPath);
            }
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
            String htmlFile = "FacturaVentas.html";
            JasperExportManager.exportReportToHtmlFile(jasperPrint, htmlFile);
            Desktop.getDesktop().browse(new File(htmlFile).toURI());
        } catch (JRException | IOException e) {
            e.printStackTrace();
            System.err.println("Error al generar la factura de ventas: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
