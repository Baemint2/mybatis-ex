<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.student-form {
  display: flex;
  flex-direction: column;
  margin-bottom: 5px;
  
  
}

.form-group {
  margin-bottom: 5px;
}

.button-group {
  display: flex;
  flex-direction: rows;
}

</style>
<title>Insert title here</title>
</head>
<body>
<h1>신규학생등록 화면</h1>
<form class="student-form"
      id="student-form"
      method="POST"
      action="${contextPath}/student/register.do">
  <div class="form-group">
    <label for="name">이름</label>
    <input type="text" id="name" name="name" placeholder="이름">
  </div>
  <div class="form-group">
    <label for="kor">국어</label>
    <input type="text" id="kor" name="kor" placeholder="국어">
  </div>
  <div class="form-group">
    <label for="eng">영어</label>
    <input type="text" id="eng" name="eng" placeholder="영어">
  </div>
  <div class="form-group">
    <label for="math">수학</label>
    <input type="text" id="math" name="math" placeholder="수학">
  </div>
</form>
<hr>
  <div class="button-group">
    <button form="student-form" class="btn-student" type="submit">작성완료</button>
    <button form="student-form" class="btn-student" type="reset">다시작성</button>
    <button type="button" onclick="location.href='${contextPath}/student/list.do'">목록보기</button>
  </div>
</body>
</html>