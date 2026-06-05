package transport;

/**
 * Observador que emite alertas cuando el snapshot supera los umbrales.
 *
 * Responsabilidad única: ACTUAR (loggear) cuando se lo indica AlertService.
 * La decisión de si hay que alertar es delegada completamente a AlertService.
 *
 * Recibe sus dependencias por constructor (inyección de dependencias),
 * lo que permite reemplazarlas con fakes/mocks en los tests.
 */
public class AlertObserver implements TransportObserver {

    private final AlertService alertService;
    private final Logger logger;

    public AlertObserver(AlertService alertService, Logger logger) {
        this.alertService = alertService;
        this.logger       = logger;
    }

    @Override
    public void onUpdate(TransportSnapshot state) {
        logger.logDebug("AlertObserver procesando snapshot: " + state.getName());

        if (alertService.shouldAlertCost(state.getCost())) {
            logger.logWarning(
                String.format("¡ALERTA! El costo de '%s' ($%.2f) superó el límite",
                    state.getName(), state.getCost())
            );
        }

        if (alertService.shouldAlertETA(state.getETA())) {
            logger.logError(
                String.format("¡ALERTA! El ETA de '%s' (%d min) superó el límite",
                    state.getName(), state.getETA())
            );
        }

        if (!alertService.shouldAlertCost(state.getCost()) &&
            !alertService.shouldAlertETA(state.getETA())) {
            logger.logInfo("'" + state.getName() + "' está dentro de los límites aceptables.");
        }
    }
}
