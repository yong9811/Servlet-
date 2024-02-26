package my.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String uid=req.getParameter("id");
		String upw=req.getParameter("pw");
		System.out.println(uid+"/"+upw);
		
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out=res.getWriter();
		
		if(uid==null||upw==null||uid.trim().isEmpty()||upw.trim().isEmpty()) {
			out.println("<script>");
			out.println("alert('아이디와 비밀번호를 입력하세요');");
			out.println("history.back();");
			out.println("</script>");
			return;
		}
		
		out.println("<h1 style='color:blue'>");
		out.println(uid+"님 환영합니다~");
		out.println("</h1>");
		
		out.close();
	}

}
