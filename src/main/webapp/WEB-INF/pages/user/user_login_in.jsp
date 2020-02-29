<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form class="form-login-in" id="form-login-in">
  <div class="form-group">
    <label class="col-sm-2 control-label">用户名称</label>
    <div class="col-sm-10">
      <input class="form-control validate[required,ajax[ajaxUserName]]" id="userName" name="userName">
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">用户账号</label>
    <div class="col-sm-10">
      <input class="form-control validate[required,ajax[ajaxUserCode]]" id="userCode" name="userCode">
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">用户密码</label>
    <div class="col-sm-10">
      <input type="password" class="form-control validate[required]" id="userPass" name="userPass">
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">用户手机</label>
    <div class="col-sm-10">
      <input class="form-control validate[required,custom[phone]]" id="userPhone"  name="userPhone">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button class="btn btn-default btn-primary" id="login_in">注册</button>
    </div>
  </div>
</form>
<script type="text/javascript">
$(document).ready(function() { 
	/* 对表单绑定校验 */
	$("#form-login-in").validationEngine();
});
	$('#login_in').on('click',function(){
		$.ajax({
			type : 'post',
			url : 'user/add',
			data : $('#form-login-in').serialize(),
			dataType : 'json',
			success : function(result) {
				if (result) {
					alert("注册成功！！")
				}
			}
		});
	});
</script>