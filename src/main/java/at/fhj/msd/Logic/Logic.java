package at.fhj.msd.Logic;

import java.util.ArrayList;
import java.util.List;
/**
 * The Logic Class has the Logic behing this Project, if the Winning Condition is met, if unallowed Charackters exists and so on
 */
public class Logic {

      public String[][] Grid;
      public String X = "X";
      public String O = "O";
      public String[] Possible_Anwsers = {"X","O"};
      public static List<String> Checker = new ArrayList<>();


      /**
       * Checks if unallowed Charackters exist, like: "Ü","?","Y" and so on
       * @param Grid
       * @return true if there are unallowed Chars, false otherwise
       */
      public static boolean NotAllowedChars(String[][] Grid)
      {
            for (int i = 0; i < 3 ; i++)
            {
                  for (int j = 0; j < 3; j++)
                  {
                        Checker.add(Grid[i][i]);
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
      public static boolean OnlyX_O(String[][] Grid)
      {
            for (int i = 0; i < 3 ; i++)
            {
                  for (int j = 0; j < 3; j++)
                  {
                        Checker.add(Grid[i][i]);
                  } 
            }
            if (Checker.stream().allMatch(values -> "X".equals(values) || "O".equals(values)))
            {
                  Checker.clear();
                  return true;
            }
            else Checker.clear(); return false;
      }

      /**
       * Checks who won the Game
       * @param Grid
       * @return true if a party won the game, false otherwise (only checks if someone won, not who [yet])
       */
      public static boolean Game_Won(String[][] Grid)
      {
          return (Rows_Same(Grid) || Colums_Same(Grid) || Dialoganly_Same(Grid));
      }

      /**
       * Checks Diagonaly
       */
      private static boolean Dialoganly_Same( String[][] Grid) {
            

            for (int i = 0; i < 3; i++)
            {
                  Checker.add(Grid[i][i]);
            }

            if ((!(Checker.contains("X") && Checker.contains("O"))) && !Checker.contains(""))
            {
                  Checker.clear();
                  return true;
            }
            else{
                  Checker.clear();
                  for (int i = 0; i < 3; i++)
                  {
                        Checker.add(Grid[2-i][i]);
                  }

                  if ((!(Checker.contains("X") && Checker.contains("O"))) && !Checker.contains(""))
                  {
                        Checker.clear();
                        return true;
                  }
            }
            Checker.clear();
            return false;
      }

      /**
       * Checks the Colums
       */
      private static boolean Colums_Same( String[][] Grid) {
            for (int i = 0; i < 3; i++)
            {
                  for (int j = 0; j < 3; j++)
                  {
                        Checker.add(Grid[j][i]);
                  }

                  if ((!(Checker.contains("X") && Checker.contains("O"))) && !Checker.contains(""))
                  {
                        Checker.clear();
                        return true;
                  }
                  else Checker.clear();
            }
            return false;
      }

      /**
       * Checks the Rows
       */
      private static boolean Rows_Same( String[][] Grid) {
         for (int i = 0; i < 3; i++)
            {
                  for (int j = 0; j < 3; j++)
                  {
                        Checker.add(Grid[i][j]);
                  }

                  if ((!(Checker.contains("X") && Checker.contains("O"))) && !Checker.contains(""))
                  {
                        Checker.clear();
                        return true;
                  }
                  else Checker.clear();
            }
            return false;
      }



}
