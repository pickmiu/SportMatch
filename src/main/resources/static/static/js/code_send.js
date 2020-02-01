var code_send=document.getElementById("code_send");
var email=document.getElementById("email");
var sec;
function Code_send(){
	code_send.style.opacity="0.6";
	code_send.style.pointerEvents="none";
	if(sec>=0){
		if(sec<10) code_send.innerHTML= "0" + sec + "S";
		else code_send.innerHTML=sec + "S";
		sec--;
	}
	else{
		clearInterval(send);
		code_send.style.opacity="1";
		code_send.style.pointerEvents="visible";
		code_send.innerHTML="重发";
	}
}

code_send.onclick=function(){
	var email_text=document.getElementById("email").value.trim();
	var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	isOK = reg.test(email_text);
	if(!isOK){
		email.value="";
		email.setAttribute("placeholder","邮箱格式不正确");
		email.classList.add("invalid");
	}
	else{
		sec=60;
		var email = $("#email").val();
		var data = "email="+email+"&operation="+"注册";
		$.ajax({
		type: 'POST',
		url: '/mail',
		data: data,
		dataType: 'json',
		success: function(data){
			if(data.message == 0)
			{
				$("#password1").val("");
				$("#verify").attr("placeholder","邮箱验证码发送失败");
				$("#verify").addClass("invalid");
			}
			else
			{
				console.log("邮箱验证码发送成功！");
			}
		},
		});
		send=setInterval(Code_send,1000);
	}
}

email.oninput=function(){
	var email_text=document.getElementById("email").value.trim();
	console.log(email_text);
	if(email_text == '')
	{
		code_send.style.opacity="0.6";
		code_send.style.pointerEvents="none";
	}
	else{
		code_send.style.opacity="1";
		code_send.style.pointerEvents="visible";
	}
}