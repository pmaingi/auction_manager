<%@ include file="../includes/includes.jsp"%>

<!DOCTYPE html>
<html lang="en">
<%@ include file="../includes/head.jsp"%>

<body>
    
	<div id="maincontainer" class="clearfix">
		<!-- header -->
		<%@ include file="../includes/header.jsp"%>
        
       <component:component component="2">
		<!-- main content -->
		<div id="contentwrapper">
			<div class="main_content">

				<!--  <div>
					<table>
						<tr>
							<td class="sepV_a"><a href="${PREFIX}/subscription?create=true"
								title="Add" ><i class="icon-eye-open"></i>
									Add Subscription</a></td>
						</tr>
					</table>
				</div> 
				-->
				<div class="row-fluid">
					<div class="span12">
						<h3 class="heading">Manual Subscription</h3>
						<table class="table table-bordered table-striped table_vam"
							id="dt_gal">
							<thead>
								<tr>
									<th class="table_checkbox"><input type="checkbox"
										name="select_rows" class="select_rows" data-tableid="dt_gal" /></th>

									<th>ID</th>
									<th>Msisdn</th>
									<th>Product Code</th>
									<th>Amount</th>
									<th>Reg status</th> 
									<th>DateCreated</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="subscription" items="${SUBSCRIPTIONS}">
									<tr>
										<td><input type="checkbox" name="row_sel" class="row_sel" /></td>

                                        <td>${subscription.id}</td>
										<td>${subscription.msisdn}</td>
										<td>${subscription.productcode}</td>
										<td>${subscription.amount}</td>
										<td>${subscription.regStatus}</td> 					
										<td>${subscription.dateCreated}</td>

										<td><a
											href="${PREFIX}/subscription?edit=true&id=${subscription.id}"
											class="sepV_a" title="Edit" ><i
												class="icon-pencil"></i> Edit </a>
										 
										<!-- <a href="#"
											title="Delete"><i class="icon-trash"></i></a></td>  -->
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