import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.SessionIdManager;
import org.eclipse.jetty.server.session.DefaultSessionIdManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyServer {

    private static void setSession(Server server, ServletHandler handlerServlet) {
        SessionIdManager idmanager= new DefaultSessionIdManager(server);
        server.setSessionIdManager(idmanager);
        SessionHandler sessionHandler = new SessionHandler();
        handlerServlet.setHandler(sessionHandler);
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        setSession(server, handler);
        server.setHandler(handler);
        handler.addServletWithMapping(servlets.StartGame.class, "");
        handler.addServletWithMapping(servlets.StartGame.class, "/StartGame");
        handler.addServletWithMapping(servlets.ChooseNumbers.class, "/ChooseNumbers");
        handler.addServletWithMapping(servlets.LotteryResult.class, "/LotteryResult");
        System.out.println("Start server jetty embedded");
        server.start();
        server.join();
    }
}