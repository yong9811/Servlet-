package my.com;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class HelloServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException,IOException
	{
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter pw=res.getWriter();
		pw.println("<html>");
		pw.println("<head><title>hello servlet</title></head>");
		pw.println("<body>");		
		pw.println("<h1>Hello Servlet~~</h1>");		
		pw.println("<h2>안녕 서블릿? </h2>");		
		pw.println("</body>");
		pw.println("</html>");
		
		pw.close();
	}
}
