package transport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Parte 2.1 — Tests de ThresholdAlertService (sin dependencias externas).
 *
 * Clase pura: dado un input, retorna un resultado determinístico.
 * No se necesitan mocks ni fakes: se instancia directamente y se verifica el resultado.
 */
class ThresholdAlertServiceTest {

    // Umbrales: costo máximo $100, ETA máximo 20 minutos
    private ThresholdAlertService service;

    @BeforeEach
    void setUp() {
        service = new ThresholdAlertService(100.0, 20);
    }

    // ── Tests de costo ─────────────────────────────────────────────────────────

    @Test
    void costoPorDebajoDelUmbral_retornaFalse() {
        assertFalse(service.shouldAlertCost(50.0));
    }

    /**
     * Comportamiento en el umbral exacto: se decidió usar operador estricto (>),
     * por lo tanto costo == umbral NO genera alerta.
     * Documentado aquí para que el equipo conozca la decisión tomada.
     */
    @Test
    void costoExactoAlUmbral_retornaFalse() {
        assertFalse(service.shouldAlertCost(100.0));
    }

    @Test
    void costoPorEncimaDelUmbral_retornaTrue() {
        assertTrue(service.shouldAlertCost(150.0));
    }

    // ── Tests de ETA ───────────────────────────────────────────────────────────

    @Test
    void etaPorDebajoDelUmbral_retornaFalse() {
        assertFalse(service.shouldAlertETA(10));
    }

    @Test
    void etaPorEncimaDelUmbral_retornaTrue() {
        assertTrue(service.shouldAlertETA(30));
    }
}
