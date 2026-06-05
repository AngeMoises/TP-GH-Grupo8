package transport;

/**
 * Decide si una condición de transporte debe generar una alerta.
 * Principio SRP: esta interfaz solo tiene la responsabilidad de "decidir".
 * AlertObserver tiene la responsabilidad de "actuar".
 */
public interface AlertService {
    boolean shouldAlertCost(double cost);
    boolean shouldAlertETA(int eta);
}
