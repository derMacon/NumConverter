package javaFX;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logic.Converter;
import logic.InvalidInputException;
import logic.Mode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class that handles every gui interaction with the user.
 */
public class FXMLNumConverterController implements Initializable {
    private static final String INPUT_PREFIX = "  Input: ";
    private static final String BLOCK_PREFIX = "  Blocksize: ";

    private static final int DEFAULT_SRC_BASE = 10;
    private static final int DEFAULT_TARGET_BASE = 2;
    private static final int DEFAULT_BLOCKSIZE = 8;

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
    private JFXPopup pppSourceBase;

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
        this.txtFldBlock.setText(BLOCK_PREFIX + String.valueOf(DEFAULT_BLOCKSIZE));
        this.txtFldInput.setText(INPUT_PREFIX);
        initComboboxes();

        this.converter = new Converter(new Mode(DEFAULT_SRC_BASE, DEFAULT_TARGET_BASE, DEFAULT_BLOCKSIZE));
    }

    /**
     * Initializes the table with a given file input
     * // todo implement file input
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

        // todo write parser for file
        ObservableList<Result> fileOutput = FXCollections.observableArrayList(new Result("Test src2",
                "test input", "test target", "test output"));
        this.data.addAll(fileOutput);

        tableResult.setItems(fileOutput);
        tableResult.getColumns().addAll(srcBaseCol, inputCol, trgtBaseCol, outputCol);
    }


    /**
     * Initializes both combo boxes for the source / target numerical system.
     */
    private void initComboboxes() {
        for (int i = 2; i < Mode.MAX_BASE; i++) {
            this.cmbBxSource.getItems().add(new Label("Base-" + i));
        }
        // todo remove before final commit
        this.cmbBxSource.getSelectionModel().select(8);
        for (int i = 2; i < Mode.MAX_BASE; i++) {
            this.cmbBxTarget.getItems().add(new Label("Base-" + i));
        }
        // todo remove before final commit
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
                int srcBase = this.cmbBxSource.getSelectionModel().getSelectedIndex() + 2;
                int trgtBase = this.cmbBxTarget.getSelectionModel().getSelectedIndex() + 2;
                int blockSize = Integer.parseInt(removePrefix(this.txtFldBlock.getText()));

                converter.setMode(new Mode(srcBase, trgtBase, blockSize));
                data.add(0, new Result(this.cmbBxSource.getSelectionModel().getSelectedItem().getText(),
                        this.txtFldInput.getText(), this.cmbBxTarget.getSelectionModel().getSelectedItem().getText(),
                        converter.conv(removePrefix(this.txtFldInput.getText()))));
                tableResult.getItems().setAll(data);

                saveToFile(this.file, this.data);
            }
        } catch (InvalidInputException ex) {
            // todo
        } catch (NullPointerException ex) {
            // todo invalid base input (not selected)
        }
    }

    private void saveToFile(File file, ObservableList<Result> lst) {
        StringBuilder lstString = new StringBuilder();
        lstString.append(lst.get(0).getSrcBase() + ", ");
        lstString.append(lst.get(0).getInputRes() + ", ");
        lstString.append(lst.get(0).getTargetBase() + ", ");
        lstString.append(lst.get(0).getOutput());

        try (Writer outputStream = new BufferedWriter(new FileWriter(file, true))) {
            outputStream.write(lstString+ "\n");
        } catch (IOException e) {
            System.out.println("error");
            // todo
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
        return temp;
    }

    @FXML
    void clearHistory(ActionEvent event) {

    }

    @FXML
    void endProgramm(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void explainConversion(ActionEvent event) {

    }
}
