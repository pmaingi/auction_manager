<%@ include file="includes.jsp"%>

<script type="text/javascript">
	var prefix = "${PREFIX}";
</script>
           <script src="${PREFIX}/js/jquery.min.js"></script>
            <script src="${PREFIX}/js/jquery-migrate.min.js"></script>
            <!-- smart resize event -->
            <script src="${PREFIX}/js/jquery.debouncedresize.min.js"></script>
            <!-- hidden elements width/height -->
            <script src="${PREFIX}/js/jquery.actual.min.js"></script>
            <!-- js cookie plugin -->
            <script src="${PREFIX}/js/jquery_cookie.min.js"></script>
            <!-- main bootstrap js -->
            <script src="${PREFIX}/js/bootstrap.min.js"></script>
            <!-- bootstrap plugins -->
            <script src="${PREFIX}/js/bootstrap.plugins.min.js"></script>
            <!-- tooltips -->
            <script src="${PREFIX}/js/jquery.qtip.min.js"></script>
            <!-- jBreadcrumbs -->
            <script src="${PREFIX}/js/jquery.jBreadCrumb.1.1.min.js"></script>
            <!-- sticky messages -->
            <script src="${PREFIX}/js/sticky.min.js"></script>
            <!-- fix for ios orientation change -->
            <script src="${PREFIX}/js/ios-orientationchange-fix.js"></script>
            <!-- scrollbar -->
            <script src="${PREFIX}//js/antiscroll.js"></script>
            <script src="${PREFIX}/js/jquery-mousewheel.js"></script>
            <!-- mobile nav -->
            <script src="${PREFIX}/js/selectNav.js"></script>
            <!-- common functions -->
            <script src="${PREFIX}/js/gebo_common.js"></script>
    
            <!-- colorbox -->
            <script src="${PREFIX}/js/jquery.colorbox.min.js"></script>
            <!-- datatable -->
            <script src="${PREFIX}/js/jquery.dataTables.min.js"></script>
            <!-- additional sorting for datatables -->
            <script src="${PREFIX}/js/jquery.dataTables.sorting.js"></script>
            <!-- datatables bootstrap integration -->
            <script src="${PREFIX}/js/jquery.dataTables.bootstrap.min.js"></script>
            <!-- tables functions -->
            <script src="${PREFIX}/js/gebo_tables.js"></script>
            
             <!-- multiselect -->
			<script src="${PREFIX}/js/jquery.multi-select.js"></script>
           
            <script src="${PREFIX}/js/bootstrap-datetimepicker.min.js"></script> 
            <script src="${PREFIX}/js/bootstrap.min.js"></script> 
            <script src="${PREFIX}/js/moment.min.js"></script> 
            <script src="${PREFIX}/js/datepicker.js"></script> 
            
           
		            
            <!-- <script src="${PREFIX}/js/datepicker1.js"></script>    -->
            
             <!--  
            <script src="${PREFIX}/js/jquery-ui/jquery-ui-sliderAccess.js"></script>
            <script src="${PREFIX}/js/jquery-ui/jquery-ui-sliderAccess.js"></script>
            <script src="${PREFIX}/js/jquery-ui/jquery-ui-timepicker-addon.js"></script>
            <script src="${PREFIX}/js/jquery-ui/jquery.ui.core.js"></script>
            <script src="${PREFIX}/js/jquery-ui/jquery.ui.datepicker.js"></script>
            <script src="${PREFIX}/js/jquery-ui/jquery.ui.slider.js"></script>
            <script src="${PREFIX}/js/jquery-ui/jquery.ui.widget.js"></script>
            -->
                        
            
    
            <script>
                $(document).ready(function() {
                    //* show all elements & remove preloader
                    setTimeout('$("html").removeClass("js")',1000);
                });
            </script>

<script type="text/javascript">

