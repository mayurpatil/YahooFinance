package com.sdm;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import yahoofinance.*;
import java.math.BigDecimal;
import java.util.Map;

public class YFServlet extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    resp.setContentType("text/html");
    
    resp.getWriter().println("<h4>Yahoo Finance API Demo in Codenvy with Google App Engine</h4>");
    String company = req.getParameter("company");
    
    Stock stock = YahooFinance.get(company);
     
BigDecimal price = stock.getQuote().getPrice();
   // double Tprice = stock.getQuote(true).getPrice();
BigDecimal change = stock.getQuote().getChangeInPercent();
BigDecimal peg = stock.getStats().getPeg();
BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
 
    resp.getWriter().println("<br/>Company Name " + company) ;
    resp.getWriter().println("<br/>Price " + price) ;
    resp.getWriter().println("<br/>Change " + change) ;
    resp.getWriter().println("<br/>Peg " + peg) ;
    resp.getWriter().println("<br/>Dividend " + dividend) ;
    
    //bulk fetch
    String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
    Map<String, Stock> stocks = YahooFinance.get(symbols); // single request
    Stock intel = stocks.get("INTC");
    Stock airbus = stocks.get("AIR.PA");
 		
    
     resp.getWriter().println("<br/><br/><br/>BULK Fetch Results<br/>") ;
    
    for (Map.Entry<String, Stock> entry : stocks.entrySet())
{
      String value=entry.getValue().toString();
      String[] arr = value.split(":");
    resp.getWriter().println("<br/>Company Name " + entry.getKey() + "/" + " Price " + arr[1]);
}
        
    
  }
}
//http://yahoofinance-api.com/
