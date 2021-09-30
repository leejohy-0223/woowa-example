package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @DisplayName("길이 테스트")
    @Test
    void length_test() {
        String str = "1 +";
        assertFalse(new Validator(str, str.split(" ")).validLength());
    }

    @DisplayName("숫자 혹은 연산자 여부 확인")
    @Test
    void blank_test() {
        String str = "1a + 23 / 3";
        assertFalse(new Validator(str, str.split(" ")).validateBlank());
    }

    @DisplayName("0으로 나누기 테스트")
    @Test
    void zero_divide() {
        String str = "1 + 2 / 0 + 5";
        assertTrue(new Validator(str, str.split(" ")).validateDivideZero());
    }

    @DisplayName("연산자, 숫자 순서 테스트")
    @Test
    void order_test() {
        String str = "1 + 2 / + 3";
        assertFalse(new Validator(str, str.split(" ")).validateOrderOp()
                && new Validator(str, str.split(" ")).validateOrderNumber()
        );
    }

    @DisplayName("전체 테스트")
    @Test
    void all_test() {
        String str = "1 + 2 / 5 + 4 + -";
        assertThrows(IllegalStateException.class, () -> {
            new Validator(str, str.split(" ")).validateAll();
        });
    }
}