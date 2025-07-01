package at.fhj.msd;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

            assertEquals(false, logic.Game_Won(arr));
            assertEquals(true, logic.Game_Won(arr2));
            assertEquals(true, logic.Game_Won(arr3));
            assertEquals(true, logic.Game_Won(arr4));
      }

            



}
