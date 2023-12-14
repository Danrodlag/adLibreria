package proyecto.adbd1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application implements Runnable {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("control-acceso.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setMinHeight(scene.getRoot().prefHeight(-1));
        stage.setMinWidth(scene.getRoot().prefWidth(-1));
        stage.setWidth(scene.getRoot().prefWidth(-1));
        stage.setHeight(scene.getRoot().prefHeight(-1));

        stage.setResizable(false);

        stage.setTitle("");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0); // Asegura que la JVM se cierre completamente
        });

        stage.show();

    }

    public void run() {
        launch();
    }
}
