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
							<h3 class="heading">Edit Auction </h3>
							<form class="form_validation_ttip" id="editauction">
							<h4 id="processmsg"></h4>
					
								<!--<div class="formSep">
									<div class="row-fluid">
										<div class="span12">
											<label>End Date <span class="f_req">*</span></label>
											 <input type="text" name="endDate" id="endDate" class="span8" value="${AUCTIONS.endDate}"/> 
											 
										</div>
									</div>
									
								 </div> -->
								<div class="formSep">
								<!--<div class="well"> -->
								<div class="row-fluid">
									<div class="span12">
									<label>End Date <span class="f_req">*</span></label>
									 <div class="input-append" id="datetimepicker"> 
                                       <input type="text" data-format="dd/MM/yyyy hh:mm:ss" value="${AUCTIONS.endDate} "/><!-- class="input-xxlarge" -->
                                         <span class="add-on">
                                           <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                                         </span>
                                     </div>
                                     <div>
                                        <input id="realdate" name="realdate" type="text" disabled="disabled" />
                                     </div>
								   </div>
								   </div>
								<!-- </div> -->
								 </div>
								 
						
								
						   <input type="hidden" name="action" value="edit" /> 
						   <input type="hidden" name="id" id="id" value="${AUCTIONS.id}" />
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