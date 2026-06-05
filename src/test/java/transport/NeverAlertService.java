package transport;

/**
 * Fake de AlertService que NUNCA retorna true.
 * Útil para testear que AlertObserver no loggea cuando el servicio dice que no.
 */
public class NeverAlertService implements AlertService {

    @Override
    public boolean shouldAlertCost(double cost) { return false; }

    @Override
    public boolean shouldAlertETA(int eta) { return false; }
}
