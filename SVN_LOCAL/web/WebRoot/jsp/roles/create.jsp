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
                        <div class="span6">
							<h3 class="heading">Add New Role </h3>
						
						<form class="form_validation_ttip" id="add">
							<h4 id="processmsg"></h4> 
							
							<div class="row-fluid">
								<div class="span12">
									<label>Name <span class="f_req">*</span></label>
									<input type="text" name="name" autofocus required />
									
								</div>
							</div>
							<div class="formSep">
							
							<div class="row-fluid">
							<div class="span6">
								<p><span class="label label-inverse">Components Available</span></p>
								<select id="custom-headers" multiple="multiple">
									<c:forEach items="${COMPONENT}" var="component">
										<option id="${component.id}" >${component.name}</option>
									</c:forEach>
								</select>
							</div>
							</div>
							
							
							<div class="row-fluid">
							<div class="span6">
								<p><span class="label label-inverse">Custom headers</span></p>
								<select id="custom-headers" multiple="multiple">
									<option value="fr">France</option>
									<option value="uk">United Kingdom</option>
									<option value="us">United States</option>
									<option value="ch">China</option>
								</select>
							</div>
							</div>
						   			
						   </div>			
						   <input type="hidden" name="action" value="create" /> 
								<div class="form-actions">
									<button class="btn btn-inverse" type="submit">Add Role</button>
									<button class="btn">Cancel</button>
								</div>
							</form>
							
                        </div>
                       
                    </div>
                    
			</div>
		</div>
	<%@ include file="../includes/sidebar.jsp"%>

	<%@ include file="../includes/scripts.jsp"%>

<script type="text/javascript">
$(function() {
    var  sourceCars=$('#notadded option').clone();
    $('#filterDis').change(function() {
        var val = $(this).val();
        $('#notadded').empty();
        sourceCars.filter(function(idx, el) {
            var found=false;
            $('#added option').each(function(){
                    if ($(this).val()===$(el).text())
                          found=true;
            });

            if(found)
                return false;

            return val === 'ALL' || $(el).text().indexOf('[' + val + ']') >= 0;
        }).appendTo('#notadded');
    });

    $('#notadded').dblclick(function() {
        $('#notadded option:selected').appendTo('#added');
      });

    $('#added').dblclick(function() {
        var targetList=$('#added option:selected');
        targetList.appendTo('#notadded');
        var foption = $('#notadded option:first');
        var soptions = $.makeArray($('#notadded option:not(:first)')).sort(function(a, b) {
         return a.text == b.text ? 0 : a.text < b.text ? -1 : 1
        });
        $('#notadded').html(soptions).prepend(foption);
        foption.attr("selected", true).siblings("option").removeAttr("selected");
    });
    
    $('#role_save').click(function(){
    	var jsonObj = [];
    	$("#added option").each(function(){
    		jsonObj.push($(this).attr('id'));
    	})
    	if(jsonObj.length > 0){
    		$('#processmsg').html("Processing...");
    		$('#processmsg').removeClass("alert_success").removeClass("alert_error").addClass("alert_warning");
    		$.post("${PREFIX}/roles",{
    			name : $('#name').val(),
    			componenets : JSON.stringify(jsonObj),
    			action : 'save'
    		}, function(d) {
    			if(!d.error){
					$('#processmsg').removeClass("alert_warning").addClass("alert_success");
					$('#processmsg').html(d.message);
					loader.load('${PREFIX}/roles', 'View Roles');
				}else{
					$('#processmsg').removeClass("alert_warning").addClass("alert_error");
					$('#processmsg').html(d.error);
				}
    		})
    	}
    });
});
</script>

</div>
</body>
</html>