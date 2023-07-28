import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Input input = new Input();
        Calculation calculation = new Calculation();

        String expression = input.getExpression();
        calculation.calculate(expression);
    }
}


class Input {
    public String getExpression() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите математическое выражение: ");
        String expression = scanner.nextLine();
        String[] expression_splitted = expression.split(" ");

        if (expression_splitted.length != 3 && expression_splitted.length != 5)
            throw new IllegalArgumentException("Неверный формат ввода !");

        for (int i = 0; i < expression_splitted.length; i += 2) {
            int num = Integer.parseInt(expression_splitted[i]);
            if (num < -10 || num > 10 || num == 0) {
                throw new IllegalArgumentException("Неверный диапазон чисел !");
            }
        }

        for (int i = 1; i < expression_splitted.length; i += 2) {
            char operator = expression_splitted[i].charAt(0);
            if (operator != '+' && operator != '-' && operator != '*' && operator != '/') {
                throw new IllegalArgumentException("Неверный знак оператора !");
            }
        }

        return expression;


    }
}


class Calculation {
    public void calculate(String expression) {
        String[] expression_splitted = expression.split(" ");
        int result;

        if (expression_splitted.length == 3) {
            int num1 = Integer.parseInt(expression_splitted[0]);
            int num2 = Integer.parseInt(expression_splitted[2]);
            char operator1 = expression_splitted[1].charAt(0);

            result = calc(num1, num2, operator1);

        }

        else{
            int num1 = Integer.parseInt(expression_splitted[0]);
            int num2 = Integer.parseInt(expression_splitted[2]);
            int num3 = Integer.parseInt(expression_splitted[4]);
            char operator1 = expression_splitted[1].charAt(0);
            char operator2 = expression_splitted[3].charAt(0);

            if (operator1 == '*' || operator1 == '/') {
                result = calc(num1, num2, operator1);
                result = calc(result, num3, operator2);
            } else {
                result = calc(num2, num3, operator2);
                result = calc(result, num1, operator1);
            }

        }

        System.out.println(result);
    }


    private int calc(int num1, int num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Неверный знак оператора !");
        }
    }
}
