package software.ulpgc;

public class FibonnacciCommand implements Command {
    @Override
    public Output execute(Input input) {
        try{
            int number = Integer.parseInt(input.get(":number"));
            return isOutOfBound(number) ? outOfBoundOutput() : outputOf(number);
        }
        catch (NumberFormatException e){
            return nanOutput();
        }
    }

    private boolean isOutOfBound(int number) {
        return number < 0 || number > 30;
    }

    private Output outOfBoundOutput() {
        return new Output() {
            @Override
            public int responseCode() {
                return 404;
            }

            @Override
            public String result() {
                return "Number is out of bound";
            }
        };
    }

    private Output outputOf(int number) {
        return new Output() {
            @Override
            public int responseCode() {
                return 200;
            }

            @Override
            public String result() {
                return String.valueOf(fibonacciOf(number));
            }
        };
    }

    private int fibonacciOf(int number) {
        if (number == 0) return 0;
        if (number == 1) return 1;
        int fib1 = 1;
        int fib2 = 1;

        for (int i = 3; i <= number; i++) {
            int temporalVar = fib2;
            fib2 += fib1;
            fib1 = temporalVar;
        }
        return fib2;
    }


    private Output nanOutput() {
        return new Output() {
            @Override
            public int responseCode() {
                return 405;
            }

            @Override
            public String result() {
                return "Not a number";
            }
        };
    }
}
