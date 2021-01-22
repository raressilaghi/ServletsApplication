
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChooseNumbers extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int NUMBER_OF_LOTTERY_NUMBERS = Integer.parseInt(request.getParameter("tip"));
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>ChooseNumbers</title>");
        out.println("<style> div{\n" +
                "    margin-bottom: 15px;\n" +
                "    } p{font-weight: bold;\n" +
                "    font-size: 20px;}</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Introduceti numerele</p>");
        out.println("<form method=\"POST\" action=\"?\">");

        for(int i=1; i<=NUMBER_OF_LOTTERY_NUMBERS; i++) {
            out.println("<div>");
            out.println("Nr"+i+":<input type=\"text\" name=\"nr"+i+"\">");
            out.println("</div>");
        }
        out.println("<input type=\"hidden\"  name=\"tip\" value=\""+NUMBER_OF_LOTTERY_NUMBERS+"\">");
        out.println("<div>");
        out.println("<input type=\"submit\" value=\"Pune numerele\" />");
        out.println("</div>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int NUMBER_OF_LOTTERY_NUMBERS = Integer.parseInt(request.getParameter("tip"));
        int min =1;
        int max;
        if(NUMBER_OF_LOTTERY_NUMBERS == 6)
            max=49;
        else
            max=40;
        PrintWriter out= response.getWriter();
        int [] userLotteryNumbers = new int [NUMBER_OF_LOTTERY_NUMBERS];
        int nr;
        StringBuilder errorString= new StringBuilder();
        for(int i=0; i<NUMBER_OF_LOTTERY_NUMBERS; i++) {
            try {
                nr=Integer.parseInt(request.getParameter("nr"+(i+1)));
                if(nr<min) {
                    errorString.append(" Numerele nu pot fi mai mici decat ").append(min).append(".");
                }
                if(nr>max) {
                    errorString.append(" Numerele nu pot fi mai mari decat ").append(max).append(".");
                }
                for(int k = 0; k <i; k++) {
                    if(userLotteryNumbers[k] ==  nr) {
                        errorString.append(" Numerele nu au voie sa se repete.");
                    }
                }
                userLotteryNumbers[i] = nr;

            }
            catch(NumberFormatException e) {
                errorString.append(" Valorile introduse trebuie sa fie numere.");
            }
        }
        if(errorString.length() > 0)
        {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ChooseNumbers</title>");
            out.println("<style> div{\n" +
                    "    margin-bottom: 15px;\n" +
                    "    } p{ font-weight: bold;\n" +
                    "    font-size: 20px;}</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Introduceti numerele</p>");
            out.println("<form method=\"POST\" action=\"?\">");
            for(int i=0; i<NUMBER_OF_LOTTERY_NUMBERS; i++) {
                out.println("<div>");
                out.println("Nr"+(i+1)+":<input type=\"text\"value=\""+userLotteryNumbers[i]+"\" name=\"nr"+(i+1)+"\">");
                out.println("</div>");
            }
            out.println("<input type=\"hidden\"  name=\"tip\" value=\""+NUMBER_OF_LOTTERY_NUMBERS+"\">");
            out.println("<div>");
            out.println("<input type=\"submit\" value=\"Pune numerele\" />");
            out.println("</div>");
            out.println("</form>");
            out.println(errorString);
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
        else {
            request.getSession().setAttribute("UserNumbers", userLotteryNumbers);
            response.sendRedirect("LotteryResult");
        }
        out.close();
        doGet(request, response);
    }

}
