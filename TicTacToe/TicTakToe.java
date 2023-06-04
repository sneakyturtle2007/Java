import java.io.IOException;
import java.util.*;

class TicTakToe {
    public static String[][] TicTakToeBoard = new String[3][3];
    private static String winner;

    // This method does not take in any parameters and sets every section of the
    // TicTakToeBoard 2D array to "*".
    public TicTakToe() {
        for (int r = 0; r < TicTakToeBoard.length; r++) {
            for (int c = 0; c < TicTakToeBoard[r].length; c++) {
                TicTakToeBoard[r][c] = "*";
            }
        }
    }

    // Converts the String coordinates and uses them to change the specific row and
    // col to "X"
    public boolean updateBoard(String move) {

        int col = Integer.parseInt(move.substring(2));
        int row = Integer.parseInt(move.substring(0, 1));
        if (TicTakToeBoard[row][col] == "*") {
            TicTakToeBoard[row][col] = "X";
            return true;
        } else {
            System.out.println("Not a valid coor.");
            return false;
        }

    }

    // Checks the rows and cols of the 2D array, for three in a row of an "X" or an
    // "O" and returns a boolean value if there are three in a row of either it also
    // checks the diagonals although with a separate if statement.
    public boolean winTester() {
        int temp = 0;
        boolean ifcheckingX = true;
        for (int z = 0; z < 2; z++) {
            for (int i = 0; i < TicTakToeBoard[0].length; i++) {
                temp = 0;
                for (int k = 0; k < TicTakToeBoard.length; k++) {
                    if (TicTakToeBoard[k][i] == "X" && ifcheckingX) {
                        temp++;
                        if (temp == 3) {
                            winner = "X";
                            return true;
                        }
                    } else if (TicTakToeBoard[k][i] == "O" && !ifcheckingX) {

                        temp++;

                        if (temp == 3) {

                            winner = "O";
                            return true;
                        }
                    }

                }

            }
            ifcheckingX = false;
        }
        ifcheckingX = true;
        for (int z = 0; z < 2; z++) {

            for (int i = 0; i < TicTakToeBoard.length; i++) {
                temp = 0;
                for (int k = 0; k < TicTakToeBoard[i].length; k++) {
                    if (TicTakToeBoard[i][k] == "X" && ifcheckingX) {
                        temp++;

                        if (temp == 3) {
                            winner = "X";
                            return true;
                        }
                    } else if (TicTakToeBoard[i][k] == "O" && !ifcheckingX) {

                        temp++;

                        if (temp == 3) {

                            winner = "O";
                            return true;
                        }
                    }
                }
            }
            ifcheckingX = false;
        }
        if (TicTakToeBoard[0][0].equals("X") && TicTakToeBoard[1][1].equals("X") && TicTakToeBoard[2][2].equals("X") ||
                TicTakToeBoard[0][2].equals("X") && TicTakToeBoard[1][1].equals("X")
                        && TicTakToeBoard[2][0].equals("X")) {
            winner = "X";
            return true;
        } else if (TicTakToeBoard[0][0].equals("O") && TicTakToeBoard[1][1].equals("O")
                && TicTakToeBoard[2][2].equals("O")
                ||
                TicTakToeBoard[0][2].equals("O") && TicTakToeBoard[1][1].equals("O")
                        && TicTakToeBoard[2][0].equals("O")) {
            winner = "O";
            return true;
        }
        return false;
    }

    // Overrides the normal tostring method to properly print out the 2D array.
    public String toString() {
        String result = "";
        for (int r = 0; r < TicTakToeBoard.length; r++) {
            for (int c = 0; c < TicTakToeBoard[r].length; c++) {
                result += TicTakToeBoard[r][c].toString() + " ";
            }
            result += "\n";
        }
        return result;
    }

