package racing.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomUtilsTest {

    @Test
    void random_util_test() {

        int rdNum = 0;

        for (int i = 0; i < 100; i++) {
            rdNum = RandomUtils.nextInt(0, 9);
            System.out.println("i = " + i + ", rdNum = " + rdNum);
            assertThat(rdNum).isBetween(0, 9);
        }
    }

}