package mypackage;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.simple.*;
import java.io.FileWriter;
import java.io.IOException;

public class CarRentalNew extends HttpServlet {

  FileWriter file;
  JSONArray myArray = new JSONArray();
  public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();

    String ModelVehicle = req.getParameter("model_vehicle");
    String SubModel = req.getParameter("sub_model_vehicle");
    String numberDays = req.getParameter("dies_lloguer");
    String numberUnits = req.getParameter("num_vehicles");

	  int finalPrice = 0;
    if (SubModel.equals("Diesel")) finalPrice += 20;
    else finalPrice += 30;
    finalPrice = (Integer.parseInt(ModelVehicle) + finalPrice) * Integer.parseInt(numberDays) * 	Integer.parseInt(numberUnits);

    JSONObject obj = new JSONObject();
    obj.put("modelV", ModelVehicle);
    obj.put("subM", SubModel);
    obj.put("nDays", numberDays);
    obj.put("nUnits", numberUnits);
	  obj.put("finalP", finalPrice);

    myArray.add(obj);

    file =  new FileWriter("/var/lib/tomcat7/webapps/my_webapp/WEB-INF/classes/mypackage/test.json");
    myArray.writeJSONString(file);
    file.flush();

    out.println("<h1> Your Order </h1>");
    out.println("<h2> Final price = " + finalPrice + "</h2>");

  }

  public void doPost(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    doGet(req, res);
  }
}
