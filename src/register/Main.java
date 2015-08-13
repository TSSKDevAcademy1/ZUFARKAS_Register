package register;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by jaro on 3.2.2014.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Register register = new ArrayRegister(20);


        ConsoleUI ui = new ConsoleUI(register);
        ui.run();
    }
}




