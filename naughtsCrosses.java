import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class naughtsCrosses {

    //Prints current board
    static void printBoard(char board[][]) {
        System.out.println("\n" + board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("-+-+-");
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("-+-+-");
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2] + "\n");
    }

    static void playerTurn(char matrix[][], char letter) {
        Scanner keyboard = new Scanner(System.in);
        int x, y;

        //Choose coordinate for move
        while(true){
            System.out.print("x coordinate: ");
            x = keyboard.nextInt();
            System.out.print("y coordinate: ");
            y = keyboard.nextInt();
            if(validMove(matrix, x, y, 1) == true) {
                break;
            }
        }
        //Makes valid move
        matrix[x - 1][y - 1] = letter;
        printBoard(matrix);
        //keyboard.close();
    }

    static void computerTurn(char matrix[][], char letter) {
        int x, y;

        //Choose coordinate for move
        while(true){
            x = ThreadLocalRandom.current().nextInt(1, 4);
            y = ThreadLocalRandom.current().nextInt(1, 4);
            //System.out.println(x + " " + y);
            if(validMove(matrix, x, y, 2) == true) {
                break;
            }
        }
        //Makes valid move
        matrix[x - 1][y - 1] = letter;
        printBoard(matrix);
    }

    //Selects whose move it is
    static char getTurn(int turn) {
            
        if(turn % 2 == 1) {
            return 'X';
        } else {
            return 'O';
        }
    }

    //Checks if the move was valid
    static boolean validMove(char[][] matrix, int xCoord, int yCoord, int gameMode) {

        if(xCoord <= matrix.length && xCoord > 0 && yCoord <= matrix.length && yCoord > 0) {
            if(matrix[xCoord - 1][yCoord - 1] == ' ') {
                return true;
            } else if(gameMode == 2) {
                System.out.println("Please make a valid move - Choose from an empty space");
                System.out.println();
            }
        } else if(gameMode == 2) {
            System.out.println("Please make a valid move - Coordinates range from [1,3]");
            System.out.println();
        }

        return false;

    }

    //Checks if anyone has won
    static boolean winCheck(char[][] matrix, char letter) {

        if(rowCheck(matrix, letter) == true) {
            return true;
        } else if(colCheck(matrix, letter) == true) {
            return true;
        } else if(diagCheck(matrix, letter) == true) {
            return true;
        } else {
            return false;
        }
    } 

    static boolean rowCheck(char[][] matrix, char letter) {

        for(int i = 0; i < matrix.length; i++) {
            int count = 0;
            for(int j = 0; j < matrix.length; j++) {
                if(matrix[i][j] == letter) {
                    count++;
                }
            }
            if(count == 3) {
                return true;
            }
        }

        return false;

    }

    static boolean colCheck(char[][] matrix, char letter) {

        for(int i = 0; i < matrix.length; i++) {
            int count = 0;
            for(int j = 0; j < matrix.length; j++) {
                if(matrix[j][i] == letter) {
                    count++;
                }
            }
            if(count == 3) {
                return true;
            }
        }

        return false;

    }

    static boolean diagCheck(char[][] matrix, char letter) {

        if(matrix[0][0] == letter && matrix[1][1] == letter && matrix[2][2] == letter) {
            return true;
        } else if(matrix[0][2] == letter && matrix[1][1] == letter && matrix[2][0] == letter) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void main(String[] arg) {
        
        Scanner keyboard = new Scanner(System.in);
    
        System.out.println("What game mode do you want to play?");
	
        while(true) {
      
            char matrix[][] = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
            int turn = 1;
            char letter;

            System.out.println("\n-=-=-=-=-=-=-=-=-=-\n");
            System.out.println("1) Single player");
            System.out.println("2) Multiplayer");
            System.out.println("3) Quit");
            int gameMode = keyboard.nextInt();

            System.out.println("The x and y coordinates range from [1,3]");
            printBoard(matrix);

            //Single player
            if(gameMode == 1) {
                while(true) {
                    //Selects whose turn it is
                    letter = getTurn(turn);

                    if(letter == 'X') {
                        System.out.println("Please make your move");  
                        playerTurn(matrix, letter);
                    } else {
                        computerTurn(matrix, letter);
                    }

                    //Win check
                    if(turn >= 5){
                        if(winCheck(matrix, letter) == true) {
                            if(letter == 'X') {
                                System.out.println("Congratulations, you have WON!");
                            } else {
                                System.out.println("You LOSE!");
                            }
                            break;
                        } else {
                            turn++;
                        }
                    } else {
                        turn++;
                    }
                    if(turn == 10) {
                        System.out.println("Game is a DRAW!");
                        break;
                    }
                }
              //Multiplayer  
            } else if(gameMode == 2) {
                    while(true) {
                    //Selects whose turn it is
                    letter = getTurn(turn);
                    System.out.println("Team " + letter + " please enter coordinates for position of '" + letter + "'");
                    playerTurn(matrix, letter);

                    //Win check
                    if(turn >= 5){
                        if(winCheck(matrix, letter) == true) {
                            System.out.println("Congratulations team " + letter + ", you have WON!");
                            break;
                        } else {
                            turn++;
                        }
                    } else {
                        turn++;
                    }
                    if(turn == 10) {
                        System.out.println("Game is a DRAW!");
                        break;
                    }
                }

              //Quit  
            } else if(gameMode == 3) {
                System.out.println("\nGoodbye!");
                break;
            }

        }

        keyboard.close();
    }
}