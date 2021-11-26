package it.unicam.cs.pa.jlogo_110944.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.logging.Logger;


/**
 * Controller of the window to load commands.
 */
public class LoadCommandsController {
    private final Logger logger = Logger.getLogger("it.unicam.cs.pa.jlogo_110944.LoadCommandsController");

    private FileChooser fileChooser;
    private String pathSelectedFile;
    private String writtenCommands;

    /**
     * Handles the onAction event of the Cancel button.
     */
    @FXML
    public void handleClose(ActionEvent actionEvent) {
        logger.info("Close button clicked");
        doClose(actionEvent);
    }

    /**
     * Handles the onAction event of the Load From File button.
     */
    @FXML
    public void handleLoadFromFile(ActionEvent actionEvent) {
        logger.info("Load button clicked");
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        File selectedFile = this.fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            this.pathSelectedFile = selectedFile.getAbsolutePath();
            logger.info("File loaded");
            doClose(actionEvent);
        }
    }

    /**
     * Handles the onAction event of the Write Commands button.
     */
    @FXML
    public void handleWriteCommands(ActionEvent actionEvent) {
        try {
            logger.info("Write Commands button clicked");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/writeCommands.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            WriteCommandsController controller = loader.getController();
            stage.showAndWait();
            if (controller.isOk()) {
                this.writtenCommands = controller.textWriteCommands.getText();
                logger.info("Commands written");
                doClose(actionEvent);
            }
        } catch (Exception e) {
            logger.severe("Commands could not be written: " + e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        this.pathSelectedFile = null;
        this.writtenCommands = "";
        this.fileChooser = new FileChooser();
        this.fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        this.fileChooser.setTitle("Open File");
    }

    /**
     * Closes the window.
     */
    private void doClose(ActionEvent actionEvent) {
        ((Stage) ((javafx.scene.control.Button) actionEvent.getSource()).getScene().getWindow()).close();
    }

    /**
     * Returns the path to the selected file.
     */
    public String getFilePath() {
        return this.pathSelectedFile;
    }

    /**
     * Returns the written commands.
     */
    public String getWrittenCommands() {
        return this.writtenCommands;
    }
}
