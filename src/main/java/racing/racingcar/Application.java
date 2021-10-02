package racing.racingcar;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        Validation valid = new Validation();

        String carList = "";
        int count = 0;

        while(true) {
            System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분");
            carList = scanner.next();
            System.out.println("시도할 횟수는 몇 회인가요?");
            count = scanner.nextInt();
            try {
                valid.inputValidation(carList, count);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            // 검증 완료 시 break
            break;
        }

        // 멤버 파싱 및 Car List에 등록
        // TODO
        String[] carArray = carList.split(",");


//        System.out.println("실행 결과");
//        for (int i = 0; i < count; i++) {
//
//        }

    }
}
