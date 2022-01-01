<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<div>
		<a type="button" class="btn btn-primary btn-md" href="/add-student">Add Student</a>
	</div>
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>List of Students</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="10%">Id</th>
						<th width="30%">Name</th>
						<th width="20%">Department</th>
						<th width="20%">Country</th>
						<th width="20%">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${students}" var="student">
						<tr>
							<td>${student.id}</td>
							<td>${student.name}</td>
							<td>${student.department}</td>
							<td>${student.country}</td>
							<td><a type="button" class="btn btn-success"
								href="/update-student?id=${student.id}">Update</a>
							<a type="button" class="btn btn-warning"
								href="/delete-student?id=${student.id}">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>