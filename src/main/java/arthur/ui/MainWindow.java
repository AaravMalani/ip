package arthur.ui;

import java.util.Objects;

import arthur.commands.CommandHandler;
import arthur.messages.Message;
import arthur.messages.WelcomeMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private static final Image[] ARTHUR_IMAGES = {
            MainWindow.loadImage("/images/arthur1.jpg"),
            MainWindow.loadImage("/images/arthur2.jpg"),
            MainWindow.loadImage("/images/arthur3.jpg"),
            MainWindow.loadImage("/images/arthur4.jpg")
    };
    private static final Image USER_IMAGE = MainWindow.loadImage("/images/user.png");

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private CommandHandler handler;


    private int arthurImageIndex = 0;

    private static Image loadImage(String image) {
        return new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream(image)));
    }

    /**
     * Initializes the MainWindow by setting up the scroll pane and displaying the welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Message message = new WelcomeMessage();
        dialogContainer.getChildren().add(
                DialogBox.getArthurDialog(message.toString(),
                        ARTHUR_IMAGES[arthurImageIndex++ % ARTHUR_IMAGES.length],
                        message.getClassName())
        );
    }

    /**
     * Injects the CommandHandler instance
     */
    public void setHandler(CommandHandler handler) {
        this.handler = handler;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            return;
        }
        Message response = handler.handle(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, USER_IMAGE),
            DialogBox.getArthurDialog(response.toString(),
                    ARTHUR_IMAGES[arthurImageIndex++ % ARTHUR_IMAGES.length],
                    response.getClassName())
        );
        userInput.clear();
    }
}
