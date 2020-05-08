import java.util.Scanner;

public class MazeRunner {

    public static Scanner input = new Scanner(System.in);
    public static int moves = 0;

    public static void main(String[] args) {

        MazeRunner.intro();

    }

    public static void intro() {
        Maze myMap = new Maze();
        System.out.print("Welcome to Maze Runner!\nThis is your current position:\n");
        myMap.printMap();
        userMove(myMap);
    }

    public static String userMove(Maze myMap) {

        System.out.print("Where would you like to move? Please press R, L, U, D to move or 'stop' to quit and press enter. ");
        String direction = input.nextLine().toUpperCase();
        if (!myMap.didIWin() && moves < 100) {
            moves++;
            movesSwitchCase(direction, myMap);
        } else if (moves >= 100) {
            youLose("moves");
        } else {
            youWin();
        }
        return direction;

    }

    public static void movesSwitchCase(String direction, Maze myMap) {
        switch (direction) {
            case "R":
                if (!navigatePit(direction, myMap)) {
                    if (myMap.canIMoveRight()) {
                        myMap.moveRight();
                        myMap.printMap();
                    } else {
                        myMap.printMap();
                        System.out.println("You've hit a wall!");
                    }
                    System.out.println("You chose Right.");
                }
                movesCheck(myMap);
                userMove(myMap);
                break;
            case "L":
                if (!navigatePit(direction, myMap)) {
                    if (myMap.canIMoveLeft()) {
                        myMap.moveLeft();
                        myMap.printMap();
                    } else {
                        myMap.printMap();
                        System.out.println("You've hit a wall!");
                    }
                    System.out.println("You chose Left.");
                }
                movesCheck(myMap);
                userMove(myMap);
                break;
            case "U":
                if (!navigatePit(direction, myMap)) {
                    if (myMap.canIMoveUp()) {
                        myMap.moveUp();
                        myMap.printMap();
                    } else {
                        myMap.printMap();
                        System.out.println("You've hit a wall!");
                    }
                    System.out.println("You chose Up.");
                }
                movesCheck(myMap);
                userMove(myMap);
                break;
            case "D":
                if (!navigatePit(direction, myMap)) {
                    if (myMap.canIMoveDown()) {
                        myMap.moveDown();
                        myMap.printMap();
                    } else {
                        myMap.printMap();
                        System.out.println("You've hit a wall!");
                    }
                    System.out.println("You chose Down.");
                }
                movesCheck(myMap);
                userMove(myMap);
                break;
            case "STOP":
                youLose("giveUp");
                break;
            default:
                System.out.println("Please enter one of the four valid letters.");
                moves--;
                movesCheck(myMap);
                userMove(myMap);
                break;
        }
    }

    public static void movesCheck(Maze myMap) {
        System.out.println("You've made " + moves + " move(s).");
        if (moves == 50) {
            System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes.");
        } else if (moves == 75) {
            System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
        } else if (moves == 90) {
            System.out.println("DANGER! You have made 90 moves, you only have 10 moves left!");
        } else if (moves >= 100) {
            youLose("moves");
            System.out.println("You lose.");
        }
    }

    public static boolean navigatePit(String direction, Maze myMap) {
        if (myMap.isThereAPit(direction)) {
            System.out.print("There's a pit up ahead! Do you want to jump? (y/n) ");
            String response = input.nextLine().toUpperCase();
            if (response.startsWith("Y")) {
                myMap.jumpOverPit(direction);
                myMap.printMap();
                return true;
            } else if (response.startsWith("N")) {
                return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void youLose(String reason) {
        if (reason.equals("moves")) {
            System.out.println("Sorry, you ran out of moves!");
            playAgain();
        } else if (reason.equals("giveUp")) {
            System.out.println("Awh, why did you give up? Play again soon? Goodbye!");
            playAgain();
        } else {
            System.out.println("We're not sure what happened.");
            playAgain();
        }
    }

    public static void youWin() {
        System.out.println("Congrats! You won in only " + moves + " moves!");
        playAgain();
    }

    public static void playAgain() {
        System.out.print("You lose! Want to play again? (y/n)");
        String response = input.nextLine().toLowerCase();
        if (response.equals("y")) {
            moves = 0;
            intro();
        } else {
            System.out.println("Play again soon? Goodbye!");
            System.exit(0);
        }
    }
}
