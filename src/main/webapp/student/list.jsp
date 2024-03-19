<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<h1>학생 관리</h1>
<div>
<button type="button" onclick='location.href="${contextPath}/student/write.jsp"' >신규학생등록</button>
<hr>
<form id="rangeForm" action="${contextPath}/student/list.do" method="post">
  <label for="begin">평균</label>
  <input type="text" id="begin" name="begin" placeholder="begin"> ~
  <input type=text id="end" name="end" placeholder="end"/>
  <button type="submit" >조회</button>
  <button type="button">전체 조회</button>
  <hr>
  <c:if test="${not empty studentTop3List}">
  <div>
    <c:forEach items="${studentTop3List}" var="student" varStatus="status">
      <p>${student.rn}위 ${student.name}님 ${student.ave}점</p>
    </c:forEach>
  </div>
  </c:if>
  <hr>
</form>
  <span>전체 학생 ${studentCount}명</span>
      <table border="1">
        <tr>
          <th>학번</th>
          <th>성명</th>
          <th>국어</th>
          <th>영어</th>
          <th>수학</th>
          <th>평균</th>
          <th>학점</th>
          <th>버튼</th>
        </tr>
        <tr>
        <c:choose>
    <c:when test="${empty studentList}">
      <tr>
        <td colspan="8">등록된 학생이 없습니다.</td>
      </tr>
    </c:when>
    <c:otherwise>
      <c:forEach items="${studentList}" var="student">
        <tr>
          <td>${student.stu_no}</td>
          <td>${student.name}</td>
          <td>${student.kor}</td>
          <td>${student.eng}</td>
          <td>${student.math}</td>
          <td>${student.ave}</td>
          <td>${student.mark}</td>
          <td>
            <button type="button" class="btn-detail" data-stu-no="${student.stu_no}">상세</button>
            <button type="button" class="btn-remove">삭제</button>
          </td>
        </tr>
      </c:forEach>
    </c:otherwise>
  </c:choose>
    <tr>
      <td colspan=5>전체 평균</td>
      <td>${totalAverage}</td>
    </tr>
</table>

</div>
<script>
$('.btn-detail').on('click', (evt) => {
		const stuNo = evt.target.dataset.stuNo;
		location.href = '${contextPath}/student/detail.do?stu_no=' + stuNo;
})
$('.btn-remove').on('click', (evt)=>{
	if(confirm('연락처를 삭제할까요?')) {    		
  	const stuNo = $(evt.target).prev().data('stuNo');
  	location.href = '${contextPath}/student/remove.do?stu_no=' + stuNo;  	
	}
	})
</script>
</body>
</html>