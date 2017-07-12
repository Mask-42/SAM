package android;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author jagdeepsingh
 */
public class Jsonrece extends HttpServlet {

    String email, pass;

    String s2 = "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Connected");
        String auth = "notfound";

        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            //sb.append(line + "\n");
            sb.append(line);
            line = reader.readLine();
        }
        reader.close();
        String data = sb.toString();

        // System.out.println(data);
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(data);

            JSONObject jsonObject = (JSONObject) obj;

            //  u = (String) jsonObject.get("user");
            //  p = (String) jsonObject.get("pass");
            email = (String) jsonObject.get("email");
            pass = (String) jsonObject.get("pass");

            System.out.println(email);

            System.out.println(pass);

            Connection con = database.ConFactory.getConnection();

            /*
            
             String q = "insert into " + comp + "_tracking( empid, latitude, longitude, location, time, date ) "
             + "values(" + i + "," + lat + "," + lon + ",'" + addr + "','" + t + "','" + d + "');";
             Statement st = con.createStatement();
             st.executeUpdate(q);
             */
            String q = "select * from " + db.Dbconn.facultie_table_name + " where email=? and password=? ";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                //auth = true;
               auth= rs.getString("type");
            }
        } catch (SQLException | ParseException ex) {
            ex.printStackTrace();
            Logger.getLogger(Jsonrece.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

           
                out.print(auth);
            // TODO output your page here. You may use following sample code. 
            /*
             out.println("<!DOCTYPE html>");
             out.println("<html>");
             out.println("<head>");
             out.println("<title>Servlet Jsonrece</title>");            
             out.println("</head>");
             out.println("<body>");
             out.println("<h1>Servlet Jsonrece at " + request.getContextPath() + "</h1>");
            
             out.println(s2);
             
             
             out.println("</body>");
             out.println("</html>");*/

        } finally {
            out.close();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
