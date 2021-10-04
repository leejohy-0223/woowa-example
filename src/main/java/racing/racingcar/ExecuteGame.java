package racing.racingcar;

import racing.util.RandomUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ExecuteGame {

    private final Scanner scanner;
    Validation valid = new Validation();

    List<Car> validCar;
    List<Car> result = new ArrayList<>();

    String carList = "";
    int count = 0;
    int MAX_RESULT = Integer.MIN_VALUE;

    public ExecuteGame(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        while(true) {
            insertCarnameAndCount();
            if (verify()) continue;
            break;
        }
        System.out.println("\n실행 결과");
        randomAndSorting();
        resultUnit();
    }

    private void resultUnit() {
        winnerExtractor();
        winnerPrinter();
    }

    private void insertCarnameAndCount() {
        inputCarName();
        inputCount();
    }

    private void randomAndSorting() {
        while(count-- > 0) {
            int size = validCar.size();
            randomExecute(size);
        }
        Collections.sort(validCar, (o1, o2) -> o2.getPosition() - o1.getPosition());
    }

    private void inputCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        count = scanner.nextInt();
    }

    private void inputCarName() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분");
        carList = scanner.next();
    }

    private boolean verify() {
        try {
            validCar = valid.inputValidation(carList, count);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    private void randomExecute(int size) {
        for(int i = 0; i< size; i++) {
            Car car = validCar.get(i);
            int result = RandomUtils.nextInt(0, 9);

            positionChange(car, result);
            drawRoute(car);
        }
        System.out.println();
    }

    private void winnerExtractor() {
        for (Car car : validCar) {
            int position = car.getPosition();
            if(position >= MAX_RESULT) {
                result.add(car);
                MAX_RESULT = position;
                continue;
            }
            break;
        }
    }

    private void winnerPrinter() {
        System.out.print("최종 우승자: ");

        for (int i = 0; i < result.size(); i++) {
            if(i != result.size() - 1) {
                System.out.print(result.get(i).getName() + ", ");
                return;
            }
            System.out.print(result.get(i).getName());
        }
    }

    private void positionChange(Car car, int result) {
        if(result > 3) {
            car.increasePosition();
        }
    }

    private void drawRoute(Car car) {
        System.out.print(car.getName() + " : ");
        int position = car.getPosition();

        if (zeroChecker(position)) return;

        while(position-- > 0) {
            System.out.print("-");
        }
        System.out.println();
    }

    private boolean zeroChecker(int position) {
        if(position == 0) {
            System.out.println(" ");
            return true;
        }
        return false;
    }
}
