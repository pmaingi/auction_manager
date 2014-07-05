<%@ include file="../includes/includes.jsp"%>
	
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
	<div class="span12">
	<h3 class="heading">Users table</h3>
       <div>
			<table>
				<tr>
					<td class="sepV_a"><a href="${PREFIX}/admin?create=true"
						id="Add"><i class="icon-eye-open"></i> Add User</a></td>
				</tr>
			</table>
		</div>
		<table class="table table-bordered table-striped" id="smpl_tbl">
		     <thead>
				<tr>
					<th>Username</th>
					<th>Role</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="admin" items="${ADMINS}">
					<tr>
						<td>${admin.username}</td>
						<td>${admin.roleName}</td>
						<td>
						  <a href="${PREFIX}/admin?edit=true&id=${admin.id}"
							class="sepV_a" title='Edit'><i class='icon-pencil'></i>Edit </a>

						  <a class="sepV_a" title='Delete' onclick="contents.del('${PREFIX}/admin?id=${admin.id}&delete=yes')" >
						  <i class='icon-trash icon-white'></i>Delete </a>
						</td>
					 </tr>
				</c:forEach>

			</tbody>
		</table>

	</div>

</div>
</div>
</div>
</div>
<%@ include file="../includes/sidebar.jsp"%>
<%@ include file="../includes/scripts.jsp"%>
</body>
</html>