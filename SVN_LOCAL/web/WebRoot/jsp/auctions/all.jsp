<%@ include file="../includes/includes.jsp"%>

<!DOCTYPE html>
<html lang="en">
<%@ include file="../includes/head.jsp"%>

<body>
	<div id="maincontainer" class="clearfix">
		<!-- header -->
		<%@ include file="../includes/header.jsp"%>
		
		<component:component component="3">
		<!-- main content -->
		<div id="contentwrapper">
			<div class="main_content">

				<div class="row-fluid">
					<div class="span12">
						<h3 class="heading">List Auctions</h3>
						<table class="table table-bordered table-striped table_vam"
							id="dt_gal">
							<thead>
								<tr>
									<th class="table_checkbox"><input type="checkbox"
										name="select_rows" class="select_rows" data-tableid="dt_gal" /></th>

									<th>Id</th>
									<th>product Code</th>
									<th>Description</th>
									<th>Isopen</th>
									<th>DateCreated</th> 
									<th>Enddate</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="auction" items="${AUCTIONS}">
									<tr>
										<td><input type="checkbox" name="row_sel" class="row_sel" /></td>

                                        <td>${auction.id}</td>
										<td>${auction.productcode}</td>
										<td>${auction.description}</td>
										<td>${auction.isopen}</td>
										<td>${auction.dateCreated}</td>
										<td>${auction.endDate}</td> 		
								

										<td><a
											href="${PREFIX}/auction?edit=true&id=${auction.id}"
											class="sepV_a" title="Edit" "><i
												class="icon-pencil"></i> Edit </a>
										 
										<!-- <a href="#"
											title="Delete"><i class="icon-trash"></i></a> -->
											</td> 
									</tr>
								</c:forEach>

							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
		 </component:component>

		<%@ include file="../includes/sidebar.jsp"%>
		
		<%@ include file="../includes/scripts.jsp"%>

	</div>
</body>
</html>