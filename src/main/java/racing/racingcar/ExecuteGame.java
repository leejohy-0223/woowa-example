package racing.racingcar;

import racing.util.RandomUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExecuteGame {

    List<Car> validCar;
    List<Car> result = new ArrayList<>();

    int count;
    int MAX_RESULT = Integer.MIN_VALUE;

    public ExecuteGame(List<Car> validCar, int count) {
        this.validCar = validCar;
        this.count = count;
    }

    public void start() {
        while(count-- > 0) {
            int size = validCar.size();
            randomExecute(size);
        }

        Collections.sort(validCar, (o1, o2) -> o2.getPosition() - o1.getPosition());

        winnerExtractor();
        winnerPrinter();
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

        if(position == 0) {
            System.out.println(" ");
            return;
        }

        while(position-- > 0) {
            System.out.print("-");
        }
        System.out.println();
    }
}
