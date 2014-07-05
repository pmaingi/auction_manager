<%@ include file="../includes/includes.jsp"%>

<!DOCTYPE html>
<html lang="en">
<%@ include file="../includes/head.jsp"%>

<body>
	<div id="maincontainer" class="clearfix">
		<!-- header -->
		<header>
			<div class="navbar navbar-fixed-top">
				<div class="navbar-inner">
					<div class="container-fluid">
						<a class="brand" href="${PREFIX}/home"><i
							class="icon-home icon-white"></i> Bid pro</a>
						<ul class="nav user_menu pull-right">

							<li class="divider-vertical hidden-phone hidden-tablet"></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="${PREFIX}/profile">My Profile</a></li>
									<li class="divider"></li>
									<li><a href="${PREFIX}/login">Log Out</a></li>
								</ul></li>
						</ul>

					</div>
				</div>
			</div>

		</header>

		<!-- main content -->
		<div id="contentwrapper">
			<div class="main_content">

				<div class="row-fluid">
                        <div class="span6">
							<h3 class="heading">Edit product </h3>
							<form class="form_validation_ttip" id="editpage">
							<h4 id="processmsg"></h4>
								<div class="formSep">
									<div class="row-fluid">
										<div class="span12">
											<label>Product Name <span class="f_req">*</span></label>
											<input type="text" name="productname" id="productname" class="span8" value="${PRODUCTS.productname}"/>
											
										</div>
									</div>
									<div class="row-fluid">
										<div class="span12">
											<label>Product Code <span class="f_req">*</span></label>
											<input type="text" name="code" id="code" class="span8" value="${PRODUCTS.code}"/>
										</div>
									</div>
									
									<div class="formSep">
									<div class="row-fluid">
										<div class="span12">
											<label>description <span class="f_req">*</span></label>
											<textarea name="description" id="description" cols="10" rows="3" class="span8" >${PRODUCTS.description}</textarea>
										</div>
									</div>
								   </div>
								   <div class="row-fluid">
										<div class="span12">
											<label>Start Price <span class="f_req">*</span></label>
											<input type="text" name="startPrice" class="span8" value="${PRODUCTS.startPrice}"/>
										</div>
									</div>
									<div class="row-fluid">
										<div class="span12">
											<label>Registration Fee <span class="f_req">*</span></label>
											<input type="text" name="registrationFee" class="span8" value="${PRODUCTS.registrationFee}"/>
										</div>
									</div>
								 
								</div>
								
						   <input type="hidden" name="action" value="edit" /> 
						   <input type="hidden" name="id" id="id" value="${PRODUCTS.id}" />
								<div class="form-actions">
									<button class="btn btn-inverse" type="submit">Save changes</button>
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