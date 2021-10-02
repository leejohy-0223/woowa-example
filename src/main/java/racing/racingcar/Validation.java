package racing.racingcar;

import java.util.ArrayList;
import java.util.List;

public class Validation {

    public List<Car> inputValidation(String cars, int count) {
        
        // 자동차 입력 검증 & 여기 넘기기
        // TODO
        List<Car> carList = carValidation(cars);

        // 입력 숫자 검증
        countValidation(count);

        return carList;
    }

    private List<Car> carValidation(String cars) {
        String[] split = cars.split(",");
        
        List<Car> carList = new ArrayList<>();
        List<String> carChk = new ArrayList<>();

        // 공백 문자 허용 x 확인
        for (String s : split) {
            blankValidation(s);
            overlapValidation(carChk, s);

            carChk.add(s);
            carList.add(new Car(s));
        }

        return carList;
    }

    private void overlapValidation(List<String> carChk, String s) {
        if(carChk.contains(s)) {
            throw new IllegalStateException("[ERROR] 중복 이름 허용 안한다");
        }
    }

    private void blankValidation(String s) {
        if(s.equals("") || s.equals(" ")) {
            throw new IllegalStateException("[ERROR] 공백 허용 안한다.");
        }
    }

    public void countValidation(int count) {

    }
}
