package transport;

/**
 * Fake de AlertService que SIEMPRE retorna true.
 * Útil para testear que AlertObserver loggea cuando se lo indica el servicio.
 */
public class AlwaysAlertService implements AlertService {

    @Override
    public boolean shouldAlertCost(double cost) { return true; }

    @Override
    public boolean shouldAlertETA(int eta) { return true; }
}
