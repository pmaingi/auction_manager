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
							<h3 class="heading">Send Message</h3>
							<form class="form_validation_ttip" id="createbroadcast">
							<h4 id="processmsg"></h4>
								<div class="row-fluid">
										<div class="span12">
											<label>msisdn <span class="f_req">*</span></label>
											<input type="text" name="msisdn" class="span8" value="${BROADCAST.productname}"/>
											
										</div>
									</div>
									<div class="formSep">
									<div class="row-fluid">
										<div class="span12">
											<label>message <span class="f_req">*</span></label>
											<textarea name="message" id="message" cols="20" rows="10" class="span8" >${BROADCAST.description}</textarea>
										</div>
									</div>
								   </div>

									
								
						   <input type="hidden" name="action" value="create" /> 
								<div class="form-actions">
									<button class="btn btn-inverse" type="submit">Send message</button>
									<button class="btn">Cancel</button>
								</div>
							</form>
                        </div>
                       
                    </div>
                 </div>
		</div>
	<%@ include file="../includes/sidebar.jsp"%>

	<%@ include file="../includes/scripts.jsp"%>

	</div>
</body>
</html>