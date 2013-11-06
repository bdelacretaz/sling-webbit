package ch.x42.sling.webbit;

import org.webbitserver.WebSocketConnection;
import org.webbitserver.WebSocketHandler;

public class EchoHandler implements WebSocketHandler {

    int connectionCount;
    
    public void onOpen(WebSocketConnection connection) {
      connection.send("This is " + getClass().getName() + ", there are " + connectionCount + " other connections active");
      connectionCount++;
    }
    
    public void onClose(WebSocketConnection connection) {
      connectionCount--;
    }
    
    public void onMessage(WebSocketConnection connection, String message) {
      connection.send("From " + getClass().getSimpleName() + ": " + message.toUpperCase());
    }

    @Override
    public void onMessage(WebSocketConnection connection, byte[] data) throws Throwable {
        onMessage(connection, new String(data));
    }

    @Override
    public void onPing(WebSocketConnection connection, byte[] data) throws Throwable {
    }

    @Override
    public void onPong(WebSocketConnection connection, byte[] data) throws Throwable {
    }
}
