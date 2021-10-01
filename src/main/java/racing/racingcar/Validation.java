package racing.racingcar;

public class Validation {

    public void inputValidation(String cars, int count) {
        carValidation(cars);
        countValidation(count);
    }

    public void carValidation(String cars) {
        String[] split = cars.split(",");
        for (String s : split) {
            System.out.println("s : " + s + " ");
        }
    }

    public void countValidation(int count) {

    }
}
