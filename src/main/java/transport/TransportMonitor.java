package transport;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TransportMonitor {

    private List<TransportObserver> observerList;
    private TransportStrategy currentStrategy;
    private Logger logger = Logger.getInstance();
    private Timer timer;

    public TransportMonitor() {
        this.observerList = new ArrayList<>();
    }

    // ── Observer pattern ──────────────────────────────────────────────────────

    public void suscribe(TransportObserver obss) {
        observerList.add(obss);
        logger.logInfo("Observer suscrito: " + obss.getClass().getSimpleName());
    }

    public void unsuscribe(TransportObserver obsus) {
        observerList.remove(obsus);
        logger.logInfo("Observer desuscrito: " + obsus.getClass().getSimpleName());
    }

    private void notifyObservers(TransportSnapshot snapshot) {
        for (TransportObserver observer : observerList) {
            observer.onUpdate(snapshot);
        }
    }

    // ── Strategy pattern ──────────────────────────────────────────────────────

    public void setTransporte(TransportStrategy estrategia) {
        this.currentStrategy = estrategia;
        logger.logInfo("Estrategia cambiada a: " + estrategia.getName());
    }

    // ── Monitoring ────────────────────────────────────────────────────────────

    public void start(int intervalMS) {
        if (currentStrategy == null) {
            logger.logError("No hay estrategia definida. Llame a setTransporte() primero.");
            return;
        }

        logger.logInfo("Iniciando monitoreo cada " + intervalMS + " ms...");

        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                TransportSnapshot snapshot = new TransportSnapshot(
                    currentStrategy.getName(),
                    currentStrategy.getCost(),
                    currentStrategy.getDistance(),
                    currentStrategy.getETA()
                );
                notifyObservers(snapshot);
            }
        }, 0, intervalMS);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            logger.logInfo("Monitoreo detenido.");
        }
    }
}
