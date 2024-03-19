package com.gdu.prj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.gdu.prj.common.ActionForward;
import com.gdu.prj.dao.StudentDao;
import com.gdu.prj.dao.StudentDaoImpl;
import com.gdu.prj.dto.StudentDto;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

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
    student.calculateAveragAndMark();
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
    String beginStr = request.getParameter("begin");
    String endStr = request.getParameter("end");
    
    // String 값을 int로 변환, 파라미터가 없거나 잘못된 경우 기본값 설정
    int begin = beginStr != null ? Integer.parseInt(beginStr) : 0;
    int end = endStr != null ? Integer.parseInt(endStr) : 100; // 예시 기본값
    
    // 파라미터를 맵에 담기
    Map<String, Object> params = new HashMap<>();
    params.put("begin", begin);
    params.put("end", end);
    
    
    
    int studentCount = studentDao.getStudentCount();
    List<StudentDto> studentList = studentDao.selectStudentList();
    List<StudentDto> studentTop3List = studentDao.selectStudentTop3List();
//    List<StudentDto> rangeStudentList = studentDao.selectRangeStudentList(params);
    
    double totalAverage = studentDao.getTotalAverage();
    request.setAttribute("studentTop3List", studentTop3List);
    request.setAttribute("studentCount", studentCount);
    request.setAttribute("studentList", studentList);
    request.setAttribute("totalAverage", totalAverage);
    return new ActionForward("/student/list.jsp", false);

  }
  

  @Override
  public ActionForward getStudentByNo(HttpServletRequest request) {
    Optional<String> opt = Optional.ofNullable(request.getParameter("stu_no"));
    int stu_no = Integer.parseInt(opt.orElse("0"));
    StudentDto student = studentDao.selectStudentByNo(stu_no);
    String view = null;
    if(student != null) {
      view = "/student/detail.jsp";
      request.setAttribute("student", student);
    } else {
      view = "/index.jsp";
    }
    return new ActionForward(view, false);
  }

  @Override
  public ActionForward editStudent(HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ActionForward modifyStudent(HttpServletRequest request) {
      int stu_no = Integer.parseInt(request.getParameter("stu-no"));
     String name = request.getParameter("name");
     int kor = Integer.parseInt(request.getParameter("kor"));
     int eng = Integer.parseInt(request.getParameter("eng"));
     int math = Integer.parseInt(request.getParameter("math"));
     StudentDto student = StudentDto.builder()
                                 .stu_no(stu_no)
                                 .name(name)
                                 .kor(kor)
                                 .eng(eng)
                                 .math(math)
                                .build();
     student.calculateAveragAndMark();
     int updateCount = studentDao.updateStudent(student);
     String view = null;
     if(updateCount == 0) {
       view = request.getContextPath() + "/main.do";
     } else {
       view = request.getContextPath() + "/student/detail.do?stu_no=" + stu_no;
     }
    return new ActionForward(view, true);
  }

  @Override
  public ActionForward removeStudent(HttpServletRequest request) {
    String param = request.getParameter("stu_no");
    int stu_no = 0;
    if(!param.isEmpty()) {
      stu_no = Integer.parseInt(param);
    }
    int deleteCount = studentDao.deleteStudent(stu_no);
    String view = null;
    if(deleteCount == 0) {
      view = request.getContextPath() + "/main.do"; 
    } else {
      view = request.getContextPath() + "/student/list.do";
    }
    return new ActionForward(view, true);
  }

}
