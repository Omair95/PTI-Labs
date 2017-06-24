package mypackage;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CarRentalList extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    BufferedReader br = null;
	  JSONParser parser = new JSONParser();
    String space = " ";
    String user = req.getParameter("userid");
    String pass = req.getParameter("password");

    if (user.equals("admin") && pass.equals("pti"))
    {
      out.println("<h1><pre>Model" + space + "Engine" + space + "Days" + space + "Units" + space + "Final price </pre></h1>");
      try {
        JSONArray a = (JSONArray) parser.parse(new FileReader("/var/lib/tomcat7/webapps/my_webapp/WEB-INF/classes/mypackage/test.json"));

        for (Object o: a)
        {
          JSONObject order = (JSONObject) o;
          String model = "hola";
          String space1 = "   ";
          if (order.get("modelV").equals("54")) model = "Economic";
          else if (order.get("modelV").equals("71")) model = "Semi-Luxe";
          else if (order.get("modelV").equals("82")) model = "Luxe";
          else if (order.get("modelV").equals("139")) model = "Limusina";
          out.println("<h2><pre>" + model + space1 + order.get("subM") + space1 + order.get("nDays") +
                      space1 + order.get("nDays") + space1 + order.get("nUnits") + space1 + order.get("finalP") + "</pre></h2>");
        }
      }
  	  catch (FileNotFoundException e) {
              out.println("<h1>" + e  + "</h1>");
              e.printStackTrace();
      } catch (IOException e) {
              out.println("<h1>" + e  + "</h1>");
              e.printStackTrace();
      } catch (ParseException e) {
              out.println("<h1>" + e  + "</h1>");
              e.printStackTrace();
      }
    } else
    {
      out.println("<h1> Wrong user or password </h1>");
    }

  }

  public void doPost(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    doGet(req, res);
  }
}
