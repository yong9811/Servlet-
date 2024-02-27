package member.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDAO;

@WebServlet("/MemberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException,IOException{
		doGet(req,res);
	}//------------------------------------
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out=res.getWriter();//2byte기반 출력 스트림
		//ServletOutputStream sos=res.getOutputStream(); //1byte기반
		
		//1. 사용자가 입력한 값 받기
		String uid=req.getParameter("id");
		
		out.println("<b>"+uid+"</b><br>");//브라우저에 출력
		System.out.println("uid: "+uid);//톰캣 콘솔에 출력
		
		//2. 유효성 체크
		if(uid==null||uid.trim().isEmpty()) {
			res.sendRedirect("member/mypage.html");
			//페이지 이동을 시킴==> 브라우저의 url을 member/mypage.html
			//로 변경해서 서버에 새롭게 요청을 보내는 방식으로 페이지를 
			//이동한다
			return;
		}
		MemberDAO userDao=new MemberDAO();
		try {
			int n=userDao.deleteMember(uid);
			String msg=(n>0)?"회원 탈퇴 완료":"회원탈퇴 실패-아이디를 확인하세요";
			String loc=(n>0)?"index.html":"javascript:history.back()";
			
			out.println("<script>");
			out.println("alert('"+msg+"')");
			out.println("location.href='"+loc+"'");
			out.println("</script>");
		
		}catch(SQLException e) {
			out.println("<b>서버 에러: "+e.getMessage()+"</b><br>");
			e.printStackTrace();
		}
		out.close();
	}

}
