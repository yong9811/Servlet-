package member.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.model.*;

@WebServlet("/Signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out=res.getWriter();
		//0. post방식일때 한글처리
		req.setCharacterEncoding("utf-8");
		
		//1. 사용자가 입력한 값 받기
		String name=req.getParameter("name");
		String id=req.getParameter("id");
		String pw=req.getParameter("pw");
		String tel=req.getParameter("tel");
		out.println(name+"/"+id+"/"+pw+"/"+tel+"<br>");
		
		//2. 유효성 체크 (name,id,pw)
		if(name==null||id==null||pw==null||name.trim().isEmpty()||id.trim().isEmpty()||pw.trim().isEmpty()) {
			out.println("<script>");
			out.println("alert('아이디,이름,비밀번호는 모두 입력해야 해요')");
			out.println("history.back()");
			out.println("</script>");
			return;
		}
		
		//3. 1번에서 받은 값을 MemberVO객체에 담아주기
		MemberVO user=new MemberVO(id,pw,name,tel,null);
		
		//4. MemberDAO객체 생성해서 insertMember()호출하기
		MemberDAO userDao=new MemberDAO();
		try {		
			//5. 그 결과 메시지 처리 ==> alert()로 보여주기
			int n=userDao.insertMember(user);
			String msg=(n>0)?"회원가입 완료!!- 홈페이지로 이동합니다":"회원가입 실패-다시 가입하세요";
			String loc=(n>0)?"index.html":"member/join.html";
		
			//6. 페이지 이동 ===> index.html로
			out.println("<script>");
			out.println("alert('"+msg+"')");
			out.println("location.href='"+loc+"'");
			out.println("</script>");
			
		}catch(SQLException e) {
			out.println("<b>이미 사용중인 아이디 이거나 서버 에러입니다</b>");
			e.printStackTrace();
		}
		out.close();
	}
}
