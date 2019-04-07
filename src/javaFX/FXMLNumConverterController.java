package javaFX;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import logic.Converter;
import logic.InvalidInputException;
import logic.Mode;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class that handles every gui interaction with the user.
 */
public class FXMLNumConverterController implements Initializable {
    private static final String INPUT_PREFIX = "   Input: ";
    private static final String BLOCK_PREFIX = "   Blocksize: ";
    private static final String TUTORIAL_DESCR = "Before you start to convert a number the source as well as the " +
            "target base must be declared in both drop down menus. Now you may type in the number to convert. The " +
            "Block panel states the minimal digit count of the output in the respective numeral system.\n\nExample, " +
            "converting the number 1 from the decimal system to binary: source base = 10, target base = 2, block = 8 " +
            "(does not really matter, since it is the minimal digit count), typing enter in the input field will " +
            "start the conversion process.\n\nWhen hovering over the the fields, a small description will pop up. " +
            "This " +
            "description is also available under the 'help' tag.";

    private static final int DEFAULT_SRC_BASE = 10;
    private static final int DEFAULT_TARGET_BASE = 2;
    private static final int DEFAULT_BLOCKSIZE = 8;
    private static final String DARK_MODE_CSS_TITLE = "jfoenixTheme.css";

    @FXML
    private JFXTextField txtFldBlock;

    @FXML
    private TableView<Result> tableResult;

    @FXML
    private JFXComboBox<Label> cmbBxTarget;

    @FXML
    private JFXTextField txtFldInput;

    @FXML
    private JFXComboBox<Label> cmbBxSource;

    @FXML
    private StackPane stackPane;

    private Converter converter;

    /**
     * List of results generated during the programms runtime
     */
    private final ObservableList<Result> data = FXCollections.observableArrayList();

    private File file = new File("history.txt");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // todo find out why the textfield is not in focus
        this.txtFldInput.requestFocus();

        initTable();
        this.txtFldBlock.setText(BLOCK_PREFIX + DEFAULT_BLOCKSIZE);
        this.txtFldInput.setText(INPUT_PREFIX);
        initComboboxes();
        this.tableResult.setPlaceholder(new Label("empty history"));

