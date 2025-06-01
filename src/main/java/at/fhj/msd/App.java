package at.fhj.msd;

import at.fhj.msd.Logic.Logic;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String[][] Grid = {{"","",""},
                           {"","",""},
                           {"","",""}};

        System.out.println(Logic.Game_Won(Grid));  

    }
}
