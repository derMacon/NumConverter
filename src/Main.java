import java.util.Scanner;

public class Main {

    private static final String BIN_TO_DEC = "Mode: Binary to decimal";
    private static final String DEC_TO_BIN = "Mode: Decimal to binary";
    private static boolean decToBinMode = true;
    private static final int DEFAULT_BASE = 8;

    public static void main(String[] args) {
        boolean userContinue = false;
        String mode = decToBinMode ? DEC_TO_BIN : BIN_TO_DEC;
        Scanner in = new Scanner(System.in);
        System.out.println("Binary / Decimal converter\n");

        String temp = null;
        while(userContinue) {
            System.out.print(mode + " -> ");
            temp = in.next();
            try{
                System.out.println(Converter.convFromDec(Integer.parseInt(in.next()), DEFAULT_BASE));
            } catch (Exception e){
                userContinue = false;
            }
        }

    }



}
