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
							<h3 class="heading">Add to Auction </h3>
							<form class="form_validation_ttip" id="addpage">
							<h4 id="processmsg"></h4>
								<div class="formSep">
									<div class="row-fluid">
										<div class="span12">
											<label>Add to Auction(set 1 to add) - NB: any other number won't be added to auction  <span class="f_req">*</span></label>
											<input type="text" name="addAuction" id="addAuction" class="span8" value="${PRODUCTS.addAuction}"/>
											
										</div>
									</div>	
								 
								</div>
                      <!-- 
								<div class="formSep">
									<div class="row-fluid">
										<div class="span12">
											<label>End date<span class="f_req">*</span></label>
											
											 <input type="text" name="addAuction" id="addAuction" class="span8" value="${PRODUCTS.addAuction}"/> 
											
											<input type="text" name="endDate" id="endDate" value="" />
										</div>
									</div>	
								 
								</div>
							 -->	
								
								
						   <input type="hidden" name="action" value="add" /> 
						   <input type="hidden" name="id" id="id" value="${PRODUCTS.id}" />
								<div class="form-actions">
									<button class="btn btn-inverse" type="submit">Add to Auction</button>
									<button class="btn">Cancel</button>
								</div>
							</form>
                        </div>
                       
                    </div>
                    
			</div>
		</div>

		<!-- sidebar -->
		<a href="javascript:void(0)" class="sidebar_switch on_switch ttip_r"
			title="Hide Sidebar">Sidebar switch</a>
		<div class="sidebar">

			<div class="antiScroll">
				<div class="antiscroll-inner">
					<div class="antiscroll-content">

						<div class="sidebar_inner">
							<form action="{PREFIX}/search_page" class="input-append" method="post">
								<input autocomplete="off" name="query"
									class="search_query input-medium" size="16" type="text"
									placeholder="Search..." />
								<button type="submit" class="btn">
									<i class="icon-search"></i>
								</button>
							</form>
							<div id="side_accordion" class="accordion">

								<div class="accordion-group">
									<div class="accordion-heading">
										<a href="#collapseOne" data-parent="#side_accordion"
											data-toggle="collapse" class="accordion-toggle"> <i
											class="icon-folder-close"></i> Content
										</a>
									</div>
									<div class="accordion-body collapse" id="collapseOne">
										<div class="accordion-inner">
											<ul class="nav nav-list">
												<li><a href="${PREFIX}/subscription">Subscription</a></li>
												
											</ul>
										</div>
									</div>
								</div>

							</div>

							<div class="push"></div>
						</div>

					</div>
				</div>
			</div>

		</div>

		<%@ include file="../includes/scripts.jsp"%>

	</div>
</body>
</html>