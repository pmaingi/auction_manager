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
							<h3 class="heading">Add product </h3>
							<form class="form_validation_ttip" id="createpage">
							<h4 id="processmsg"></h4>
								<div class="formSep">
									<div class="row-fluid">
										<div class="span12">
											<label>Product Name <span class="f_req">*</span></label>
											<input type="text" name="productname" class="span8" value="${PRODUCTS.productname}"/>
											
										</div>
									</div>
									<div class="row-fluid">
										<div class="span12">
											<label>Product Code <span class="f_req">*</span></label>
											<input type="text" name="code" class="span8" value="${PRODUCTS.code}"/>
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
								
						   <input type="hidden" name="action" value="create" /> 
								<div class="form-actions">
									<button class="btn btn-inverse" type="submit">Add product</button>
									<button class="btn">Cancel</button>
								</div>
							</form>
                        </div>
                       
                    </div>
                    
				<!-- hide elements (for later use) -->
				<div class="hide">
					<!-- actions for datatables -->
					<div class="dt_gal_actions">
						<div class="btn-group">
							<button data-toggle="dropdown" class="btn dropdown-toggle">
								Action <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="#" class="delete_rows_dt"
									data-tableid="dt_gal"><i class="icon-trash"></i> Delete</a></li>
								<li><a href="javascript:void(0)">Lorem ipsum</a></li>
								</ul>
						</div>
					</div>
					
				</div>

			</div>
		</div>
	<%@ include file="../includes/sidebar.jsp"%>

		<%@ include file="../includes/scripts.jsp"%>

	</div>
</body>
</html>