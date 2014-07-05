<%@ include file="includes/includes.jsp"%>

<!DOCTYPE html>
<html lang="en">
<%@ include file="includes/head.jsp"%>

<body>
	<!-- <div id="loading_layer" style="display: none">
		<img src="${PREFIX}/img/ajax_loader.gif" alt="" />
	</div>
     -->

	<div id="maincontainer" class="clearfix">
		<!-- header -->
		<header>
			<div class="navbar navbar-fixed-top">
				<div class="navbar-inner">
					<div class="container-fluid">
						<a class="brand" href="${PREFIX}/home.jsp"><i
							class="icon-home icon-white"></i> Bid pro</a>
						<ul class="nav user_menu pull-right">

							<li class="divider-vertical hidden-phone hidden-tablet"></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">peter <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="${PREFIX}/user_profile.jsp">My Profile</a></li>
									<li><a href="javascrip:void(0)">Another action</a></li>
									<li class="divider"></li>
									<li><a href="${PREFIX}/login.jsp">Log Out</a></li>
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
					<div class="span12">
						<h3 class="heading">Products</h3>
						<div>
							<table>
								<tr>
									<td class="sepV_a"><a href="${PREFIX}/product?create=true"
										title="Add"><i class="icon-eye-open"></i> Add Product</a></td>
								</tr>
							</table>
						</div>
						<table class="table table-bordered table-striped table_vam"
							id="dt_gal">
							<thead>
								<tr>
									<th class="table_checkbox"><input type="checkbox"
										name="select_rows" class="select_rows" data-tableid="dt_gal" /></th>

									<th>Product Name</th>
									<th>Product Code</th>
									<th>Description</th>
									<!--<th>Start Price</th> -->
									<!--  <th>Registration Fee</th> -->
									<th>DateCreated</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="product" items="${PRODUCTS}">
									<tr>
										<td><input type="checkbox" name="row_sel" class="row_sel" /></td>

										<td>${product.productName}</td>
										<td>${product.productCode}</td>
										<td>${product.description}</td>
										<!--<td>${product.startPrice}</td> -->
										<!-- <td>${product.registrationFee}</td> -->
										<td>${product.dateCreated}</td>

										<td><a
											href="${PREFIX}/product?edit=true&id=${product.id}"
											class="sepV_a" title="Edit"><i class="icon-pencil"></i>
												Edit </a> <a href="#" title="Delete"><i class="icon-trash"></i></a></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>

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
					<!-- confirmation box -->
					<div id="confirm_dialog" class="cbox_content">
						<div class="sepH_c tac">
							<strong>Are you sure you want to delete this row(s)?</strong>
						</div>
						<div class="tac">
							<a href="#" class="btn btn-gebo confirm_yes">Yes</a> <a href="#"
								class="btn confirm_no">No</a>
						</div>
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
							<form action="search_page.jsp" class="input-append" method="post">
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
												<li><a href="javascript:void(0)">Articles</a></li>
												<li><a href="javascript:void(0)">News</a></li>
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

		<%@ include file="includes/scripts.jsp"%>

	</div>
</body>
</html>