package it.unicam.cs.pa.jlogo_110944.View;

import it.unicam.cs.pa.jlogo_110944.Controller.Controller;
import it.unicam.cs.pa.jlogo_110944.Controller.Implementation.BasicController;
import it.unicam.cs.pa.jlogo_110944.Model.*;
import it.unicam.cs.pa.jlogo_110944.Model.Implementation.CartesianLocation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Main window controller.
 */
public class MainFXController extends Application implements PanelUpdateListener<CartesianLocation> {
    private final Logger logger = Logger.getLogger("it.unicam.cs.pa.jlogo_110944.MainFXController");
    private final Controller<CartesianLocation, Cursor<CartesianLocation>> controller = new BasicController();
    private int areasCount = 0;

    @FXML
    public TextArea executedCommandsTextArea;
    @FXML
    public Label executedCommandsLabel;
    @FXML
    private Button resultButton;
    @FXML
    private Pane containerPane;
    @FXML
    private Pane innerPane;
    @FXML
    private Button nextButton;
    @FXML
    private Button skipButton;
    @FXML
    private MenuItem exportCommands;
    @FXML
    private MenuItem loadCommands;

    @Override
    public void start(Stage primaryStage) {
        try {
            logger.finest("Starting the application...");
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/jlogo.fxml")));
            primaryStage.setTitle("JLOGO");
            Scene scene = new Scene(root, 300, 300);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException e) {
            logger.severe("Application start error: " + e.getMessage());
        }
    }

    @Override
    public void stop() {
        logger.info("Exiting the application...");
    }

    /**
     * Handles the onAction event of the New Game menu item.
     */
    @FXML
    public void handleNewGame() {
        try {
            logger.info("New Game Menu item clicked");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/newMatch.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            NewPanelController controller = getController(loader, stage);
            stage.showAndWait();
            if (controller.isOk()) {
                this.controller.newPanel(controller.getSize().getWidth(), controller.getSize().getHeight());
                this.loadCommands.setVisible(true);
                this.exportCommands.setVisible(false);
                disableButtons();
                moveCursor((double) controller.getSize().getWidth() / 2, (double) controller.getSize().getHeight() / 2);
            }
        } catch (Exception e) {
            logger.severe("The window for creating a new panel could not be loaded: " + e.getMessage());
        }
    }

    /**
     * Handles the onAction event of the Export Commands menu item.
     */
    @FXML
    public void handleExportCommands() {
        try {
            logger.info("Exporting commands...");
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(stage);
            if (selectedDirectory != null)
                handleExportCommands(selectedDirectory);
        } catch (IOException e) {
            logger.severe("The commands could not be exported: " + e.getMessage());
        }
    }

    /**
     * Handles the onAction event of the Load Commands menu item.
     */
    @FXML
    public void handleLoadCommands() {
        try {
            logger.info("Load Commands Menu item clicked");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/loadCommands.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            LoadCommandsController controller = getController(loader, stage);
            stage.showAndWait();
            if (controller.getFilePath() != null)
                this.controller.loadFromFile(controller.getFilePath());
            else if (controller.getWrittenCommands().length() != 0)
                this.controller.loadCommands(controller.getWrittenCommands());
            loadCommands();
        } catch (Exception e) {
            logger.severe("Commands could not be loaded : " + e.getMessage());
        }
    }

    /**
     * Handles the onAction event of the Close menu item.
     */
    @FXML
    public void handleClose() {
        logger.finest("Exiting the application...");
        Platform.exit();
    }

    /**
     * Handles the onAction event of the Next button.
     */
    @FXML
    public void handleNextButton() {
        try {
            logger.info("Next button clicked");
            this.controller.executeCommand().forEach(s -> this.executedCommandsTextArea.appendText(s + " "));
            this.executedCommandsTextArea.appendText("\n");
            if (!this.controller.hasCommand()) {
                this.setDisableNextSkipButtons(true);
                this.resultButton.setDisable(false);
                this.exportCommands.setVisible(true);
            }
        } catch (SyntaxErrorException e) {
            setDisableNextSkipButtons(true);
            logger.severe("Command execution failed: " + e.getMessage());
        }
    }

    /**
     * Handles the onAction event of the Skip button.
     */
    @FXML
    public void handleSkipButton() {
        while (this.controller.hasCommand()) {
            if(this.nextButton.isDisable())
                break;
            handleNextButton();
        }
    }

