package transport;

public class TaxiStrategy implements TransportStrategy {

    private String name;
    private double distance;
    private static final double BASE_FARE = 800.0;
    private static final double COST_PER_KM = 500.0;

    public TaxiStrategy(double distance) {
        this.name = "Taxi";
        this.distance = distance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getCost() {
        return calculateCost();
    }

    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public int getETA() {
        // Velocidad promedio taxi urbano: 40 km/h
        return (int) ((distance / 40.0) * 60);
    }

    @Override
    public double calculateCost() {
        return BASE_FARE + (distance * COST_PER_KM);
    }
}
