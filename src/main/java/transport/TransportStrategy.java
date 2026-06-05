package transport;

public interface TransportStrategy {
    String getName();
    double getCost();
    double getDistance();
    int getETA();
    double calculateCost();
}
