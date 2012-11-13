/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.servlets;

import at.heli.scada.dal.qualifier.*;
import at.heli.scada.bl.*;
import at.heli.scada.dal.*;
import at.heli.scada.entities.Engineer;
import at.heli.scada.dal.Repository;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Installation;
import at.heli.scada.entities.Measurement;
import at.heli.scada.entities.MeasurementType;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel
 */
public class TestServlet extends HttpServlet {

    @Inject @DbCustomerQualifier
    private Repository<Customer> cr;
    
    @Inject @DbEngineerQualifier
    private Repository<Engineer> er;
    
    @Inject @DbInstallationQualifier
    private InstallationRepository ir;
    
    @Inject @DbMeasurementQualifier
    private MeasurementRepository mr;
    
    @Inject @DbTypeQualifier
    private Repository<MeasurementType> mtr;
   
    CustomerService cbl;
    StatisticService sbl;
    
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");            
            out.println("</head>");
            out.println("<body>");            
            out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
            
            cbl = new CustomerService(ir, cr);
            sbl = new StatisticService(mr, ir, cr);
            
            MeasurementType mt = mtr.getById(1);
            ExecutionResult<Customer> c = cbl.getCustomer(6);
            Installation i = ir.getById(1);
           
            
            if(c.getAffectedObject() == null)
            {
                out.println("Kunde mit ID 6 existiert nicht");
            }
            else
            {
            ExecutionResult<Map<Installation, List<Statistic>>> result = sbl.getStatisticPerDay(c.getAffectedObject().getPersonid(), Date.valueOf("2012-11-12"));
            
            out.println("<h1>Tages-Statistik</h1>");
            for (Map.Entry<Installation, List<Statistic>> entry : result.getAffectedObject().entrySet()) {
                Installation key = entry.getKey();
                List<Statistic> value = entry.getValue();
                
                out.println("<h3>" + key.getDescription() + "</h3>");
                
                for(Statistic stat : value)
                {
                    out.println("<h4>" + stat.getDescription() + "</h4>");
                    out.println("Durchschnitt: " + stat.getAverage() + " " + stat.getUnit() + "<br />");
                    out.println("Maximal " + stat.getMaxvalue() + " " + stat.getUnit() + "<br />");
                    out.println("Minimal: " + stat.getMinvalue() + " " + stat.getUnit() + "<br />");
                }
            }
            
            result = sbl.getStatisticPerMonth(c.getAffectedObject().getPersonid(), Date.valueOf("2012-11-12"));
            
            out.println("<h1>Monats-Statistik</h1>");
            for (Map.Entry<Installation, List<Statistic>> entry : result.getAffectedObject().entrySet()) {
                Installation key = entry.getKey();
                List<Statistic> value = entry.getValue();
                
                out.println("<h3>" + key.getDescription() + "</h3>");
                
                for(Statistic stat : value)
                {
                    out.println("<h4>" + stat.getDescription() + "</h4>");
                    out.println("Durchschnitt: " + stat.getAverage() + " " + stat.getUnit() + "<br />");
                    out.println("Maximal " + stat.getMaxvalue() + " " + stat.getUnit() + "<br />");
                    out.println("Minimal: " + stat.getMinvalue() + " " + stat.getUnit() + "<br />");
                }
            }
            
            result = sbl.getStatisticPerYear(c.getAffectedObject().getPersonid(), Date.valueOf("2012-11-12"));
            
            out.println("<h1>Jahres-Statistik</h1>");
            for (Map.Entry<Installation, List<Statistic>> entry : result.getAffectedObject().entrySet()) {
                Installation key = entry.getKey();
                List<Statistic> value = entry.getValue();
                
                out.println("<h3>" + key.getDescription() + "</h3>");
                
                for(Statistic stat : value)
                {
                    out.println("<h4>" + stat.getDescription() + "</h4>");
                    out.println("Durchschnitt: " + stat.getAverage() + " " + stat.getUnit() + "<br />");
                    out.println("Maximal " + stat.getMaxvalue() + " " + stat.getUnit() + "<br />");
                    out.println("Minimal: " + stat.getMinvalue() + " " + stat.getUnit() + "<br />");
                }
            }
            }
	
            out.println("</body>");
            out.println("</html>");
            
        } 
        catch(Exception e)
            {
                out.println(e);
            }
        finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
