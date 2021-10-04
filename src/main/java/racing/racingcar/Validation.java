package racing.racingcar;

import racing.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Validation {

    private static final String NUMBER_REGULAR = "^[0-9]*$";
    public static final Pattern validPattern = Pattern.compile(NUMBER_REGULAR);
    List<Car> carList = new ArrayList<>();
    List<String> carChk = new ArrayList<>();

    public List<Car> inputValidation(String cars, int count) {
        
        // 자동차 입력 검증 & 이 값 넘기기
        List<Car> carList = carValidation(cars);
        // 입력 숫자 검증
        countValidation(count);

        return carList;
    }

    private List<Car> carValidation(String cars) {
        String[] split = cars.split(",");
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
            throw new InvalidInputException("중복 이름 허용 안한다");
        }
    }

    private void blankValidation(String s) {
        if(s.equals("") || s.equals(" ")) {
            throw new InvalidInputException("공백 허용 안한다.");
        }
    }

    public void countValidation(int count) {
        if(count < 1) {
            throw new InvalidInputException("0이상의 자연수만 허용합니다.");
        }
    }
}
