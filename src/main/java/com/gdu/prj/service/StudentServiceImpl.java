package com.gdu.prj.service;

import javax.servlet.http.HttpServletRequest;

import com.gdu.prj.common.ActionForward;
import com.gdu.prj.dao.StudentDao;
import com.gdu.prj.dao.StudentDaoImpl;
import com.gdu.prj.dto.StudentDto;

public class StudentServiceImpl implements StudentService {
  
  private StudentDao studentDao = StudentDaoImpl.getInstance();

  @Override
  public ActionForward addStudent(HttpServletRequest request) {
    String name = request.getParameter("name");
    int kor = Integer.parseInt(request.getParameter("kor"));
    int eng = Integer.parseInt(request.getParameter("eng"));
    int math = Integer.parseInt(request.getParameter("math"));
   
    StudentDto student = StudentDto.builder()
        .name(name)
        .kor(kor)
        .eng(eng)
        .math(math)
        .build();
    int insertCount = studentDao.insertStudent(student);
    String view = null;
    if(insertCount == 1) {
      view = request.getContextPath() + "/student/list.do";
    } else if(insertCount == 0) {
      view = request.getContextPath() + "/main.do";
    }
    return new ActionForward(view, true);
  }
  

  @Override
  public ActionForward getStudentList(HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ActionForward getStudentByNo(HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ActionForward editStudent(HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ActionForward modifyStudent(HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ActionForward removeStudent(HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

}
