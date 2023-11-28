package proyecto.adbd1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
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
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
