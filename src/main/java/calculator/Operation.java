package calculator;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Operation {

    enum Operator {

        PLUS("+", (n1, n2) -> n1 + n2),
        MINUS("-", (n1, n2) -> n1 - n2),
        MULT("*", (n1, n2) -> n1 * n2),
        DIVIDE("/", (n1, n2) -> n1 / n2);

        private String operator;
        private BiFunction<Double, Double, Double> expression;

        Operator(String operator, BiFunction<Double, Double, Double> expression) {
            this.operator = operator;
            this.expression = expression;
        }

        public double calculate(double num1, double num2) {
            return this.expression.apply(num1, num2);
        }
    }

    private Map<String, Operator> operators = new HashMap<>();

    public Operation() {
        for (Operator value : Operator.values()) {
            operators.put(value.operator, value);
        }
    }

    public double run(String operator, double num1, double num2) {
        return operators.get(operator).calculate(num1, num2);
    }
}
