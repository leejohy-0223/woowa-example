package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final int MIN_LENGTH = 3;
    private static final String DIVIDE_ZERO_FORM = "/ 0";
    private static final String OPERATION_REGULAR = "^[+\\-*/\\d]*$";
    //    private static final String OPERATION_REGULAR = "^[+\\-*/[0-9]]*$";
    private static final String OPER_REGULAR = "[+\\-*/]";
    private static final String NUMBER_REGULAR = "^[0-9]*$";
    public static final Pattern validOperationPattern = Pattern.compile(OPERATION_REGULAR);

    private final String value; // 들어온 original 식
    private final String[] splitedValues; // split 이후의 식


    public Validator(String value, String[] splitedValues) {
        this.value = value;
        this.splitedValues = splitedValues;
    }

    public boolean validateAll() {

        if (!validLength()) {
            throw new IllegalStateException("길이가 잘못되었습니다.");
        }

        if (!validateBlank()) {
            throw new IllegalStateException("입력된 문자가 잘못되었습니다. 공백을 하나로 줄이고, 숫자 & 사칙연산자만 사용했는지 확인하세요");
        }

        if (validateDivideZero()) {
            throw new IllegalStateException("0으로 나누지 마세요");
        }

        if (!(validateOrderOp() && validateOrderNumber())) {
            throw new IllegalStateException("연산자 또는 숫자의 순서를 확인하세요");
        }

        return true;
    }

    // 길이 검증
    boolean validLength() {
        return splitedValues.length >= MIN_LENGTH;
    }

    // 공백 제거 및 유효 문자 검증
    boolean validateBlank() {
        boolean b = false;
        for (String s : splitedValues) {
            Matcher matcher = Validator.validOperationPattern.matcher(s);
            b = matcher.find();
            if (!b) break;
        }
        return b;
    }

    // 0으로 나누기 검증
    boolean validateDivideZero() {
        return value.contains(DIVIDE_ZERO_FORM);
    }

    // 숫자 검증 (1 + + .. )
    boolean validateOrderNumber() {
        boolean chk = true;
        for (int i = 0; i < splitedValues.length; i += 2) {
            if (!isNumeric(splitedValues[i])) {
                return chk = false;
            }
        }
        return chk;
    }

    // 연산자 검증
    boolean validateOrderOp() {
        boolean chk = true;
        for (int i = 1; i < splitedValues.length; i += 2) {
            if (isNumeric(splitedValues[i]) || splitedValues[i].length() != 1 || i == splitedValues.length - 1) {
                return chk = false;
            }
        }
        return chk;
    }

    boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
