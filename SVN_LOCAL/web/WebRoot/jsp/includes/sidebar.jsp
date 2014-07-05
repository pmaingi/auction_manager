<%@ include file="includes.jsp"%>

<!-- sidebar -->
		<a href="javascript:void(0)" class="sidebar_switch on_switch ttip_r"
			title="Hide Sidebar">Sidebar switch</a>
		<div class="sidebar">

			<div class="antiScroll">
				<div class="antiscroll-inner">
					<div class="antiscroll-content">

						<div class="sidebar_inner">
							 <form action="{PREFIX}/search_page" class="input-append" method="post">
							<!--	<input autocomplete="off" name="query"
									class="search_query input-medium" size="16" type="text"
									placeholder="Search..." /> 
								<button type="submit" class="btn">
									<i class="icon-search"></i>
								</button> -->
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
											    <component:component component="1"><li><a href="${PREFIX}/product">Products</a></li> </component:component>
												<component:component component="2"><li><a href="${PREFIX}/subscription">Subscription</a></li> </component:component>
												<component:component component="3"><li><a href="${PREFIX}/auction">Auction</a></li> </component:component>
												<component:component component="4"><li><a href="${PREFIX}/activebids">Active bidders </a></li> </component:component>
												<component:component component="5"><li><a href="${PREFIX}/hitlogs">All Hits log </a></li> </component:component>
											    <component:component component="6"><li><a href="${PREFIX}/admin">Admin </a></li> </component:component>
												<component:component component="7"><li><a href="${PREFIX}/roles">Role </a></li> </component:component>
												<component:component component="8"><li><a href="${PREFIX}/broadcast">Send Message </a></li> </component:component>
												
												<li><a href="${PREFIX}/logout" >logout</a></li>				
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