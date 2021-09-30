package calculator;

public class Calculator {

    private String[] split;

    public Calculator(String[] split) {
        this.split = split;
    }

    public double calculate() {
        double result = Double.parseDouble(split[0]);
        Operation operation = new Operation();

        for (int i = 1; i < split.length; i+=2) {
            result = operation.run(split[i], result, Double.parseDouble(split[i+1]));
        }
        return result;
    }
}
