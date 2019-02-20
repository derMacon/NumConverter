/**
 * Class to gather information about the mode of conversion. Used in the Converter to distinguish the source and
 * target numerical systems of the number.
 */
public class Mode {

    private final int sourceBase;
    private final int targetBase;
    private final int blockSize;

    /**
     * Default occupation -> Mode converting Decimal to binary
     */
    public Mode() {
        this.sourceBase = 10;
        this.targetBase = 2;
        this.blockSize = 8;
    }

    public Mode(int sourceBase, int targetBase, int blockSize) {
        this.sourceBase = sourceBase;
        this.targetBase = targetBase;
        this.blockSize = blockSize;
    }

    public int getSourceBase() {
        return sourceBase;
    }

    public int getTargetBase() {
        return targetBase;
    }

    public int getBlockSize() {
        return blockSize;
    }

    /**
     * Generates the mode from a given String input, used when the user edits the mode in the main method
     * @param input String input from the user
     * @return mode holding the information provided by the user
     */
    public static Mode genMode(String input) {
        String[] arr = input.split(" ");
        int inputBase = 0, outputBase = 0, blockSize = 0;
        for (int i = 0; i < arr.length; i++) {
            switch (i) {
                case 0:
                    inputBase = Integer.parseInt(arr[i]);
                    break;
                case 1:
                    outputBase = Integer.parseInt(arr[i]);
                    break;
                case 2:
                    blockSize = Integer.parseInt(arr[i]);
                    break;
            }
        }
        return new Mode(inputBase, outputBase, blockSize);
    }

    @Override
    public String toString() {
        return getName(this.sourceBase) + " -> " + getName(this.targetBase) + (0 == this.blockSize ? "" :
                " [BlockSize = " + this.blockSize + "]");
    }

    /**
     * Generates the name used to display the the numerical system
     * @param base base of the num system
     * @return name of the num system
     */
    private String getName(int base) {
        String output;
        switch (base) {
            case 2:
                output = "binary";
                break;
            case 8:
                output = "octal";
                break;
            case 10:
                output = "decimal";
                break;
            case 16:
                output = "hex";
                break;
            default:
                output = "base" + base;
        }
        return output;
    }

}