    // This is just the main method that organizes all of the other methods into one
    // big algorithm.
    public static void main(String[] args) {
        TicTakToe board = new TicTakToe();
        Scanner userinput = new Scanner(System.in);
        String input;
        System.out.println("Let's play!");
        System.out.println(board);
        TicTakToeBot bot = new TicTakToeBot(TicTakToeBoard);
        while (true) {

            if (bot.iftie == true) {
                System.out.println("It's a tie!");
                System.out.println("Would you like to continue?(y:yes n:no): ");
                input = userinput.nextLine();
                if (input == "n") {
                    break;
                } else {
                    for (int r = 0; r < TicTakToeBoard.length; r++) {
                        for (int c = 0; c < TicTakToeBoard[r].length; c++) {
                            TicTakToeBoard[r][c] = "*";
                        }
                    }
                    break;
                }
            }
            System.out.print("Human, enter row, col to move(input: row,col): ");
            input = userinput.nextLine();

            boolean ifvalidmove = board.updateBoard(input);

            if (board.winTester()) {
                System.out.println(board);
                System.out.println(winner + " is the winner!");
                System.out.println("Would you like to continue?(y:yes n:no): ");
                input = userinput.nextLine();
                if (input == "n") {
                    break;
                } else {
                    for (int r = 0; r < TicTakToeBoard.length; r++) {
                        for (int c = 0; c < TicTakToeBoard[r].length; c++) {
                            TicTakToeBoard[r][c] = "*";
                        }
                    }
                }
            } else if (ifvalidmove) {

                bot.close_to_win_checker_and_location(TicTakToeBoard);

                bot.computeMove();
                if (board.winTester()) {
                    System.out.println(board);
                    System.out.println(winner + " is the winner!");
                    System.out.println("Would you like to continue?(y:yes n:no): ");
                    input = userinput.nextLine();
                    if (input == "n") {
                        break;
                    } else {
                        for (int r = 0; r < TicTakToeBoard.length; r++) {
                            for (int c = 0; c < TicTakToeBoard[r].length; c++) {
                                TicTakToeBoard[r][c] = "*";
                            }
                        }
                    }
                }
            }
            System.out.println(board);
        }
        userinput.close();
    }
}

class TicTakToeBot {
    public static String[][] TicTakToeBoard;
    private static boolean firstMove = true;
    private static int close_to_win_location = 0;
    private static boolean ifRow;
    private static boolean ifdiagonal;
    private static int beginning_Location;
    public boolean iftie = false;

    public TicTakToeBot(String[][] current_game) {
        TicTakToeBoard = current_game;

    }

    public void close_to_win_checker_and_location(String[][] current_game) {
        TicTakToeBoard = current_game;
        close_to_win_location = 0;
        int temp = 0;
        int circletemp = 0;
        ifdiagonal = false;
        boolean iffound = false;

        if (!firstMove) {

            for (int i = 0; i < TicTakToeBoard[0].length; i++) {
                temp = 0;
                circletemp = 0;
                for (int k = 0; k < TicTakToeBoard.length; k++) {
                    if (TicTakToeBoard[k][i] == "X") {
                        temp++;
                    } else if (TicTakToeBoard[k][i] == "O") {
                        circletemp++;
                    }
                    if (circletemp + temp != 3 && temp == 2) {

                        ifRow = false;
                        close_to_win_location = i;
                        iffound = true;
                    }
                }

            }
            if (!iffound) {

                for (int i = 0; i < TicTakToeBoard.length; i++) {
                    temp = 0;
                    for (int k = 0; k < TicTakToeBoard[i].length; k++) {
                        if (TicTakToeBoard[i][k] == "X") {
                            temp++;
                        } else if (TicTakToeBoard[i][k] == "O") {
                            circletemp++;
                        }
                        if (circletemp + temp != 3 && temp == 2) {
                            ifRow = true;
                            close_to_win_location = i;
                            iffound = true;

                        }
                    }
                }

            }
            if (!iffound) {
                temp = 0;
                for (int i = 0; i < 3; i++) {

                    if (TicTakToeBoard[i][i] == "X") {
                        temp++;
                    } else if (TicTakToeBoard[i][i] == "O") {
                        circletemp++;
                    }
                    if (circletemp + temp != 3 && temp == 2) {
                        ifdiagonal = true;
                        close_to_win_location = 0;
                        iffound = true;

                    }
                }
            }
            if (!iffound) {
                temp = 0;
                int index = 2;
                for (int i = 2; i >= 0; i--) {
                    if (TicTakToeBoard[index - i][i] == "X") {
                        temp++;
                    } else if (TicTakToeBoard[index - i][i] == "O") {
                        circletemp++;
                    }
                    if (circletemp + temp != 3 && temp == 2) {
                        ifdiagonal = true;
                        close_to_win_location = 2;

                    }
                }
            }

        } else {

            for (int i = 0; i < TicTakToeBoard.length; i++) {
                for (int k = 0; k < TicTakToeBoard[i].length; k++) {
                    if (TicTakToeBoard[i][k] == "X") {
                        beginning_Location += (i * 10 + k);
                    }
                }
            }
            firstMove = false;
        }

    }

