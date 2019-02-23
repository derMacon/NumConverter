package logic;

/**
 * Class to gather information about the mode of conversion. Used in the Converter to distinguish the source and
 * target numerical systems of the number.
 */
public class Mode {
    private final static int MIN_BASE = 2;
    private final static int MAX_BASE = Alphabet.values().length;
    private final static int SRC_BASE_IDX = 0;
    private final static int TRGT_BASE_IDX = 1;
    private final static int BLOCKSIZE_IDX = 2;

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

    public Mode(int sourceBase, int targetBase) {
        this(sourceBase, targetBase, 0);
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
     *
     * @param input String input from the user
     * @return mode holding the information provided by the user
     */
    public static Mode genMode(String input) throws InvalidModeException {
        String[] arr = input.trim().split(" ");
        Integer inputBase = null, outputBase = null, blockSize = 1;

        if(arr.length < 2 || arr.length < 3) {
            throw new InvalidModeException("Wrong number of args");
        }

        try {
            for (int i = 0; i < arr.length; i++) {
                switch (i) {
                    case SRC_BASE_IDX:
                        inputBase = Integer.parseInt(arr[i]);
                        break;
                    case TRGT_BASE_IDX:
                        outputBase = Integer.parseInt(arr[i]);
                        break;
                    case BLOCKSIZE_IDX:
                        blockSize = Integer.parseInt(arr[i]);
                        break;
                }
            }
        } catch (NumberFormatException e) {
            throw new InvalidModeException("Invalid number in editing parameters");
        }

        if (null == inputBase || null == outputBase
                || MIN_BASE > inputBase || MAX_BASE < inputBase
                || MIN_BASE > outputBase || MAX_BASE < outputBase) {
            throw new InvalidModeException("Specified base is out of bound " +
                    "(min. base: " + MIN_BASE + ", max. base: " + MAX_BASE + ")");
        }

        return new Mode(inputBase, outputBase, blockSize);
    }

    @Override
    public String toString() {
        return getName(this.sourceBase) + " -> " + getName(this.targetBase) + (1 >= this.blockSize ? "" :
                " [BlockSize = " + this.blockSize + "]");
    }

    /**
     * Generates the name used to display the the numerical system
     *
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
