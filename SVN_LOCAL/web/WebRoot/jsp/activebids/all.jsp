<%@ include file="../includes/includes.jsp"%>

<!DOCTYPE html>
<html lang="en">
<%@ include file="../includes/head.jsp"%>

<body>
	<div id="maincontainer" class="clearfix">
		<!-- header -->
	<%@ include file="../includes/header.jsp"%>
        
        <component:component component="4">
		<!-- main content -->
		<div id="contentwrapper">
			<div class="main_content">

				<div class="row-fluid">
					<div class="span12">
						<h3 class="heading">Active Bidders </h3>
						<table class="table table-bordered table-striped table_vam"
							id="dt_gal">
							<thead>
								<tr>
									<th class="table_checkbox"><input type="checkbox"
										name="select_rows" class="select_rows" data-tableid="dt_gal" /></th>

									<th>Id</th>
									<th>product Code</th>
									<th>product name</th>
									<th>msisdn</th>
									<th>amount</th> 
									<!-- <th>AuctionClosed</th> -->	
									<th>DateCreated</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="activebids" items="${ACTIVEBIDDERS}">
									<tr>
										<td><input type="checkbox" name="row_sel" class="row_sel" /></td>

                                        <td>${activebids.id}</td>
										<td>${activebids.productcode}</td>
										<td>${activebids.productname}</td>
										<td>${activebids.msisdn}</td>
										<td>${activebids.amount}</td>
										<!--<td>${activebids.auctionClosed} </td> 	-->	
										<td>${activebids.dateCreated}</td> 							

										<td><a
											href="#"
											class="sepV_a" title="Bid Received" ><i
												class="icon-pencil"></i> Bid Received </a>
										 
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