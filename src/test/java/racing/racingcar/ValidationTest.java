package racing.racingcar;

import org.junit.jupiter.api.Test;

class ValidationTest {

    @Test
    void split_테스트() {

        String cars = "lee,jo,,hyeong";
        Validation v = new Validation();
        v.carValidation(cars);
    }

}