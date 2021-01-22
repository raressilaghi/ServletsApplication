import java.io.*;
import org.apache.catalina.*;
import org.apache.catalina.startup.*;
import servlets.ChooseNumbers;
import servlets.LotteryResult;
import servlets.StartGame;



public class TomcatServer {
    public static void main(String[] args) throws Exception {
        Tomcat server = new Tomcat();
        server.setPort(8080);
        Context ctx = server.addContext("", (new File(".")).getAbsolutePath());
        Tomcat.addServlet(ctx, "start", new StartGame());
        Tomcat.addServlet(ctx, "check", new ChooseNumbers());
        Tomcat.addServlet(ctx, "result", new LotteryResult());
        ctx.addServletMapping("", "start");
        ctx.addServletMapping("/StartGame", "start");
        ctx.addServletMapping("/ChooseNumbers", "check");
        ctx.addServletMapping("/LotteryResult", "result");
        server.start();
        System.out.println("Start server Tomcat embedded");
        server.getServer().await();
    }
}
