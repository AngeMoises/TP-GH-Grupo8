package transport;

public class ConsolePrinter implements TransportObserver {

    private Logger logger = Logger.getInstance();

    @Override
    public void onUpdate(TransportSnapshot state) {
        logger.logInfo("ConsolePrinter recibió actualización:");
        System.out.println("  +-----------------------------------------+");
        System.out.printf("  | Transporte : %-27s |%n", state.getName());
        System.out.printf("  | Costo      : $%-26.2f |%n", state.getCost());
        System.out.printf("  | Distancia  : %-24.2f km |%n", state.getDistance());
        System.out.printf("  | ETA        : %-24d min |%n", state.getETA());
        System.out.println("  +-----------------------------------------+");
    }
}
