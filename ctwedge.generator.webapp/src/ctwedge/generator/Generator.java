package ctwedge.generator;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ctwedge.generator.casa.CASAConstraintException;
import ctwedge.generator.ipapi.Ipapi;
import ctwedge.generator.util.Utility;

/**
 * Servlet implementation class Generator
 * It is the REST Service module of the Generator
 */
@WebServlet("/")
public class Generator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static ServletContext context;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Generator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		context = request.getServletContext();
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String model = request.getParameter("model");
		String generator = request.getParameter("generator");
		String strength = request.getParameter("strength");
		String ignoreConstraints = request.getParameter("ignConstr");
		Ipapi.saveData("/var/www/html/3dcar/ctwedgelog/", generator+" "+strength+" "+model, getIP(request));
		int t = 2;
		try {t = Integer.parseInt(strength);} catch (Exception e) {}
		boolean ignoreC = false;
		try {ignoreC = Boolean.parseBoolean(ignoreConstraints);} catch (Exception e) {}
		String res = "Input parameters:\n"+model+"\n"+generator+"\n"+t+"\n"+ignoreC+"\n";
		//response.getWriter().append(res);
		
		String ts = "";
		try {
			ts = Utility.getTestSuite(model, generator, t, ignoreC).toString();
		} catch (CASAConstraintException e) {
			response.getWriter().append("Exception: arithmetic and relational expressions in constraints are not supported in CASA");
		} catch (Exception e) {e.printStackTrace();}
		
		res = ts;
		response.getWriter().append(res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public String getIP(HttpServletRequest request) {
    	String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
    }
	
}