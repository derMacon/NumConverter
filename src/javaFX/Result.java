package javaFX;

import javafx.beans.property.SimpleStringProperty;

/**
 * Class needed for the TableView. This fx component works with reflections and it's necessary to implement the
 * Getters for this class, because these are the only interface with the tableView itself.
 */
public class Result {
    SimpleStringProperty srcBase;
    SimpleStringProperty inputRes;
    SimpleStringProperty targetBase;
    SimpleStringProperty output;

    public Result(String srcBase, String inputRes, String targetBase, String output) {
        this.srcBase = new SimpleStringProperty(srcBase);
        this.inputRes = new SimpleStringProperty(inputRes);
        this.targetBase = new SimpleStringProperty(targetBase);
        this.output = new SimpleStringProperty(output);
    }

    public String getSrcBase() {
        return srcBase.get();
    }

    public String getInputRes() {
        return inputRes.get();
    }

    public String getTargetBase() {
        return targetBase.get();
    }

    public String getOutput() {
        return output.get();
    }

    public void setSrcBase(String srcBase) {
        this.srcBase.set(srcBase);
    }

    public void setInputRes(String inputRes) {
        this.inputRes.set(inputRes);
    }

    public void setTargetBase(String targetBase) {
        this.targetBase.set(targetBase);
    }

    public void setOutput(String output) {
        this.output.set(output);
    }
}
