<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./common/taglibs.jsp"%>
<%@ include file="./common/vars.jsp"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>停车场管理系统</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="${contextPath}/assets/src/page/login/bootstrap.min.css" />
<link rel="stylesheet" href="${contextPath}/assets/src/page/login/css/camera.css" />
<link rel="stylesheet" href="${contextPath}/assets/src/page/login/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${contextPath}/assets/src/page/login/matrix-login.css" />
<link href="${contextPath}/assets/src/page/login/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="${contextPath}/assets/src/page/login/js/jquery-1.5.1.min.js"></script>

</head>
<body>

	<div
		style="width:100%;text-align: center;margin: 0 auto;position: absolute;">
		<div id="loginbox">
			<form action="" method="post" name="loginForm"
				id="loginForm">
				<div class="control-group normal_text">
					<h3>
						停车场管理系统
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg"><i><img height="37"
									src="${contextPath}/assets/src/page/login/user.png" /></i></span><input type="text"
								name="loginname" id="loginname" value=""
								placeholder="请输入用户名" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly"><i><img height="37"
									src="${contextPath}/assets/src/page/login/suo.png" /></i></span><input type="password"
								name="password" id="password" placeholder="请输入密码"
								value="" />
						</div>
					</div>
				</div>
				<div style="float:right;padding-right:10%;">
					<div style="float: left;margin-top:3px;margin-right:2px;">
						<font color="white">记住密码</font>
					</div>
					<div style="float: left;">
						<input name="form-field-checkbox" id="saveid" type="checkbox"
							onclick="savePaw();" style="padding-top:0px;" />
					</div>
				</div>
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">
						<div style="float: left;" class="codediv">
							<input type="text" name="code" id="code" class="login_code"
								style="height:24px; padding-top:0px;" />
						</div>
						<div style="float: left;">
							<i><img style="height:30px;" id="codeImg" alt="点击更换"
								title="点击更换" src="" /></i>
						</div>

						<span class="pull-right" style="padding-right:3%;"><a
							href="javascript:quxiao();" class="btn btn-success">取消</a></span> <span
							class="pull-right"><a onclick="severCheck();"
							class="flip-link btn btn-info" id="to-recover">登录</a></span>

					</div>
				</div>

			</form>


			<div class="controls">
				<div class="main_input_box">
					<!--<font color="white"><span id="nameerr">Copyright © FH
							2100</span></font>-->
				</div>
			</div>
		</div>
	</div>
	<div id="templatemo_banner_slide" class="container_wapper">
		<div class="camera_wrap camera_emboss" id="camera_slide">
			<div data-src="${contextPath}/assets/src/page/login/images/banner_slide_01.jpg"></div>
			<div data-src="${contextPath}/assets/src/page/login/images/banner_slide_02.jpg"></div>
			<div data-src="${contextPath}/assets/src/page/login/images/banner_slide_03.jpg"></div>
		</div>
		<!-- #camera_wrap_3 -->
	</div>

	<script type="text/javascript">
    var a = '<c:out value="${sessionScope.loginImage}"/>';
		//服务器校验
		function severCheck(){
			if(check()){
				var loginname = $("#loginname").val();
				var password = $("#password").val();
				var code = $("#code").val();
				$.ajax({
					type: "POST",
					url: 'loginCheck.do',
			    	data: {
                        username: loginname,
                        password: password,
                        code: code
                    },
					dataType:'json',
					cache: false,
					success: function(data) {
                        if (data.success) {
                            saveCookie();
							window.location.href="index.do";
                        }
                        else {
                            $("#loginname").tips({
                                side : 1,
                                msg : data.message,
                                bg : '#FF5080',
                                time : 2
                            });
                            changeCode();
                        }
					}
				});
			}
		}

		$(document).ready(function() {
			changeCode();
			$("#codeImg").bind("click", changeCode);
		});

		$(document).keyup(function(event) {
			if (event.keyCode == 13) {
				$("#to-recover").trigger("click");
			}
		});

		function genTimestamp() {
			var time = new Date();
			return time.getTime();
		}

		function changeCode() {
			$("#codeImg").attr("src", "imageCheck.do?v=" + genTimestamp());
		}

		//客户端校验
		function check() {

			if ($("#loginname").val() == "") {

				$("#loginname").tips({
					side : 2,
					msg : '用户名不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#loginname").focus();
				return false;
			} else {
				$("#loginname").val(jQuery.trim($('#loginname').val()));
			}

			if ($("#password").val() == "") {

				$("#password").tips({
					side : 2,
					msg : '密码不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#password").focus();
				return false;
			}
			if ($("#code").val() == "") {

				$("#code").tips({
					side : 1,
					msg : '验证码不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#code").focus();
				return false;
			}

			$("#loginbox").tips({
				side : 1,
				msg : '正在登录 , 请稍后 ...',
				bg : '#68B500',
				time : 10
			});

			return true;
		}

		function savePaw() {
			if (!$("#saveid").attr("checked")) {
				$.cookie('loginname', '', {
					expires : -1
				});
				$.cookie('password', '', {
					expires : -1
				});
				$("#loginname").val('');
				$("#password").val('');
			}
		}

		function saveCookie() {
			if ($("#saveid").attr("checked")) {
				$.cookie('loginname', $("#loginname").val(), {
					expires : 7
				});
				$.cookie('password', $("#password").val(), {
					expires : 7
				});
			}
		}
		function quxiao() {
			$("#loginname").val('');
			$("#password").val('');
		}

		jQuery(function() {
			var loginname = $.cookie('loginname');
			var password = $.cookie('password');
			if (typeof(loginname) != "undefined"
					&& typeof(password) != "undefined") {
				$("#loginname").val(loginname);
				$("#password").val(password);
				$("#saveid").attr("checked", true);
				$("#code").focus();
			}
		});
	</script>
	<script>
		//TOCMAT重启之后 点击左侧列表跳转登录首页
		if (window != top) {
			top.location.href = location.href;
		}
	</script>

	<script src="${contextPath}/assets/src/page/login/js/bootstrap.min.js"></script>
    <script src="${contextPath}/assets/src/page/login/js/jquery-1.7.2.js"></script>
	<script src="${contextPath}/assets/src/page/login/js/jquery.easing.1.3.js"></script>
	<script src="${contextPath}/assets/src/page/login/js/jquery.mobile.customized.min.js"></script>
	<script src="${contextPath}/assets/src/page/login/js/camera.min.js"></script>
	<script src="${contextPath}/assets/src/page/login/js/templatemo_script.js"></script>
	<script type="text/javascript" src="${contextPath}/assets/src/page/login/js/jquery.tips.js"></script>
	<script type="text/javascript" src="${contextPath}/assets/src/page/login/js/jquery.cookie.js"></script>
</body>

</html>
