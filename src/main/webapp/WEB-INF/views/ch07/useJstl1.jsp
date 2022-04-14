<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %> <%-- header.jsp에 작성한 내용을 여기에 넣겠다! 즉 url 경로가 아님! --%>

<div class="card m-2">
	<div class="card-header">
		배열 반복 처리
	</div>
	<div class="card-body">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>No</th>
					<th>Language</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${langs}" var="lang" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${lang}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>