$(document).ready(function() {
	
				$("#editpage").submit(
						function() {
							$('#processmsg').html("Processing...");
							$('#processmsg').removeClass("alert_success")
									.removeClass("alert_error").addClass(
											"alert_warning");
							var data = $(this).serialize();
							$.post("${PREFIX}/product", data, function(d) {
								if (!d.error) {
									$('#processmsg').removeClass(
											"alert_warning").addClass(
											"alert_success");
									$('#processmsg').html(d.message);
									//commented redirects to home page
								    window.location.href = d.url;
									//the perfect one
									//window.location.href = prefix + "/product";

								} else {
									$('#processmsg').removeClass(
											"alert_warning").addClass(
											"alert_error");
									$('#processmsg').html(d.error);
									window.location.href = prefix + "/home";
								}
							});
							return false;
						});
				
				$("#addpage").submit(
						function() {
							$('#processmsg').html("Processing...");
							$('#processmsg').removeClass("alert_success")
									.removeClass("alert_error").addClass(
											"alert_warning");
							var data = $(this).serialize();
							$.post("${PREFIX}/product", data, function(d) {
								if (!d.error) {
									$('#processmsg').removeClass(
											"alert_warning").addClass(
											"alert_success");
									$('#processmsg').html(d.message);
									//commented redirects to home page
								    window.location.href = d.url;
									//the perfect one
									//window.location.href = prefix + "/product";

								} else {
									$('#processmsg').removeClass(
											"alert_warning").addClass(
											"alert_error");
									$('#processmsg').html(d.error);
									window.location.href = prefix + "/product";
								}
							});
							return false;
						});
				
				$("#editpages").submit(
						function() {
							$('#processmsg').html("Processing...");
							$('#processmsg').removeClass("alert_success")
									.removeClass("alert_error").addClass(
											"alert_warning");
							var data = $(this).serialize();
							$.post("${PREFIX}/subscription", data, function(d) {
								if (!d.error) {
									$('#processmsg').removeClass(
											"alert_warning").addClass(
											"alert_success");
									$('#processmsg').html(d.message);
									//commented redirects to home page
								    window.location.href = d.url;
									//the perfect one
									//window.location.href = prefix + "/product";

								} else {
									$('#processmsg').removeClass(
											"alert_warning").addClass(
											"alert_error");
									$('#processmsg').html(d.error);
									window.location.href = prefix + "/subscription";
								}
							});
							return false;
						});
				
				$("#editauction").submit(
						function() {
							$('#processmsg').html("Processing...");
							$('#processmsg').removeClass("alert_success")
									.removeClass("alert_error").addClass(
											"alert_warning");
							var data = $(this).serialize();
							$.post("${PREFIX}/auction", data, function(d) {
								if (!d.error) {
									$('#processmsg').removeClass(
											"alert_warning").addClass(
											"alert_success");
									$('#processmsg').html(d.message);
									//commented redirects to home page
								    window.location.href = d.url;
									//the perfect one
									//window.location.href = prefix + "/auction";

								} else {
									$('#processmsg').removeClass(
											"alert_warning").addClass(
											"alert_error");
									$('#processmsg').html(d.error);
									window.location.href = prefix + "/auction";
								}
							});
							return false;
						});
		
				$("#createpage").submit(
						function() {
							$('#processmsg').html("Processing...");
							$('#processmsg').removeClass("alert_success")
									.removeClass("alert_error").addClass(
											"alert_warning");
							var data = $(this).serialize();
							$.post("${PREFIX}/product", data, function(d) {
								if (!d.error) {
									$('#processmsg').removeClass(
											"alert_warning").addClass(
											"alert_success");
									$('#processmsg').html(d.message);
									window.location.href = d.url;

								} else {
									$('#processmsg').removeClass(
											"alert_warning").addClass(
											"alert_error");
									$('#processmsg').html(d.error);
									window.location.href = prefix + "/home";

								}
							});
							return false;
						});
				
				$("#createbroadcast").submit(
						function() {
							$('#processmsg').html("Processing...");
							$('#processmsg').removeClass("alert_success")
									.removeClass("alert_error").addClass(
											"alert_warning");
							var data = $(this).serialize();
							$.post("${PREFIX}/broadcast", data, function(d) {
								if (!d.error) {
									$('#processmsg').removeClass(
											"alert_warning").addClass(
											"alert_success");
									$('#processmsg').html(d.message);
									window.location.href = d.url;

								} else {
									$('#processmsg').removeClass(
											"alert_warning").addClass(
											"alert_error");
									$('#processmsg').html(d.error);
									window.location.href = prefix + "/home";

								}
							});
							return false;
						});
});

	var contents = {

		actionhtml : function(id, productname, code, description, dateCreated) {
			var html = "";
			
			html += "<a class='sepV_a' id='edit'  href='" + prefix + "/product?edit=true&id="+id+"'>";
			html += "<i class='icon-pencil'> </i>Edit </a>";
			
			html += "<a class='sepV_a' id='edit'  href='" + prefix + "/subscription?edit=true&id="+id+"'>";
			html += "<i class='icon-eye-open'> </i>Edit </a>";
			
			html += "<a class='sepV_a' id='datetimepicker'  href='" + prefix + "/auction?edit=true&id="+id+"'>";
			html += "<i class='icon-eye-open'> </i>Edit </a>";
			
			html += "<a class='sepV_a' id='add'  href='" + prefix + "/product?add=true&id="+id+"'>";
			html += "<i class='icon-eye-open'> </i>Add Auction </a>";
			
			html += "<a class='sepV_a' id='delete_"+id+"'  href='javascript:;' onclick='contents.del("+id+")'>";
			html += "<i class='icon-trash icon-white'></i>Delete </a>";
			return html;
		},
	
		/* del : function(item, callback){
			var r=confirm("Are you sure you want to delete " + item + "?")
			if (r==true){
				if(typeof callback == 'function'){
					callback();
				}
			}else{
				return false;
			}
		} */
		 del : function(itemid){
				var r=confirm("Are you sure you want to delete? ");
				if (r==true){
					$.get(prefix + "/admin?delete=true&id="+itemid,function(d) {
						//window.location.reload();
						$('#delete_'+itemid).parent().parent().remove();
					});
				}else{
					return false;
				}
			} 


}


</script>

