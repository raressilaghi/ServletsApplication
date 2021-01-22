package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class StartGame extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Lottery</title>");
        out.println("<style> body{width: 100vw;\r\n"
                + "		height: 100vh;\r\n"
                + "		display:flex;\r\n"
                + "		justify-content: center;\r\n"
                + "		align-items: center;\r\n"
                + "		flex-direction: column;} p{\n" +
                "            font-size: 32px;\n" +
                "        }</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p><b>Bun venit pe pagina jocului!</b></p>");
        out.println("<div>");
        out.println("<p>Alege tipul de joc</p>");
        out.println("</div>");
        out.println("<div>");
        out.println("<form method=\"GET\" action=\"ChooseNumbers\" id=\"aa\">\n" +
                "        <select id=\"tip\" name=\"tip\" style=\"width: 150px;\" form=\"aa\">\n" +
                "            <option value=\"5\">5 din 40</option>\n" +
                "            <option value=\"6\">6 din 49</option>\n" +
                "        </select>\n" +
                "        <input type=\"submit\" value=\"Incepe Jocul\"/>\n" +
                "    </form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
