<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
TD {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
.wordId{
	color : red;
}
</STYLE>

<META content="MSHTML 6.00.6000.16809" name=GENERATOR>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
		
	function checkPassword(){
		//获取登入名
		var oldUser_code = $("#user_code").val();
		//获取输入的旧密码的值
		var oldPwd = $("#user_password").val();
		
		if(oldPwd == ""){
			$("#wordId").addClass("wordId");
			$("#wordId").html("密码不能为空");
		}else{
			var url = "${pageContext.request.contextPath}/user_checkPassword.action?user_code="+oldUser_code+"&user_password="+oldPwd+"";
			$.get(url,function(data){
				if(data == 1){
					$("#wordId").removeClass("wordId");
					$("#wordId").html("密码正确");
				}else{
					$("#wordId").addClass("wordId");
					$("#wordId").html("密码错误");
				}
			});
		}
	}
	/* 校验表单提交 */
	function checkForm(){
		checkPassword();
		if($("#wordId").html() != "密码正确"){
			return false;
		}
	}
	
</script>

</HEAD>
<BODY>
<FORM id=form1 name=form1 action="${pageContext.request.contextPath }/user_password.action" onsubmit="return checkForm()" method=post>

<DIV id=UpdatePanel1>
<DIV id=div1 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
<DIV id=div2 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>


<DIV>&nbsp;&nbsp; </DIV>
<DIV>
<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
  <TBODY>
  <!-- <TR>
    <TD style="HEIGHT: 105px"><IMG src="images/login_1.gif" 
  border=0></TD></TR> -->
  <TR>
    <TD background=images/login_2.jpg height=300>
      <TABLE height=300 cellPadding=0 width=900 border=0>
        <TBODY>
        <TR>
          <TD colSpan=2 height=35></TD></TR>
        <TR>
          <TD width=360></TD>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 border=0>
              <TBODY>
              <TR>
                <TD style="HEIGHT: 28px" width=80></TD>
                <TD style="HEIGHT: 28px" width=150>
                <INPUT id="user_code" type="hidden" style="WIDTH: 130px" name="user_code" value="${model.user_code }"></TD>
                <TD style="HEIGHT: 28px" width=370>
                <SPAN id="codeId" style="FONT-WEIGHT: bold;"></SPAN></TD></TR>
              <TR>
                <TD style="HEIGHT: 28px">旧密码：</TD>
                <TD style="HEIGHT: 28px"><INPUT id="user_password" style="WIDTH: 130px" 
                  type="password" name="user_password" onblur = "checkPassword()">
                  </TD>
                <TD style="HEIGHT: 28px"><SPAN id="wordId" 
                  style="FONT-WEIGHT: bold;"></SPAN></TD></TR>
              <TR>
              <TR>
                <TD style="HEIGHT: 28px">新密码：</TD>
                <TD style="HEIGHT: 28px"><INPUT id="u_password" style="WIDTH: 130px" 
                  type="password" name="u_password"></TD>
                <TD style="HEIGHT: 28px"><SPAN id=RequiredFieldValidator4 
                  style="FONT-WEIGHT: bold;"></SPAN></TD></TR>
              <TR>
                <TD></TD>
                <TD>
                  <input type="submit" value="修改" /> 
              </TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>
  <!-- <TR>
    <TD><IMG src="images/login_3.jpg" 
border=0></TD></TR> --></TBODY></TABLE></DIV></DIV>


</FORM></BODY></HTML>


</body>
</html>