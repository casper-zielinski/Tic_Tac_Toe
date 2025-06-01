package at.fhj.msd;

import at.fhj.msd.Logic.Logic;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String[][] Grid = {{"O","X","O"},
                           {"X","O","O"},
                           {"X","O","X"}};

        System.out.println(Logic.Game_Tied(Grid));  

    }
}
