package transport;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Logger logger = Logger.getInstance();
        logger.logInfo("=== Sistema de Monitoreo de Transporte ===");

        // ── Crear monitor ──────────────────────────────────────────────────────
        TransportMonitor monitor = new TransportMonitor();

        // ── Crear AlertService con umbrales ────────────────────────────────────
        // Alerta si el costo supera $1000 o el ETA supera 30 min
        AlertService alertService = new ThresholdAlertService(1000.0, 30);

        // ── Crear y suscribir observers ────────────────────────────────────────
        AlertObserver alertObserver = new AlertObserver(alertService, logger);
        ConsolePrinter consolePrinter = new ConsolePrinter();

        monitor.suscribe(alertObserver);
        monitor.suscribe(consolePrinter);

        // ── Estrategia: Auto ─────────────────────────────────────────────────
        logger.logInfo("\n--- Probando estrategia: Auto (5 km) ---");
        monitor.setTransporte(new AutoStrategy(5.0, 1200));
        monitor.start(1000);
        Thread.sleep(2000);
        monitor.stop();

        // ── Estrategia: Colectivo ──────────────────────────────────────────────
        logger.logInfo("\n--- Probando estrategia: Colectivo (5 km) ---");
        monitor.setTransporte(new ColectivoStrategy(5.0));
        monitor.start(1000);
        Thread.sleep(2000);
        monitor.stop();

        // ── Estrategia: Taxi ───────────────────────────────────────────────────
        logger.logInfo("\n--- Probando estrategia: Taxi (5 km) ---");
        monitor.setTransporte(new TaxiStrategy(5.0));
        monitor.start(1000);
        Thread.sleep(2000);
        monitor.stop();

        // ── Desuscribir un observer ────────────────────────────────────────────
        logger.logInfo("\n--- Desuscribiendo ConsolePrinter ---");
        monitor.unsuscribe(consolePrinter);

        logger.logInfo("\n--- Taxi de nuevo, solo AlertObserver activo ---");
        monitor.setTransporte(new TaxiStrategy(3.0));
        monitor.start(1000);
        Thread.sleep(2000);
        monitor.stop();
        logger.logInfo("Hola esto es otro test");
        logger.logInfo("\n=== Fin del programa ===");
    }

}
