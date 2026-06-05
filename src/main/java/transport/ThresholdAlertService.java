package transport;

/**
 * Implementación concreta de AlertService basada en umbrales fijos.
 * Encapsula toda la lógica de comparación: AlertObserver no necesita
 * saber nada sobre maxCost ni maxEta.
 *
 * Comportamiento en el umbral exacto:
 *   - shouldAlertCost(maxCost)  → false  (solo alerta si SUPERA, no si iguala)
 *   - shouldAlertETA(maxEta)    → false  (ídem)
 */
public class ThresholdAlertService implements AlertService {

    private final double maxCost;
    private final int maxEta;

    public ThresholdAlertService(double maxCost, int maxEta) {
        this.maxCost = maxCost;
        this.maxEta  = maxEta;
    }

    @Override
    public boolean shouldAlertCost(double cost) {
        return cost > maxCost;   
    }

    @Override
    public boolean shouldAlertETA(int eta) {
        return eta > maxEta;
    }
}
