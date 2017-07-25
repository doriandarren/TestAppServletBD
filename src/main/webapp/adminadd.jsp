<%@ include file="templates/header.jsp" %>  

<title>Add Admin</title>

<%@ include file="templates/menu.jsp" %>


<div class="container">
	<div class="row">
		<div class="col-md-12 text-center">
			<h1>Add Admin</h1>
		</div>
		<form action="/servletadmin" method="post" class="form-horizontal">
			<div class="form-group">
                <label for="name_admin" class="col-md-4 control-label">Name: </label>
                <div class="col-md-6">
                    <input type="text" id="name_admin"class="form-control" name="name_admin" value="" autofocus required />
                </div>
            </div>
			
			<div class="form-group">
                <div class="col-md-6 col-md-offset-4">
					<input type="submit" class="btn btn-primary" role="button" value="Submit" />
				</div>
			</div>
		</form>
	</div>
</div>


<%@ include file="templates/footer.jsp" %>  