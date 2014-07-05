<%@ include file="../includes/includes.jsp"%>
	
<%@ include file="../includes/includes.jsp"%>

<!DOCTYPE html>
<html lang="en">
<%@ include file="../includes/head.jsp"%>

<body>
	<div id="maincontainer" class="clearfix">
		<!-- header -->
		<%@ include file="../includes/header.jsp"%>

		<!-- main content -->
		<div id="contentwrapper">
			<div class="main_content">

				<div class="row-fluid">
                        <div class="span6">
							<header><h3>Edit ${ADMIN.username}</h3></header>
							<form class="form_validation_ttip" id="edituser">
							<h4 id="processmsg"></h4>
								<div class="formSep">
									<div class="row-fluid">
										<div class="span12">
											<label>Username <span class="f_req">*</span></label>
											<input type="text" name="username" id="username" class="span8" value="${ADMIN.username}"/>
											
										</div>
									</div>
									<div class="row-fluid">
										<div class="span12">
											<label>Password <span class="f_req">*</span></label>
											<input type="password" name="password" id="password"  required />
										</div>
									</div>
									
									<div class="formSep">
									<div class="row-fluid">
										<div class="span12">
											<label>Confirm Password <span class="f_req">*</span></label>
											<input type="password" name="cpassword" id="cpassword"  required />
										</div>
									</div>
								   </div>
								   <div class="row-fluid">
										<div class="span12">
											<label>Role <span class="f_req">*</span></label>
											<select name="role" value="${ADMIN.role}" required>
					                         <option value="">--Select Role--</option>
					                         <c:forEach var="role" items="${ROLES}">
						                        <option value="${role.id}" <c:if test="${role.id == ADMIN.role}">selected="selected"</c:if>>${role.role}</option>
					                        </c:forEach>
				                            </select>
										</div>
									</div>
									
								</div>
								
						   <input type="hidden" name="action" value="edit" /> 
						   <input type="hidden" name="id" id="id" value="${ADMIN.id}" />
								<div class="form-actions">
									<button class="btn btn-inverse" type="submit">Update</button>
									<button class="btn">Cancel</button>
								</div>
							</form>
                        </div>
                       
                    </div>
                    
			</div>
		</div>

<%@ include file="../includes/sidebar.jsp"%>


<%@ include file="../includes/scripts.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
	$("#edituser").submit(function() {
		$('#processmsg').html("Processing...");
		$('#processmsg').removeClass("alert_success").removeClass("alert_error").addClass("alert_warning");
		var data = $(this).serialize();
		$.post("${PREFIX}/admin",data, function(d) {
			if(!d.error){
				$('#processmsg').removeClass("alert_warning").addClass("alert_success");
				$('#processmsg').html(d.message);
				
				window.location.href = prefix + "/home";

			}else{
				$('#processmsg').removeClass("alert_warning").addClass("alert_error");
				$('#processmsg').html(d.error);
			}
		});
		return false;
	});
})
</script>
		

</div>
</body>
</html>	