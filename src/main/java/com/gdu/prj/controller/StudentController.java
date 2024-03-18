package com.gdu.prj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.prj.common.ActionForward;
import com.gdu.prj.service.StudentService;
import com.gdu.prj.service.StudentServiceImpl;

public class StudentController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private StudentService studentService = new StudentServiceImpl();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    String contextPath = request.getContextPath();
    String urlMapping = requestURI.substring(requestURI.indexOf(contextPath) + contextPath.length());

    ActionForward actionForward = null;

    switch (urlMapping) {
    case "/student/list.do":
      actionForward = studentService.getStudentList(request);
      break;
    case "/student/write.do":
      actionForward = new ActionForward("/board/write.jsp", false);
      break;
    case "/student/register.do":
      actionForward = studentService.addStudent(request);
      break;
    case "/main.do":
      actionForward = new ActionForward("/index.jsp", false);
      break;
    case "/student/detail.do":
      actionForward = studentService.getStudentByNo(request);
      break;
    case "/student/edit.do":
      actionForward = studentService.editStudent(request);
      break;
    case "/student/modify.do":
      actionForward = studentService.modifyStudent(request);
      break;
    case "/student/remove.do":
      actionForward = studentService.removeStudent(request);
      break;
    }

    // 어디로 어떻게 이동하는지 결정
    if (actionForward != null) {
      if (actionForward.isRedirect()) {
        response.sendRedirect(actionForward.getView());
      } else {
        request.getRequestDispatcher(actionForward.getView()).forward(request, response);
      }
    }

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    super.doPost(request, response);
  }

}
