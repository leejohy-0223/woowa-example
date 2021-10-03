package racing.racingcar;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidationTest {

    @Test
    void split_테스트() throws Exception {

        /* REFLECTION */
        Method m = Validation.class.getDeclaredMethod("blankValidation", String.class);
        m.setAccessible(true);

        /* GIVEN */
        Validation v = new Validation();
        String test = " ";

        /* WHEN & THEN */
        try {
            m.invoke(v, test);
        } catch (InvocationTargetException e) {
            assertThat(e.getCause() instanceof IllegalStateException).isEqualTo(true);
        }
    }

    @Test
    void overlap_테스트() throws Exception {

        /* REFLECTION */
        Method m = Validation.class.getDeclaredMethod("overlapValidation", List.class, String.class);
        m.setAccessible(true);

        /* GIVEN */
        Validation v = new Validation();
        List<String> test = new ArrayList<>();
        test.add("lee");

        String input = "lee";

        /* WHEN & THEN */
        try {
            m.invoke(v, test, input);
        } catch (InvocationTargetException e) {
            assertThat(e.getCause() instanceof IllegalStateException).isEqualTo(true);
        }
    }

    @Test
    void car_valid_테스트() throws Exception{
        // reflection
        Method m = Validation.class.getDeclaredMethod("carValidation", String.class);
        m.setAccessible(true);

        // given
        Validation v = new Validation();
        String input = "lee,jo,hy,woni,bible";

        // when & then
        List<Car> invoke = (List<Car>) m.invoke(v, input);

        assertThat(invoke.size()).isEqualTo(5);
        assertThat(invoke.get(2).getName()).isEqualTo("hy");
    }

    @Test
    void input_validation_테스트() {
        // given
        Validation v = new Validation();
        String input1 = "lee,lee,hy,,bible";
        String input2 = "lee,lee,hy,jo,bible";
        String input3 = "lee,jo,hy";


        //when & then
        assertThrows(IllegalStateException.class, () -> {
            v.inputValidation(input1, 1);
        });

        //when & then
        assertThrows(IllegalStateException.class, () -> {
            v.inputValidation(input2, 1);
        });

        assertThrows(IllegalStateException.class, () -> {
            v.inputValidation(input3, -1);
        });

        assertThat(v.inputValidation(input3, 1).size()).isEqualTo(3);
    }

    @Test
    void count_validation_테스트() {
        // given
        Validation v = new Validation();
        int count = 80;
        int count1 = -1;

        // when & then
        v.countValidation(count);

        assertThrows(IllegalStateException.class, () -> {
            v.countValidation(count1);
        });
    }
}