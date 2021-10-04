package racing.racingcar;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        ExecuteGame executeGame = new ExecuteGame(scanner);
        executeGame.start();
        scanner.close();
    }
}
