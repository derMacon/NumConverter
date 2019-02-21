import java.util.Scanner;

public class Main {

    private static final String USAGE = "- type a number to convert with given mode\n- e to edit the current mode\n" +
            "- x to exit";
    private static final String EDIT_OPTIONS = "fb sb b\nwhere\n  fb - first base (base of the given input number)" +
            "\n  sb - second base (base of the numerical system from the target)\n  b  - blocksize (minimum count " +
            "of digits to display number)\ne.g. decimal to binary with " +
            "blocksize 8-> " +
            "10 2 8: ";


    public static void main(String[] args) {
        boolean userContinue = true;
        Scanner in = new Scanner(System.in);
        System.out.println("Numerical converter V1.0\n" + USAGE);

        Mode mode = new Mode();
        String curr = null;
        while (userContinue) {
            try {
                System.out.print(mode + ": ");
                curr = in.nextLine();
                // user input longer than one word / number
                if (!curr.matches(".*\\S")) {
                    System.out.println(USAGE);
                } else if (curr.toLowerCase().equals("x")) {
                    System.exit(0);
                } else if (curr.toLowerCase().equals("e")) {
                    System.out.print(EDIT_OPTIONS);
                    curr = in.nextLine();

                    // todo regex for invalid user input
//                    while (!curr.matches("[0-9]*\b[0-9]*\b[0-9]*")) {
//                        System.out.print("Try again: ");
//                        curr = in.nextLine();
//                    }

                    mode = Mode.genMode(curr);
                } else {
                    // actual conversion process
                    System.out.println(Converter.conv(curr, mode));
                }
            } catch (RuntimeException e) {
                System.out.println(USAGE);
            }
        }

    }


}
