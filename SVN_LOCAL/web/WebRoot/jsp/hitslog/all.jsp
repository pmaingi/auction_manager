<%@ include file="../includes/includes.jsp"%>

<!DOCTYPE html>
<html lang="en">
<%@ include file="../includes/head.jsp"%>

<body>
	<div id="maincontainer" class="clearfix">
		<!-- header -->
	
	<%@ include file="../includes/header.jsp"%>

        <component:component component="5">
		<!-- main content -->
		<div id="contentwrapper">
			<div class="main_content">

				<div class="row-fluid">
					<div class="span12">
						<h3 class="heading">List Hits to the application </h3>
						<table class="table table-bordered table-striped table_vam"
							id="dt_gal">
							<thead>
								<tr>
									<th class="table_checkbox"><input type="checkbox"
										name="select_rows" class="select_rows" data-tableid="dt_gal" /></th>

									<th>Id</th>
									<th>msisdn</th>
									<th>keyword</th>
									<th>DateCreated</th> 
									<th>None</th> 
									<th>None</th> 
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="hits" items="${HITSLOG}">
									<tr>
										<td><input type="checkbox" name="row_sel" class="row_sel" /></td>

                                        <td>${hits.id}</td>
										<td>${hits.msisdn}</td>
										<td>${hits.keyword}</td>
										<td>${hits.dateCreated}</td>
										<td>none</td>
										<td>none</td> 
																	

										<td><a
											href="#"
											class="sepV_a" title="hits" ><i
												class="icon-pencil"></i> Hit received </a>
										
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