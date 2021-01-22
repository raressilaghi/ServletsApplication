package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LotteryResult extends HttpServlet {

    int [] userLotteryNumbers;
    int [] lotteryNumbers;


    public int[] lotteryExtraction(int nr) {

        int [] lotteryNumbers = new int [6];
        int n;
        if (nr==6)
            n=49;
        else
            n=40;
        Random random = new Random();
        boolean isRepeated;
        int randomNumber;
        for(int i=0; i<6; i++) {
            do {
                isRepeated = false;
                randomNumber = random.nextInt(n+1-1)+1;
                for(int k=0; k <= i; k++)
                {
                    if(lotteryNumbers[k] == randomNumber) {
                        isRepeated = true;
                        break;
                    }
                }
            }while(isRepeated);
            lotteryNumbers[i]= randomNumber;
        }
        return lotteryNumbers;

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int matchedNumbers = 0;
        userLotteryNumbers= (int[]) request.getSession().getAttribute("UserNumbers");
        lotteryNumbers = lotteryExtraction(userLotteryNumbers.length);
        for (int userLotteryNumber : userLotteryNumbers) {
            for (int lotteryNumber : lotteryNumbers) {
                if (userLotteryNumber == lotteryNumber) {
                    matchedNumbers++;
                }
            }
        }

        PrintWriter out= response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>LottoResults</title>");
        out.println("<style>div{font-size: 20px;\n" +
                "    margin-bottom: 10px;\n" +
                "    text-align: center;} p{display: flex;\n" +
                "    justify-content: center;}</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div>");
        out.println("Nr Alese de User: ");

        for (int userLotteryNumber : userLotteryNumbers) out.print(userLotteryNumber + ", ");

        out.println("</div>");
        out.println("<div>");
        if(userLotteryNumbers.length == 6)
            out.println("Nr Extrase la Lotto 6 din 49:  ");
        else
            out.println("Nr Extrase la Lotto 5 din 40:  ");

        for (int lotteryNumber : lotteryNumbers) out.print(lotteryNumber + ", ");
        out.println("</div>");
        out.println("<div>");
        if(matchedNumbers > 1)
            out.println("Ai nimerit " + matchedNumbers + " numere!");
        else
        if(matchedNumbers == 1)
            out.println("Ai nimerit " + matchedNumbers + " numar!");
        else
            out.println("Nu ai nimerit nici un numar!");
        out.println("</div>");
        out.println("<p><button type=\"button\" onclick=\"window.location.href='StartGame'\">Joaca din nou </button></p>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

}