        this.converter = new Converter(new Mode(DEFAULT_SRC_BASE, DEFAULT_TARGET_BASE, DEFAULT_BLOCKSIZE));
    }

    /**
     * Initializes the table with a given file input
     */
    private void initTable() {
        TableColumn srcBaseCol = new TableColumn("Source base");
        TableColumn inputCol = new TableColumn("Input");
        TableColumn trgtBaseCol = new TableColumn("Target base");
        TableColumn outputCol = new TableColumn("Output");

        srcBaseCol.setResizable(true);

        srcBaseCol.setCellValueFactory(
                new PropertyValueFactory<Result, String>("srcBase")
        );
        inputCol.setCellValueFactory(
                new PropertyValueFactory<Result, String>("InputRes")
        );
        trgtBaseCol.setCellValueFactory(
                new PropertyValueFactory<Result, String>("targetBase")
        );
        outputCol.setCellValueFactory(
                new PropertyValueFactory<Result, String>("output")
        );

        ObservableList<Result> fileOutput = readFile();
        this.data.addAll(fileOutput);

        tableResult.setItems(fileOutput);
        tableResult.getColumns().addAll(srcBaseCol, inputCol, trgtBaseCol, outputCol);
    }

    /**
     * Reads the content of the file attribut and generates a ObservableList objects containing Result objects.
     *
     * @return a ObservableList objects containing Result objects.
     */
    private ObservableList<Result> readFile() {
        ObservableList<Result> fileOutput = FXCollections.observableArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            String line = br.readLine();
            while (line != null) {
                String[] temp = line.split(", ");
                fileOutput.add(0, new Result(temp[0], temp[1], temp[2], temp[3]));
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            clearHistory(new ActionEvent());
        } catch (IOException e) {
            e.printStackTrace();
            clearHistory(new ActionEvent());
        }
        return fileOutput;
    }


    /**
     * Initializes both combo boxes for the source / target numerical system.
     */
    private void initComboboxes() {
        for (int i = 2; i < Mode.MAX_BASE; i++) {
            this.cmbBxSource.getItems().add(new Label("Base-" + i));
        }
        this.cmbBxSource.getSelectionModel().select(8);
        for (int i = 2; i < Mode.MAX_BASE; i++) {
            this.cmbBxTarget.getItems().add(new Label("Base-" + i));
        }
        this.cmbBxTarget.getSelectionModel().select(0);
    }

    /**
     * Processes the given information provided by the user and generates a result which will be displayed in the
     * table view.
     *
     * @param e Keyevent to distinguish between a normal input and the enter key
     */
    @FXML
    public void calc(KeyEvent e) {
        try {
            if (e.getCode().equals(KeyCode.ENTER)) {
                // Gather input
                int srcBase = this.cmbBxSource.getSelectionModel().getSelectedIndex() + 2;
                int trgtBase = this.cmbBxTarget.getSelectionModel().getSelectedIndex() + 2;
                int blockSize = Integer.parseInt(removePrefix(this.txtFldBlock.getText()));

                // generate output
                converter.setMode(new Mode(srcBase, trgtBase, blockSize));
                data.add(0, new Result(this.cmbBxSource.getSelectionModel().getSelectedItem().getText(),
                        removePrefix(this.txtFldInput.getText()),
                        this.cmbBxTarget.getSelectionModel().getSelectedItem().getText(),
                        converter.conv(removePrefix(this.txtFldInput.getText()))));
                tableResult.getItems().setAll(data);

                // udate file / gui
                saveToFile(this.file, this.data);
                this.txtFldInput.setText(INPUT_PREFIX);
                this.txtFldInput.positionCaret(INPUT_PREFIX.length());
            }
        } catch (InvalidInputException ex) {
            showMessage("Error", ex.getMessage());
        } catch (StringIndexOutOfBoundsException | NumberFormatException ex) {
            showMessage("Error", "Invalid Input");
        } catch (NullPointerException ex) {
            showMessage("Error", "Invalid base as input");
        }
    }

    @FXML
    public void showTutorial(ActionEvent event) {
        showMessage("Description", TUTORIAL_DESCR);
    }

    /**
     * Opens a dialog window with the given title and message
     * @param title title of the dialog window
     * @param message message of the dialog window
     */
    private void showMessage(String title, String message) {
        StackPane stackpane = this.stackPane;

        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(title));
        TextFlow flow = new TextFlow(new Text(message));
        flow.setTextAlignment(TextAlignment.JUSTIFY);
        content.setBody(flow);
        content.setMaxWidth(stackpane.getWidth() * 0.9);

        this.stackPane.setDisable(false);
        JFXDialog dialog = new JFXDialog(this.stackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Okay");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
                stackpane.setDisable(true);
            }
        });

        button.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.RAISED);
        button.setPrefHeight(32);
        content.setActions(button);
        dialog.show();
    }

    /**
     * Saves the given list to the given file
     * @param file file the list will be saved to
     * @param lst list of Results to save
     */
    private void saveToFile(File file, ObservableList<Result> lst) {
        StringBuilder lstString = new StringBuilder();
        lstString.append(lst.get(0).getSrcBase() + ", ");
        lstString.append(lst.get(0).getInputRes() + ", ");
        lstString.append(lst.get(0).getTargetBase() + ", ");
        lstString.append(lst.get(0).getOutput());

        try (Writer outputStream = new BufferedWriter(new FileWriter(file, true))) {
            outputStream.write(lstString + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes any kind prefixes used as constants
     *
     * @param str String to remove the String from
     * @return String without a prefix
     * @throws NumberFormatException
     */
    private String removePrefix(String str) throws NumberFormatException {
        String temp = str;
        if (str.startsWith(INPUT_PREFIX)) {
            temp = str.substring(INPUT_PREFIX.length());
        } else if (str.startsWith(BLOCK_PREFIX)) {
            temp = str.substring(BLOCK_PREFIX.length());
        }
        return temp.trim();
    }

    @FXML
    void clearHistory(ActionEvent event) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.close();
        this.data.clear();
        tableResult.setItems(FXCollections.observableArrayList());
    }

    @FXML
    void endProgramm(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void explainConversion(ActionEvent event) {
        openWebpage("https://www.electronics-tutorials.ws/binary/bin_2.html");
    }

    @FXML
    void openRepo(ActionEvent event) {
        openWebpage("https://github.com/derMacon/NumConverter");
    }

    /**
     * Opens a given URL in the users default browser
     *
     * @param url url to open in the browser
     */
    private void openWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            showMessage("Error", e.getMessage());
        }
    }

    @FXML
    public void displayDarkMode(ActionEvent event) {
        this.txtFldBlock.getScene().getStylesheets().add(DARK_MODE_CSS_TITLE);
    }

    @FXML
    public void displayLightMode(ActionEvent event) {
        this.txtFldBlock.getScene().getStylesheets().removeAll(DARK_MODE_CSS_TITLE);
    }
}