    /**
     * Handles the onAction event of the About menu item.
     */
    @FXML
    public void handleAbout() {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.getDialogPane().setContentText("JLOGO: This is a project for the exam of Advanced Programming at \nthe Bachelor Degree in Computer Science at University of Camerino.\nFor more information on how the program works please consult the pdf attached to the project");
        infoAlert.getDialogPane().setMinWidth(600);
        infoAlert.showAndWait();
    }

    /**
     * Handles the onAction event of the Result button.
     */
    @FXML
    public void handleResultButton() {
        try {
            logger.info("Result button clicked");
            Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/result.fxml")));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            TextArea textArea = (TextArea) root.getChildren().get(1);
            this.controller.result().forEach(l -> textArea.appendText(l + "\n"));
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            logger.severe("It was not possible to display the result of the executed commands : " + e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        this.controller.addListener(this);
        this.executedCommandsLabel.setVisible(false);
        this.loadCommands.setVisible(false);
        this.exportCommands.setVisible(false);
        initializeCommandsTextArea();
        disableButtons();
        fixCoordinatesPane();
    }

    @Override
    public void fireAddLine(Line<CartesianLocation> line) {
        double x1 = line.getStart().getX();
        double y1 = line.getStart().getY();
        double x2 = line.getFinish().getX();
        double y2 = line.getFinish().getY();
        javafx.scene.shape.Line lineFX = new javafx.scene.shape.Line(x1, y1, x2, y2);
        lineFX.setStrokeWidth(line.getSize());
        strokeColor(lineFX, line.getColor());
        this.innerPane.getChildren().add(this.innerPane.getChildren().size() - 1, lineFX);
    }

    @Override
    public void fireCursorMoved(CartesianLocation location) {
        moveCursor(location.getX(), location.getY());
    }

    @Override
    public void fireAreaFound(Area<CartesianLocation> area) {
        List<CartesianLocation> coordinates = area.getLines().stream()
                .sequential()
                .map(Line::getStart)
                .collect(Collectors.toList());
        addArea(coordinates, area.getColor());
    }

    @Override
    public void fireClearScreen() {
        removeLinesAreas();
    }

    @Override
    public void fireSetScreenColor(RGBColor color) {
        Rectangle background = (Rectangle) this.innerPane.getChildren().get(0);
        fillColor(background, color);
    }

    @Override
    public void firePanelChanged(int width, int height) {
        initializeCommandsTextArea();
        resizeCommandsTextArea(width);
        resizePanes(width, height);
        resizeStage(width, height);
        clearInnerPane();
        makeCursor(width, height);
        createBackground(width, height);
        this.innerPane.setClip(new Rectangle(width, height));
        this.executedCommandsLabel.setVisible(false);
        this.loadCommands.setVisible(true);
    }

    @Override
    public void fireAngleChanged(int angle) {
        ImageView view = (ImageView) this.innerPane.getChildren().get(this.innerPane.getChildren().size() - 1);
        view.setRotate(90 + angle);
    }

    /**
     * Resizes the text area on which the executed commands are displayed.
     */
    private void resizeCommandsTextArea(int width) {
        this.executedCommandsTextArea.setLayoutX(width + 19);
        this.executedCommandsLabel.setLayoutX(width + 76);
    }

    /**
     * Initializes the text area on which the commands will be displayed.
     */
    private void initializeCommandsTextArea() {
        this.executedCommandsTextArea.clear();
        this.executedCommandsTextArea.setEditable(false);
        this.executedCommandsTextArea.setVisible(false);
    }

    /**
     * Adjust the coordinates of the pane so that they start at the bottom left of the screen.
     */
    private void fixCoordinatesPane() {
        Scale scale = new Scale();
        scale.setX(1);
        scale.setY(-1);
        scale.pivotYProperty().bind(Bindings.createDoubleBinding(() ->
                        this.innerPane.getBoundsInLocal().getMinY() + this.innerPane.getBoundsInLocal().getHeight() / 2,
                this.innerPane.boundsInLocalProperty()));
        this.innerPane.getTransforms().add(scale);
    }

    /**
     * Creates the background on which the cursor moves.
     */
    private void createBackground(int width, int height) {
        Rectangle rectangle = new Rectangle(width, height);
        this.innerPane.getChildren().add(0, rectangle);
        rectangle.setFill(Color.WHITE);
    }

    /**
     * Adds an area to the pane.
     */
    private void addArea(List<CartesianLocation> vertices, RGBColor color) {
        double[] verticesCoordinates = new double[vertices.size() * 2];
        int i = 0;
        for (CartesianLocation c : vertices) {
            verticesCoordinates[i++] = c.getX();
            verticesCoordinates[i++] = c.getY();
        }
        Polygon area = new Polygon(verticesCoordinates);
        fillColor(area, color);
        this.innerPane.getChildren().add(++areasCount, area);
    }


    /**
     * Disable all buttons.
     */
    private void disableButtons() {
        nextButton.setDisable(true);
        skipButton.setDisable(true);
        resultButton.setDisable(true);
    }

    /**
     * Depending on the boolean, activates or deactivates the next and skip buttons.
     */
    private void setDisableNextSkipButtons(boolean b) {
        nextButton.setDisable(b);
        skipButton.setDisable(b);
    }

    /**
     * Removes all inner pane children
     */
    private void clearInnerPane() {
        this.innerPane.getChildren().clear();
        this.areasCount = 0;
    }

    /**
     * Removes all lines and ares from the inner pane
     */
    private void removeLinesAreas() {
        this.areasCount = 0;
        this.innerPane.getChildren().remove(1, this.innerPane.getChildren().size() - 1);
    }

    /**
     * Resizes all panes.
     */
    private void resizePanes(int width, int height) {
        this.containerPane.setPrefHeight(height + 50);
        this.containerPane.setPrefWidth(width + 255);
        this.innerPane.setPrefHeight(height);
        this.innerPane.setPrefWidth(width);
        this.innerPane.setLayoutX(10);
        this.innerPane.setLayoutY(50);
        this.innerPane.setStyle("-fx-border-color: black");
    }

    /**
     * Creates the cursor.
     */
    private void makeCursor(int width, int height) {
        try {
            logger.finest("Adding turtle image...");
            Image img = new Image(Objects.requireNonNull(getClass().getResource("/turtle.png")).toString());
            ImageView view = new ImageView(img);
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setRotate(90);
            this.innerPane.getChildren().add(view);
            moveCursor((double) width / 2, (double) height / 2);
        } catch (Exception e) {
            logger.severe("It was not possible to load the cursor image: " + e.getMessage());
        }
    }

    /**
     * Moves the cursor.
     */
    private void moveCursor(double x, double y) {
        ImageView view = (ImageView) this.innerPane.getChildren().get(this.innerPane.getChildren().size() - 1);
        view.setX(x - 25);
        view.setY(y - 25);
    }

    /**
     * Resize the primary stage.
     */
    private void resizeStage(int width, int height) {
        Window w = Stage.getWindows()
                .stream()
                .filter(Window::isShowing)
                .findFirst().orElse(null);
        if (w != null) {
            w.setHeight(height + 100);
            w.setWidth(width + 325);
            w.centerOnScreen();
        }
    }

    /**
     * Sets the stroke color of a shape.
     */
    private void strokeColor(Shape s, RGBColor color) {
        int red = color.getRed() & 0xFF;
        int green = color.getGreen() & 0xFF;
        int blue = color.getBlue() & 0xFF;
        s.setStroke(Color.rgb(red, green, blue));
    }

    /**
     * Sets the fill color of a shape.
     */
    private void fillColor(Shape s, RGBColor color) {
        int red = color.getRed() & 0xFF;
        int green = color.getGreen() & 0xFF;
        int blue = color.getBlue() & 0xFF;
        s.setFill(Color.rgb(red, green, blue));
    }

    /**
     * Loads the scene to the stage and returns a controller.
     */
    private <T> T getController(FXMLLoader loader, Stage stage) throws IOException {
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(loader.load()));
        return loader.getController();
    }

    /**
     * If there is a command to execute, enables next and skip buttons.
     */
    private void loadCommands() {
        logger.info("Commands loaded");
        if (this.controller.hasCommand()) {
            setDisableNextSkipButtons(false);
            this.loadCommands.setVisible(false);
            this.executedCommandsLabel.setVisible(true);
            this.executedCommandsTextArea.setVisible(true);
        }
    }

    /**
     * Given a directory, creates inside it a file to which export the
     * commands. If there was already a file with the same name, its content is deleted.
     */
    private void handleExportCommands(File directory) throws IOException {
        String path = directory.getAbsolutePath() + "/commands.txt";
        File commandFile = new File(path);
        if (!commandFile.createNewFile() && !commandFile.isDirectory())
            Files.write(commandFile.toPath(), "".getBytes());
        if (commandFile.isDirectory()) {
            logger.info("It was not possible to export commands: directory with the name command.txt already exist");
            return;
        }
        logger.info("Commands successfully exported");
        this.controller.exportCommands(commandFile);
    }
}
