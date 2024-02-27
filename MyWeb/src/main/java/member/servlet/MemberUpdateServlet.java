package member.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDAO;
import member.model.MemberVO;


@WebServlet("/MemberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out=res.getWriter();
		
		String id=req.getParameter("id");
		out.println("id: "+id+"<br>");
		
		if(id==null||id.trim().isEmpty()) {
			res.sendRedirect("member/mypage.html");
			return;
		}
		System.out.println("여기를 수행할까요?");
		
		MemberDAO userDao=new MemberDAO();
		try {
			MemberVO user=userDao.findById(id.trim());
			//out.println("user: "+user+", Name: "+user.getName()+"<br>");
			
			//결과값을 받아서 request 또는 session 에 저장해서
			req.setAttribute("user", user); //key =value 짝을 이루어 저장한다
			
			//화면단으로 이동시킨다=>edit.jsp로 이동
			/* # 페이지 이동 방식
			 * <1> redirect방식으로 이동 =>sendRedirect(이동할 페이지) 이용
			 * 		- 지정된 페이지로 서버에 새롭게 요청을 보내서 응답하는 방식
			 *      - 브라우저의 url을 이동할 페이지로 바꿔서 새로운 요청을 보낸다
			 *        따라서 request에 저장한 값이 있으면 꺼내서 사용할 수 없다.(왜? 새로운 request를 보내므로)
			 *      - 브라우저 url은 이동한 페이지의 url을 나타낸다.  
			 * <2> forward방식으로 이동 => RequestDispatcher의 forward() 이용
			 *      - 서버 내부에서 지정된 페이지로 이동한다
			 *      - 이동된 페이지에서는 request를 함께 공유한다
			 *        따라서 request에 저장한 값이 있으면 이동된 페이지에서 꺼내서 사용할 수 있다
			 *      - 브라우저의 url은 이전 url을 가리킨다.  
			 * */
			
			RequestDispatcher disp=req.getRequestDispatcher("member/edit.jsp");//이동할 페이지 지정
			disp.forward(req, res);//forward 방식으로 이동
			
			//res.sendRedirect("member/edit.jsp");//redirect방식으로 이동
			
		}catch(SQLException e) {
			//out.println("<b>서버 에러: "+e.getMessage()+"</b><br>");
			throw new ServletException(e);//브라우저에 스택기록 출력
			//e.printStackTrace();
		}
		
		out.close();
	}

}



