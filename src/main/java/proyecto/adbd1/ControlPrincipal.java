package proyecto.adbd1;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

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
    public ImageView imgLogo;
    public TableView<Libro> tablaLibros;
    public TableColumn<Libro, String> columTitulo;
    public TableColumn<Libro, String> columAutor;
    public TableColumn<Libro, Date> columAnoPub;
    public TableColumn<Libro, Integer> columCantidad;
    public Button btnAnadir;
    public Button btnBorrar;
    public boolean running;
    public Button btnModificar;
    public Button btnBuscar;

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
        running = true;
        ejecutarCargaPeriodica();
        tablaLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null) {
                running=false;
            }else {
                running= true;
            }
        });
        deseleccionarTabla();
        btnAnadir.setOnAction(this::btnAnadirPulsado);
        btnBorrar.setOnAction(event -> btnBorrarPulsado());
        btnBuscar.setOnAction(this::btnBuscarPulsado);
        btnModificar.setOnAction(event -> btnModificarPulsado());

    }

    private void btnModificarPulsado() {
        Libro libro = tablaLibros.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modLibro.fxml"));

        try {
            ControlModificar controlModificar = new ControlModificar(libro);
            loader.setController(controlModificar);
            Parent root = loader.load();

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

    }

    private void btnBuscarPulsado(ActionEvent event) {
    }

    private void btnBorrarPulsado() {
        Libro libro = tablaLibros.getSelectionModel().getSelectedItem();
        borrarLibro(libro);
    }

    private void borrarLibro(Libro libro) {

        String query = "DELETE FROM libros WHERE idLibro = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, libro.getIdLibro());
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

    }

    public void deseleccionarTabla(){
        Timeline deseleccionarTimeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> tablaLibros.getSelectionModel().clearSelection()));

        deseleccionarTimeline.setCycleCount(Animation.INDEFINITE);

        deseleccionarTimeline.play();
        running = true;
        ejecutarCargaPeriodica();
    }

    public void ejecutarCargaPeriodica() {
        // Crea un objeto Runnable que llama a cargarDatosLibros()

        Runnable tarea = () -> {
            while (true) {

                try {
                    if(!running){
                        Platform.runLater(() -> cargarDatosLibros());
                    }
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // En caso de interrupción, termina el bucle
                    break;
                }
            }
        };
        // Crea e inicia un hilo con la tarea definida
        Thread hilo = new Thread(tarea);

        hilo.setDaemon(true);
        hilo.start();
    }

    private void cargarDatosLibros() {
        tablaLibros.getItems().clear();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM libros";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int idLibro = resultSet.getInt("idLibro");
                        String titulo = resultSet.getString("libros.titulo");
                        String autor = resultSet.getString("libros.autor");
                        Date fecha = resultSet.getDate("libros.anioPublicación");
                        int cantidadDisponible = resultSet.getInt("libros.cantidadDisponible");

                        tablaLibros.getItems().add(new Libro(idLibro,titulo, autor, fecha, cantidadDisponible ));

                    }
                }
            }
            tablaLibros.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
