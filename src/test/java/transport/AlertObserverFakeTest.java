package transport;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Parte 2.2 — Tests de AlertObserver usando Fakes manuales.
 *
 * Un fake es una implementación alternativa simple escrita a mano.
 * Permite controlar el comportamiento sin depender de la lógica real.
 *
 * Fakes usados:
 *   - AlwaysAlertService / NeverAlertService → controlan la decisión
 *   - FakeLogger → captura los mensajes en lugar de imprimirlos
 */
class AlertObserverFakeTest {

    // Snapshot de ejemplo para todos los tests
    private final TransportSnapshot snapshot =
        new TransportSnapshot("Taxi", 200.0, 5.0, 15);

    @Test
    void conAlwaysAlertService_seLoggeanWarningYError() {
        // Arrange
        FakeLogger fakeLogger = new FakeLogger();
        AlertObserver observer = new AlertObserver(new AlwaysAlertService(), fakeLogger);

        // Act
        observer.onUpdate(snapshot);

        // Assert: se loggeó al menos un warning y un error
        assertFalse(fakeLogger.warningMessages.isEmpty(),
            "Debería haberse loggeado un warning por el costo");
        assertFalse(fakeLogger.errorMessages.isEmpty(),
            "Debería haberse loggeado un error por el ETA");
    }

    @Test
    void conNeverAlertService_noSeLoggeanAlertas() {
        // Arrange
        FakeLogger fakeLogger = new FakeLogger();
        AlertObserver observer = new AlertObserver(new NeverAlertService(), fakeLogger);

        // Act
        observer.onUpdate(snapshot);

        // Assert: no se loggeó ninguna alerta
        assertTrue(fakeLogger.warningMessages.isEmpty(),
            "No debería haber warnings cuando el servicio siempre retorna false");
        assertTrue(fakeLogger.errorMessages.isEmpty(),
            "No debería haber errors cuando el servicio siempre retorna false");
    }
}
