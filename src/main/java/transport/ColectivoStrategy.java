package transport;

public class ColectivoStrategy implements TransportStrategy {

    private String name;
    private double distance;
    private static final double COST_PER_KM = 150.0; // Precio en pesos

    public ColectivoStrategy(double distance) {
        this.name = "Colectivo";
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
        // Velocidad promedio colectivo urbano: 20 km/h
        return (int) ((distance / 20.0) * 60);
    }

    @Override
    public double calculateCost() {
        return distance * COST_PER_KM;
    }
}
