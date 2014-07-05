<%@ include file="../includes/includes.jsp"%>

<!DOCTYPE html>
<html lang="en">
<%@ include file="../includes/head.jsp"%>

<body>
	<div id="maincontainer" class="clearfix">
		<!-- header -->
		<%@ include file="../includes/header.jsp"%>
		
		<component:component component="7">
 		<!-- main content -->
		<div id="contentwrapper">
			<div class="main_content">

				<div class="row-fluid">
					<div class="span12">
						<h3 class="heading">List Roles</h3>
						  <div>
		      	<%-- <table>
				   <tr>
					<td class="sepV_a"><a href="${PREFIX}/roles?create=true"
						id="Add"><i class="icon-eye-open"></i> Add Role</a></td>
				  </tr>
			    </table> --%>
		        </div>
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>Name</th>
									<th>Component</th>
									
							</tr>
							</thead>
							<tbody>
								<c:forEach var="role" items="${ROLES}">
									<tr>
								
                                        <td>${role.role}</td>
										<td>${role.components}</td>
															
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