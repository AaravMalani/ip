package arthur;

import java.io.IOException;

import arthur.commands.CommandHandler;
import arthur.state.CommandContext;
import arthur.state.Storage;
import arthur.ui.MainWindow;
import arthur.ui.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Starts the Arthur application.
 */
public class ArthurDent extends Application {
    // AI-assisted: Named the minimum window dimensions and main layout resource.
    private static final String MAIN_WINDOW_FXML = "/view/MainWindow.fxml";
    private static final Image LOGO = Utils.loadImage("/images/arthur3.jpg");
    private static final double MINIMUM_WINDOW_HEIGHT = 220;
    private static final double MINIMUM_WINDOW_WIDTH = 417;

    @Override
    public void start(Stage stage) {
        try {
            Storage storage = new Storage();
            CommandContext context = storage.load();
            CommandHandler handler = new CommandHandler(context, storage);

            FXMLLoader fxmlLoader = new FXMLLoader(ArthurDent.class.getResource(MAIN_WINDOW_FXML));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Arthur Dent");
            stage.getIcons().add(LOGO);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);

            fxmlLoader.<MainWindow>getController().setHandler(handler); // inject the command handler
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

