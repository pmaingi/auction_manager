<%@ include file="includes.jsp"%>

<header>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="brand" href="${PREFIX}/home"><i class="icon-home icon-white"></i> BID PRO</a>
				<ul class="nav user_menu pull-right">
					<li class="divider-vertical hidden-phone hidden-tablet"></li>
					
					 <li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">
						<img src="img/user_avatar.png" alt="" class="user_avatar" />Welcome, ${AUTHSESSION.username} <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${PREFIX}/user_profile.jsp">My Profile</a></li>
							<li class="divider"></li>
							<li><a href="${PREFIX}/logout">Log Out</a></li>
						</ul>
					</li> 

					<li class="divider-vertical hidden-phone hidden-tablet"></li>
				</ul>

			</div>
		</div>
	</div>

</header>