package member.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDAO;
import member.model.MemberVO;

@WebServlet("/MemberList")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		1. MemberDAO객체 생성해서 selectAll()호출
		MemberDAO userDao=new MemberDAO();
		try {
			ArrayList<MemberVO> arr=userDao.selectAll();
//		2. ==> ArrayList<MemberVO> ==>request객체에 저장.   (key: "userAll")
			req.setAttribute("userAll", arr);
//		3. forward방식으로 member/list.jsp 페이지로 이동시키기
			RequestDispatcher disp=req.getRequestDispatcher("member/list.jsp");
			disp.forward(req, res);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
