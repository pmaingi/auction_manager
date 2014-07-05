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
						<h3 class="heading">Products</h3>
						<div>
							<table>
								<tr>
									<td class="sepV_a"><a href="${PREFIX}/product?create=true"
										id="Add"><i class="icon-eye-open"></i> Add Product</a></td>
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
									<th>Start Price</th> 
									<th>Reg Fee</th> 								
									<th>DateCreated</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="product" items="${PRODUCTS}">
									<tr>
										<td><input type="checkbox" name="row_sel" class="row_sel" /></td>

										<td>${product.productname}</td>
										<td>${product.code}</td>
										<td>${product.description}</td>
										<td>${product.startPrice}</td>
										<td>${product.registrationFee}</td>									
									  	<td>${product.dateCreated}</td>
										
										<td><a  
											href="${PREFIX}/product?edit=true&id=${product.id}"
											class="sepV_a" title='Edit'><i class='icon-pencil'></i>
												Edit </a> 
											 <a  
											href="${PREFIX}/product?add=true&id=${product.id}"
											class="sepV_a" title='Add'><i class='icon-pencil'></i>
										     Add Auction </a> 
											
											<!--  <a href="javascript:void(0)"  id="add_auction"><i class="icon-eye-open"></i> Add to Auction</a> -->
											<!-- ${PREFIX}/product?add=true&id=${product.id} -->
											<!--  <a href="#" title='Delete'><i class='icon-trash'></i></a></td> -->
									</tr>
								</c:forEach>

							</tbody>
						</table>

					</div>
				</div>
  
			</div>
				
		</div>

        <%@ include file="../includes/sidebar.jsp"%>

		<%@ include file="../includes/scripts.jsp"%>

	</div>
</body>
</html>