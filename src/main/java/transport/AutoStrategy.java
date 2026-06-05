package transport;

public class AutoStrategy implements TransportStrategy {

    private String name;
    private double distance;
    private double gasCostperKM;

    public AutoStrategy(double distance, double G) {
        this.name = "Auto";
        this.distance = distance;
        this.gasCostperKM = G;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getCost() {
        return gasCostperKM;
    }

    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public int getETA() {
        return (int) ((distance / 35.0) * 60);
    }

    @Override
    public double calculateCost() {
        return distance * gasCostperKM;
    }
}

