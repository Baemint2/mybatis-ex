<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>

</style>
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

</head>
<body>
<h1>신규학생등록 화면</h1>
<div>
<form id="frm-detail"
      method="POST">
    <div>
      <label for="stu-no">학번</label>
      <input type="text" id="stu-no" name="stu-no" value="${student.stu_no}" readonly>
    </div>
    <div>
      <label for="name">이름</label>
      <input type="text" id="name" name="name" value="${student.name}">
    </div>
    <div>
      <label for="kor">국어</label>
      <input type="text" id="kor" name="kor" value="${student.kor}">
    </div>
    <div>
      <label for="eng">영어</label>
      <input type="text" id="eng" name="eng" value="${student.eng}">
    </div>
    <div>
      <label for="math">수학</label>
      <input type="text" id="math" name="math" value="${student.math}">
    </div>
    <div>
      <label for="ave">평균</label>
      <input type="text" id="ave" name="ave" value="${student.ave}" disabled>
    </div>
    <div>
      <label for="mark">학점</label>
      <input type="text" id="mark" name="mark" value="${student.mark}" disabled>
    </div>
    <hr>
      <div class="button-group">
        <button type="button" id="btn-modify">수정하기</button>
        <button type="button" id="btn-list">목록보기</button>
      </div>
   </form>
 </div>
  <script>
  	const frmDetail = document.getElementById('frm-detail');
  	const btnModify = document.getElementById('btn-modify');
  	const btnList = document.getElementById('btn-list');
  	
  	btnModify.addEventListener('click', (evt) => {
  		$(frmDetail).attr('action', '${contextPath}/student/modify.do');
  		$(frmDetail).submit();
  	})
  	
  	btnList.addEventListener('click', (evt) => {
  		location.href = '${contextPath}/student/list.do';
  	})
  </script>
</body>
</html>