package com.gdu.prj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudentDto {
  private int rn;
  private int stu_no;
  private String name;
  private int kor;
  private int eng;
  private int math;
  private double ave;
  private char mark;
  public void calculateAveragAndMark() {
    this.ave = (this.kor + this.eng + this.math) / 3.0;
    if(this.ave >= 90) this.mark = 'A';
    else if(this.ave >= 80) this.mark = 'B';
    else if(this.ave >= 70) this.mark = 'C';
    else if(this.ave >= 60) this.mark = 'D';
    else this.mark = 'F';
  }
  
}
