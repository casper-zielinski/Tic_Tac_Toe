package at.fhj.msd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import at.fhj.msd.Logic.Logic;

public class LogicTest {

      @Test
      @DisplayName("Check Winner")
      public void winnerchecker() {
            Logic logic = new Logic();

            String[][] arr = {{"X","O","X"},
                              {"X","O","X"},
                              {"O","X","O"}};

            String[][] arr2 ={{"O","O","X"},
                              {"X","O","X"},
                              {"O","X","O"}};

            String[][] arr3 ={{"O","O","O"},
                              {"X","O","X"},
                              {"O","X","O"}};

            String[][] arr4 ={{"X","X","X"},
                              {"X","O","X"},
                              {"O","X","O"}};
            String[][] winX_TopRow = {
                              {"X", "X", "X"},
                              {"O", "", "O"},
                              {"", "", ""}};

            String[][] winO_LeftCol = {
                              {"O", "X", "X"},
                              {"O", "", "X"},
                              {"O", "", ""}};

            String[][] winX_Diagonal1 = {
                              {"X", "O", ""},
                              {"O", "X", ""},
                              {"", "", "X"}};

            String[][] winO_Diagonal2 = {
                              {"X", "", "O"},
                              {"X", "O", ""},
                              {"O", "", "X"}};

            String[][] tieGame = {
                              {"X", "O", "X"},
                              {"X", "O", "O"},
                              {"O", "X", "X"}};


            assertFalse(logic.Game_Won(arr));
            assertTrue(logic.Game_Won(arr2));
            assertTrue(logic.Game_Won(arr3));
            assertTrue(logic.Game_Won(arr4));
            assertTrue(logic.Game_Won(winX_TopRow));
            assertTrue(logic.Game_Won(winO_LeftCol));
            assertTrue(logic.Game_Won(winX_Diagonal1));
            assertTrue(logic.Game_Won(winO_Diagonal2));
      }

            



}
