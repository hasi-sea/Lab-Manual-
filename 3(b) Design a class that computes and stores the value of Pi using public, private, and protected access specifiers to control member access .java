class PiCalculator {
    private double piValue;

    protected void computePi() {
        piValue = Math.PI;
    }

    public double getPiValue() {
        computePi();
        return piValue;
    }
}

public class Main {
    public static void main(String[] args) {
        PiCalculator calculator = new PiCalculator();
        System.out.println("The computed value of Pi is: " + calculator.getPiValue());
    }
}
