<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,member.model.*" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>::전체 회원 목록::</title>
</head>
<body>

<div id="wrap">
	<h1>회원 목록 [관리자 페이지 - Admin] </h1>
	<table border="1">
		<tr>
			<th>이름</th>
			<th>아이디</th>
			<th>연락처</th>
			<th>가입일</th>
			<th>삭제</th>
		</tr>
		<!-- ---------------- -->
		<% 
			ArrayList<MemberVO> arr=(ArrayList<MemberVO>)request.getAttribute("userAll");
			if(arr==null||arr.size()==0){
		%>
				<tr>
					<td colspan="5">
					<b>데이터가 없습니다</b>
					</td>
				</tr>	
		<%
			}else{
				for(MemberVO vo:arr){
		%>
					<tr>
						<td><%=vo.getName() %></td>
						<td><%=vo.getId() %></td>
						<td><%=vo.getTel() %></td>
						<td><%=vo.getIndate()%></td>
						<td>
						<a href="#" onclick="goDel('<%=vo.getId()%>')">삭제</a>
						</td>
					</tr>
		<%		}//for-----
			}//else-----------------
		%>
		<!-- ------------------ -->
	</table>
	<!-- 삭제 관련 form --------- -->
	<form name="df" method="post" action="MemberDelete">
		<input type="hidden" name="id">
	</form>
	
</div>
	<script>
		function goDel(uid){
			df.id.value=uid;
			let yn=confirm(uid+"님의 정보를 삭제할까요?");
			if(yn){
				df.submit();
			}
		}//--------------------
	</script>
</body>
</html>