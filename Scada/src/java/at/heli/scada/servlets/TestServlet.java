/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.servlets;

import at.heli.scada.entities.Statistic;
import at.heli.scada.bl.interfaces.ExecutionResult;
import at.heli.scada.dal.interfaces.InstallationRepository;
import at.heli.scada.dal.interfaces.MeasurementRepository;
import at.heli.scada.dal.qualifier.*;
import at.heli.scada.bl.*;
import at.heli.scada.dal.interfaces.CustomerRepository;
import at.heli.scada.dal.interfaces.EngineerRepository;
import at.heli.scada.dal.interfaces.TypeRepository;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Engineer;
import at.heli.scada.entities.Installation;
import at.heli.scada.entities.MeasurementType;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
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
    private CustomerRepository cr;
    
    @Inject @DbEngineerQualifier
    private EngineerRepository er;
    
    @Inject @DbInstallationQualifier
    private InstallationRepository ir;
    
    @Inject @DbMeasurementQualifier
    private MeasurementRepository mr;
    
    @Inject @DbTypeQualifier
    private TypeRepository mtr;
   
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
            
            Engineer e = er.getById(1);
            
            ExecutionResult<Customer> resCustomer = cbl.getCustomer(2);
            
            MeasurementType mt = new MeasurementType();
            mt.setDescription("Innentemperatur");
            mt.setUnit("°C");
            mt.setValuemax(BigDecimal.valueOf(50.00));
            mt.setValuemin(BigDecimal.valueOf(-10.00));
            mtr.save(mt);
            
            //MeasurementType mt = mtr.getById(1);
            ExecutionResult<Customer> c = cbl.getCustomer(1);
            Installation i = ir.getById(1);
           
            
            if(c.getAffectedObject() == null)
            {
                out.println("Kunde mit ID 1 existiert nicht");
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
