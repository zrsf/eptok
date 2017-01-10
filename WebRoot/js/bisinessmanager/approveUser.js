function approveUserTemp(userCode, operatorType, tipMsg) {
	if(confirm('确认要' + tipMsg + '该用户?')) {
		$.ajax({
			url : "user.do?method=approveUser",
			dataType:"json",
			type : "post",
			async : true,
			cache : false,
			data : {"userCode": userCode, "operatorType":operatorType},
			success : function(rs) {
			    var dataObj=eval(rs);//转换为json对象
			    if(dataObj.ret == "false") {
			    	alert(dataObj.msg);
			    }else {
			    	$('form')[0].submit();
			    }
			}
		});
			
	}
}