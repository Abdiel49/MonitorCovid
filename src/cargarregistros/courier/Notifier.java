package cargarregistros.courier;

public interface Notifier {
    void sendMessage(Message m);
    void subscribe(Observer o);
}
