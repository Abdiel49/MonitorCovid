package cargarregistros.courier;

public interface Observer {
    void mailBox(Message m);
    void subscribe(Observer o);
}