    public void computeMove() {

        boolean alreadyplayed = false;
        if (ifdiagonal) {

            int index = 2;
            while (!alreadyplayed) {
                for (int i = 0; i < 3; i++) {
                    if (TicTakToeBoard[i][i].equals("*") && !alreadyplayed) {
                        TicTakToeBoard[i][i] = "O";
                        alreadyplayed = true;
                    }
                }

                for (int i = 2; i >= 0; i--) {

                    if (TicTakToeBoard[index - i][i].equals("*") && !alreadyplayed) {
                        TicTakToeBoard[index - i][i] = "O";
                        alreadyplayed = true;
                    }
                }

            }

        } else if (ifRow) {

            while (!alreadyplayed) {
                for (int i = 0; i < 3; i++) {
                    if (close_to_win_location < 3 && TicTakToeBoard[close_to_win_location][i].equals("*")
                            && !alreadyplayed) {

                        TicTakToeBoard[close_to_win_location][i] = "O";
                        alreadyplayed = true;
                    } else if (close_to_win_location < 3
                            && TicTakToeBoard[close_to_win_location][i].equals("*") && !alreadyplayed) {

                        close_to_win_location--;
                        TicTakToeBoard[close_to_win_location][i] = "O";
                        alreadyplayed = true;

                    } else if (close_to_win_location >= 3) {

                        alreadyplayed = true;
                        iftie = true;
                    }

                }
                close_to_win_location++;

            }

        } else if (ifRow == false) {

            while (!alreadyplayed) {
                for (int i = 0; i < 3; i++) {

                    if (close_to_win_location < 3 && TicTakToeBoard[i][close_to_win_location].equals("*")
                            && !alreadyplayed) {
                        TicTakToeBoard[i][close_to_win_location] = "O";
                        alreadyplayed = true;
                    } else if (close_to_win_location < 3 && !alreadyplayed
                            && TicTakToeBoard[i][close_to_win_location].equals("*")) {

                        close_to_win_location--;
                        TicTakToeBoard[i][close_to_win_location] = "O";
                        alreadyplayed = true;

                    } else if (close_to_win_location >= 3) {

                        alreadyplayed = true;
                        iftie = true;
                    }
                }
                close_to_win_location++;
            }

        } else {

            int randlocation_x = (int) (Math.random() + 0.01) * 2;
            int randlocation_y = (int) (Math.random() + 0.01) * 2;
            while (randlocation_y == beginning_Location / 10 && randlocation_x == beginning_Location % 10) {
                randlocation_x = (int) (Math.random() + 0.01) * 2;
                randlocation_y = (int) (Math.random() + 0.01) * 2;
            }
            TicTakToeBoard[randlocation_y][randlocation_x] = "O";
        }
    }
}