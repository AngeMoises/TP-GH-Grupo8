package transport;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;


/**
 * Parte 2.3 — Tests de AlertObserver usando Mocks (Mockito).
 *
 * Diferencia clave respecto al fake:
 *   - El mock permite verificar INTERACCIONES: si un método fue llamado,
 *     cuántas veces, y con qué argumentos.
 *   - El fake solo verifica el resultado final (qué quedó en la lista).
 *
 * Mismos escenarios que AlertObserverFakeTest, distinta técnica.
 */
class AlertObserverMockTest {

    private final TransportSnapshot snapshot =
        new TransportSnapshot("Taxi", 200.0, 5.0, 15);

    @Test
    void cuandoShouldAlertCostEsTrue_seLlamaLogWarning() {
        // Arrange
        AlertService mockService = mock(AlertService.class);
        Logger mockLogger        = mock(Logger.class);

        when(mockService.shouldAlertCost(anyDouble())).thenReturn(true);
        when(mockService.shouldAlertETA(anyInt())).thenReturn(false);

        AlertObserver observer = new AlertObserver(mockService, mockLogger);

        // Act
        observer.onUpdate(snapshot);

        // Assert: logWarning fue llamado exactamente una vez con cualquier string
        verify(mockLogger, times(1)).logWarning(anyString());
        // logError no debería haberse llamado
        verify(mockLogger, never()).logError(anyString());
    }

    @Test
    void cuandoShouldAlertETAEsTrue_seLlamaLogError() {
        // Arrange
        AlertService mockService = mock(AlertService.class);
        Logger mockLogger        = mock(Logger.class);

        when(mockService.shouldAlertCost(anyDouble())).thenReturn(false);
        when(mockService.shouldAlertETA(anyInt())).thenReturn(true);

        AlertObserver observer = new AlertObserver(mockService, mockLogger);

        // Act
        observer.onUpdate(snapshot);

        // Assert: logError fue llamado exactamente una vez
        verify(mockLogger, times(1)).logError(anyString());
        // logWarning no debería haberse llamado
        verify(mockLogger, never()).logWarning(anyString());
    }

    @Test
    void cuandoAmbasCondicionesSonFalse_elLoggerNoEsLlamadoConAlertas() {
        // Arrange
        AlertService mockService = mock(AlertService.class);
        Logger mockLogger        = mock(Logger.class);

        when(mockService.shouldAlertCost(anyDouble())).thenReturn(false);
        when(mockService.shouldAlertETA(anyInt())).thenReturn(false);

        AlertObserver observer = new AlertObserver(mockService, mockLogger);

        // Act
        observer.onUpdate(snapshot);

        // Assert: ni logWarning ni logError fueron llamados
        verify(mockLogger, never()).logWarning(anyString());
        verify(mockLogger, never()).logError(anyString());
    }
}
