import java.util.Scanner;

public class MazeRunner {

    public static Maze myMap = new Maze();
    public static Scanner input = new Scanner(System.in);
    public static int moves = 0;

    public static void main(String[] args) {

        intro();
        userMove();

    }

    public static void intro() {
        System.out.print("Welcome to Maze Runner!\nThis is your current position:\n");
        myMap.printMap();
    }

    public static String userMove() {

        System.out.print("Where would you like to move? Please press R, L, U, D to move or 'stop' to quit and press enter. ");
        String direction = input.nextLine().toUpperCase();
        if (!myMap.didIWin() && moves < 100) {
            moves++;
            movesSwitchCase(direction);
        } else if (moves >= 100) {
            youLose("moves");
        } else {
            youWin();
        }
        return direction;

    }

    public static void movesSwitchCase(String direction) {
        switch (direction) {
            case "R":
                navigatePit(direction);
                if (myMap.canIMoveRight()) {
                    myMap.moveRight();
                    myMap.printMap();
                } else {
                    myMap.printMap();
                    System.out.println("You've hit a wall!");
                }
                System.out.println("You chose Right.");
                movesCheck();
                userMove();
                break;
            case "L":
                navigatePit(direction);
                if (myMap.canIMoveLeft()) {
                    myMap.moveLeft();
                    myMap.printMap();
                } else {
                    myMap.printMap();
                    System.out.println("You've hit a wall!");
                }
                System.out.println("You chose Left.");
                movesCheck();
                userMove();
                break;
            case "U":
                navigatePit(direction);
                if (myMap.canIMoveUp()) {
                    myMap.moveUp();
                    myMap.printMap();
                } else {
                    myMap.printMap();
                    System.out.println("You've hit a wall!");
                }
                System.out.println("You chose Up.");
                movesCheck();
                userMove();
                break;
            case "D":
                navigatePit(direction);
                if (myMap.canIMoveDown()) {
                    myMap.moveDown();
                    myMap.printMap();
                } else {
                    myMap.printMap();
                    System.out.println("You've hit a wall!");
                }
                System.out.println("You chose Down.");
                movesCheck();
                userMove();
                break;
            case "STOP":
                youLose("giveUp");
                break;
            default:
                System.out.println("Please enter one of the four valid letters.");
                moves--;
                movesCheck();
                userMove();
                break;
        }
    }

    public static void movesCheck() {
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

    public static void navigatePit(String direction) {
        System.out.println(direction);
        if (myMap.isThereAPit(direction)) {
            System.out.print("There's a pit up ahead! Do you want to jump? (y/n) ");
            String response = input.nextLine().toUpperCase();
            if (response.startsWith("Y")) {
                myMap.jumpOverPit(direction);
            } else if (response.startsWith("N")) {
                ;
            } else {
                ;
            }
        } else {
            ;
        }
    }

    public static void youLose(String reason) {
        if (reason.equals("moves")) {
            System.out.println("Sorry, you ran out of moves!");
            System.exit(0);
        } else if (reason.equals("giveUp")) {
            System.out.println("Awh, why did you give up? Play again soon? Goodbye!");
            System.exit(0);
        } else {
            System.out.println("We're not sure what happened.");
            playAgain();
        }
    }

    public static void youWin() {
        System.out.println("Congrats! You won in only +" + moves + " moves!");
    }

    public static void playAgain() {
        System.out.print("You lose! Want to play again? (y/n)");
        String response = input.nextLine().toLowerCase();
        if (response.equals("y")) {
            moves = 0;
            intro();
            userMove();
        } else {
            System.out.println("Play again soon? Goodbye!");
        }
    }
}
