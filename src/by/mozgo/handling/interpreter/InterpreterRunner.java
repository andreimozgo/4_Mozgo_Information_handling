package by.mozgo.handling.interpreter;

/**
 * Created by Андрей on 18.04.2017.
 */
public class InterpreterRunner {
    public static void main(String[] args) {
        String expression = "8 2 7 4 + * -";
        Client interpreter = new Client(expression);
        System.out.println("[ " + expression + " ] = " + interpreter.calculate());
    }
}
