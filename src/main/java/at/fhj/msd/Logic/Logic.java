package at.fhj.msd.Logic;

import java.util.ArrayList;
import java.util.List;
/**
 * The Logic Class has the Logic behing this Project, if the Winning Condition is met, if unallowed Charackters exists and so on
 */
public class Logic {

      public String X = "X";
      public String O = "O";
      public List<String> Checker = new ArrayList<>();

      public static String[][] clearArray (String[][] Grid)
      {
            for (int i = 0; i < 3 ; i++)
            {
                  for (int j = 0; j < 3; j++)
                  {
                        Grid[i][j] = "";
                  } 
            }
            return Grid;
      }


      /**
       * Checks if unallowed Charackters exist, like: "Ü","?","Y" and so on
       * @param Grid
       * @return true if there are unallowed Chars, false otherwise
       */
      public boolean NotAllowedChars(String[][] Grid)
      {
            for (int i = 0; i < 3 ; i++)
            {
                  for (int j = 0; j < 3; j++)
                  {
                        Checker.add(Grid[i][i]);
                         if (Grid[i][j] == null)
                        {
                              return true;
                        }
                  } 
            }
            if (Checker.stream().allMatch(values -> "X".equals(values) || "O".equals(values) || "".equals(values)))
            {
                  Checker.clear();
                  return false;
            }
            else Checker.clear(); return true;
      }

      /**
       * Checks if the Grid hast only X and O as thei Values
       * @param Grid
       * @return true if The Grid only Has X and O Values, false otherwise 
       */
      public boolean OnlyX_O(String[][] Grid)
      {
            for (int i = 0; i < 3 ; i++)
            {
                  for (int j = 0; j < 3; j++)
                  {
                      String xy = Grid[i][j];
                      if (!(xy.equals("X") || xy.equals("O")))
                      {
                        return false;
                      }
                  } 
            }
            return true;
      }

      /**
       * Checks who won the Game
       * @param Grid
       * @return true if a party won the game, false otherwise (only checks if someone won, not who [yet])
       */
      public boolean Game_Won(String[][] Grid)
      {
          return (Rows_Same(Grid) || Colums_Same(Grid) || Dialoganly_Same(Grid)) && !NotAllowedChars(Grid);
      }

      public boolean Game_Tied(String[][] Grid)
      {
            return OnlyX_O(Grid) && !Game_Won(Grid);
      }

      /**
       * Checks Diagonaly
       */
      private boolean Dialoganly_Same( String[][] Grid) {
            

            String mid = Grid[1][1];
            
            if (!mid.equals("") &&
                  ((mid.equals(Grid[0][0]) && mid.equals(Grid[2][2])) ||
                  (mid.equals(Grid[0][2]) && mid.equals(Grid[2][0])))) return true;
                                
            else return false;
      }

      /**
       * Checks the Colums
       * 
       */
      private boolean Colums_Same( String[][] Grid) {
            for (int i = 0; i < Grid.length; i++)
            {
                  String t = Grid[i][0];
                  if (!(t.equals("")) && t.equals(Grid[i][1]) && t.equals(Grid[i][2]))
                  {
                        return true;
                  }
            }
            return false;
      }

      /**
       * Checks the Rows
       */
      private boolean Rows_Same( String[][] Grid) {
         for (int i = 0; i < Grid.length; i++)
            {
                  String t = Grid[0][i];
                  if (!(t.equals("")) && t.equals(Grid[1][i]) && t.equals(Grid[2][i]))
                  {
                        return true;
                  }
            }
            return false;
      }



}
