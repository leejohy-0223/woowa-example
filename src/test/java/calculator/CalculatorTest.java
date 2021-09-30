package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

class CalculatorTest {

    @Test
    @DisplayName("valid한 패턴 검증 실패")
    void valid_Pattern() {
        String input = "1 + 2 * 3a";
        String[] s = input.split(" ");
        Validator validator = new Validator(input, s);

        boolean b = validator.validateBlank();
        assertThat(b).isEqualTo(false);
    }

    @Test
    @DisplayName("숫자 순서 테스트")
    void valid_order_number() {
        String input = "1 + 2 * 32 / 3";
        String[] s = input.split(" ");
        Validator validator = new Validator(input, s);

        boolean b = validator.validateOrderNumber();
        assertThat(b).isEqualTo(true);
    }

    @Test
    @DisplayName("연산자 순서 테스트")
    void valid_order_op() {
//        String input = "+ 1 * 2 + 4";
        String input = "1 * / + 2";
        String[] s = input.split(" ");
        Validator validator = new Validator(input, s);

        boolean b = validator.validateOrderOp();
        assertThat(b).isEqualTo(true);

    }

    @Test
    @DisplayName("숫자 + 연산자 통합 테스트")
    void valid_order() {
//        String input = "1 / / 2 * 3";
//        String input = "1 + 2 * 3  "; // triming 처리함
//        String input = "1 11 + 2 * 3 ";
        String input = "1 + 23 / 1 *";
        String[] s = input.split(" ");
        Validator validator = new Validator(input, s);

        boolean b = validator.validateOrderOp() && validator.validateOrderNumber();
        assertThat(b).isEqualTo(false);
    }

    @Test
    @DisplayName("통합 테스트")
    void valid_all() {
        String input = "+ + 1 + 2";
        String[] s = input.split(" ");
        Validator validator = new Validator(input, s);

        boolean b = validator.validateAll();
        assertThat(b).isEqualTo(true);
    }

}