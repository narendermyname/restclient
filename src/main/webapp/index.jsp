<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/restclient/js/jquery-1.8.3.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>REST Client</title>
<script type="text/javascript">
	function callMe() {
		var method = $("#_method").val();
		var url = $("#_url").val();
		var requestBody = $("#_requestBody").val();
		var ctype = $("#_ctype").val();
		var accept = $("#_accept").val();
		if (url === '') {
			alert("Please Enter Proper Url");
			return false;
			_
		}
		/* if (method !== 'GET' || method !== 'PUT' && body == '') {
			alert("Body can not be empty methos is put or post");
			return false;
		} */
		var postData = {
			'method' : method,
			'url' : url,
			'requestBody' : requestBody,
			'accept' : accept,
			'ctype' : ctype
		};
		$.ajax({
			type : "POST",
			url : "/restclient/executeRest",
			dataType : 'text/javascript',
			data : postData,
			success: function(data){         
                $("#_responseBody").val(data.result);
             },
             error: function(XMLHttpRequest, textStatus, errorThrown){
                $("#_responseBody").val(XMLHttpRequest.responseText);
             }
		});
		return false;
	}
</script>
</head>
<body style="color: green;">
	<center>
		<h3>REST Client</h3>
		<div>
			<s:form action="" name="executeRest" method="POST">
				<s:textfield key="URL" size="100" name="url" />
				<s:select list="{'GET','POST','PUT','DELETE'}" name="method"
					key="Method" />
				<s:select list="{'application/json'}" name="accept" key="Accept:" />
				<s:select list="{'application/json'}" name="ctype"
					key="Content-Type:" />
				<s:textarea key="Request" cols="100" rows="12" name="requestBody" />
				<s:textarea key="Response" cols="100" rows="10" name="responseBody"/>
				<s:submit id="submit" onclick="return callMe();" />
				<s:reset />
			</s:form>
		</div>
	</center>
</body>
</html>