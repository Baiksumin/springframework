<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %> <%-- header.jsp에 작성한 내용을 여기에 넣겠다! 즉 url 경로가 아님! --%>
<div class="card m-2">
	<div class="card-header">
		JSP 템플릿 엔진 이해(~.jsp -> ~.java -> ~.class)
	</div>
	<div class="card-body">
		<%-- 오늘 날짜 : <%=request.getAttribute("strDate")%> <br/> --%>
		오늘 날짜 : ${strDate} <%-- ${}는 Expression Language(EL) --%>
	</div>
</div>

<div class="card m-2">
	<div class="card-header">
		객체 저장 범위
	</div>
	<div class="card-body">
		<%-- 얘는 잘 안사용함...ㅎ --%>
		<p>request 범위 객체 값 : <%=request.getAttribute("requestScopeValue") %></p>
		<p>session 범위 객체 값 : <%=session.getAttribute("sessionScopeValue")%></p>
		<p>application 범위 객체 값 : <%=application.getAttribute("applicationScopeValue")%></p>
		
		<hr/>
		<%-- 찾는 순서 : request 범위 -> session 범위 -> application 범위 --%>
		<p>
			request 범위 객체 값 : ${requestScopeValue} <br/>
			member's name: ${member.name} <br/>
			member's age: ${member.age} <br/>
			member's job: ${member.job} <br/>
			member's city: ${member.city.name} <br/>
		</p>
		<p>
			session 범위 객체 값 : ${sessionScopeValue} <br/>
			member's name: ${member2.name} <br/>
			member's age: ${member2.age} <br/>
			member's job: ${member2.job} <br/>
			member's city: ${member2.city.name} <br/>
		</p>
		<p>
			application 범위 객체 값 : ${applicationScopeValue} <br/>
			방문 카운팅: ${counter}
		</p>
		
		<hr/>
		<a href="requestScopeSave" class="btn btn-info btn-sm mr-2">request 범위에 객체 저장</a>
		<a href="sessionScopeSave" class="btn btn-info btn-sm mr-2">session 범위에 객체 저장</a>
		<a href="applicationScopeSave" class="btn btn-info btn-sm mr-2">application 범위에 객체 저장</a>
	</div>
</div>

<div class="card m-2">
	<div class="card-header">
		JSTL(Java Standard Tag Library) : 조건 처리, 반복 처리
	</div>
	<div class="card-body">
		<a href="useJstl1" class="btn btn-info btn-sm mr-2">JSTL 사용하기</a>
		<a href="useJstl2" class="btn btn-info btn-sm mr-2">JSTL 사용하기2</a>
	
	</div>
</div>

<div class="card m-2">
	<div class="card-header">
		ModelAndView로 객체 전달
	</div>
	<div class="card-body">
		<a href="modelAndViewReturn" class="btn btn-info btn-sm mr-2">ModelAndView 리턴해서 객체 전달</a>
	
	</div>
</div>

<div class="card m-2">
	<div class="card-header">
		Model 매개변수로 객체 전달
	</div>
	<div class="card-body">
		<a href="modelArgument" class="btn btn-info btn-sm mr-2">Model 매개변수로 객체 리턴</a>
	
	</div>
</div>

<div class="card m-2">
	<div class="card-header">
		@ModelAttribute로 객체 전달
	</div>
	<div class="card-body">
		<a href="modelAttribute?kind=suit&sex=woman" class="btn btn-info btn-sm mr-2">@ModelAttribute로 객체 리턴</a>
	</div>
</div>

<div class="card m-2">
	<div class="card-header">
		Command(DTO) 객체로 전달
	</div>
	<div class="card-body">
		<a href="commandObject?kind=suit&sex=woman" class="btn btn-info btn-sm mr-2">Command(DTO) 객체로 리턴</a>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>