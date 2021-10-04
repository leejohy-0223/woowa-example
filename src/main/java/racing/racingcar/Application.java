package racing.racingcar;

import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        Validation valid = new Validation();

        String carList = "";
        List<Car> validCarList;
        int count = 0;

        while(true) {
            System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분");
            carList = scanner.next();
            System.out.println("시도할 횟수는 몇 회인가요?");
            count = scanner.nextInt();
            try {
                validCarList = valid.inputValidation(carList, count);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            // 검증 완료 시 break
            break;
        }
        System.out.println("\n실행 결과");
        // TODO
        new ExecuteGame(validCarList, count).start();
    }
}
