<!DOCTYPE html>
<%@ include file="includes/includes.jsp"%>

<!--[if lt IE 7 ]> <html lang="en" class="ie6 ielt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="ie7 ielt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="ie8"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<title>Auction System</title>
<link rel="stylesheet" type="text/css" href="css/login.css" />

</head>
<body>
	<div class="container">
		<section id="content">
			<form id="login">
				<h1 id="msg_resp">Log In</h1>
				<div>
					<input type="text" placeholder="Username" name="username" id="username" />
				</div>
				<div>
					<input type="password" placeholder="Password" name="password"
						id="password" />
				</div>
				<div>
					<input type="submit" name="submit" value="Log in" /> 
					
					<input type="hidden" placeholder="Password" name="password"
						id="password" />
						<!-- <a href="#">Lost your password?</a> -->
												
				</div>
			</form>
			<!-- form -->


		</section>
<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/jquery.validate.min.js"></script>

<script type="text/javascript">
		$(document).ready(function() {
			$("#login").validate({
				submitHandler : function(form) {
					$('#msg_resp').html("Processing Login..");
					var data = $(form).serialize();
					$.post("${PREFIX}/login", data, function(d) {
						if (!d.error) {
							$('#msg_resp').html(d.message);
							window.location.href = d.url;
						} else {
							$('#msg_resp').html(d.error);
						}
					});
					return false;
				}
			});
		});
	</script>
		<!-- content -->
	</div>

	
</body>
</html>