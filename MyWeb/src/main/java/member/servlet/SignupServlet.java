package member.servlet;
import member.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out=res.getWriter();
		req.setCharacterEncoding("utf-8");
		
		String name=req.getParameter("name");
		String id=req.getParameter("id");
		String pw=req.getParameter("pw");
		String tel=req.getParameter("tel");
		out.println(name+"/"+id+"/"+pw+"/"+tel+"<br>");
		
		if(name==null||id==null||pw==null||name.trim().isEmpty()||id.trim().isEmpty()||pw.trim().isEmpty()) {
			out.println("<script>");
			out.println("alert('아이디,이름,비밀번호는 모두 입력해야 해요')");
			out.println("history.back();");
			out.println("</script>");
			return;
		}
		MemberVO user=new MemberVO(id,pw,name,tel,null);
		
		MemberDAO userDao=new MemberDAO();
		try {
			int n=userDao.insertMember(user);
			String msg=(n>0)?"회원가입 완료!!- 홈페이지로 이동합니다":"회원가입 실패-다시 가입하세요";
			String loc=(n>0)?"index.html":"member/join.html";
			out.println("<script>");
			out.println("alert('"+msg+"')");
			out.println("location.href='"+loc+"'");
			out.println("</script>");
		} catch (SQLException e) {
			out.println("<b>이미 사용중인 아이디 이거나 서버 에러입니다</b>");
			e.printStackTrace();
		}
		
		out.close();
	}

}
