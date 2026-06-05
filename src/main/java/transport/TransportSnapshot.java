package transport;

public class TransportSnapshot {

    private String name;
    private double cost;
    private double distance;
    private int ETA;

    public TransportSnapshot(String n, double c, double d, int e) {
        this.name = n;
        this.cost = c;
        this.distance = d;
        this.ETA = e;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public double getDistance() {
        return distance;
    }

    public int getETA() {
        return ETA;
    }

    @Override
    public String toString() {
        return String.format(
            "[Snapshot] Transporte: %s | Costo: $%.2f | Distancia: %.2f km | ETA: %d min",
            name, cost, distance, ETA
        );
    }
}
