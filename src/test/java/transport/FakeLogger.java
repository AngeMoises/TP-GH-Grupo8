package transport;

import java.util.ArrayList;
import java.util.List;

/**
 * Fake del Logger: captura los mensajes en listas en lugar de imprimirlos.
 * Permite verificar en los tests qué se loggeó sin depender de la consola.
 *
 * Extiende Logger (cuyo constructor ahora es protected) para poder
 * usarse en lugar del Logger real sin necesidad de una interfaz extra.
 */
public class FakeLogger extends Logger {

    public final List<String> debugMessages   = new ArrayList<>();
    public final List<String> infoMessages    = new ArrayList<>();
    public final List<String> warningMessages = new ArrayList<>();
    public final List<String> errorMessages   = new ArrayList<>();

    @Override
    public void logDebug(String msg)   { debugMessages.add(msg); }

    @Override
    public void logInfo(String msg)    { infoMessages.add(msg); }

    @Override
    public void logWarning(String msg) { warningMessages.add(msg); }

    @Override
    public void logError(String msg)   { errorMessages.add(msg); }
}
