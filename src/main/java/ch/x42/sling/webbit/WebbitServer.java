package ch.x42.sling.webbit;

import java.util.concurrent.Future;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;

/** Try running webbit inside Sling, based on 
 *  http://thechangelog.com/webbit-a-java-event-based-websocket-and-http-server/
 */
@Component(immediate=true)
public class WebbitServer {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private int port = 6162;
    private Future<? extends WebServer> serverFuture;
    
    @Activate
    protected void activate(ComponentContext ctx) {
        serverFuture = WebServers.createWebServer(port)
            .add("/echo", new EchoHandler())
            .start();
        log.info("WebServer starting on port {}", port);
    }
    
    @Deactivate
    protected void deactivate(ComponentContext ctx) throws Exception {
        log.warn("Stopping WebServer");
        serverFuture.get().stop();
        log.warn("WebServer stopped");
    }
}
