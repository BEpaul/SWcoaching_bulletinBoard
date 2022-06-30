package com.example.swcoaching.member.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "member")
@Entity // JPA가 관리하는 것
public class MemberEntity {
  @Id // 기본 키 매핑
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략 / IDENTITY : 기본 키 생성을 데이터베이스에 위임
  private Long id;
  private String username;
  private String password;
  private String name;
  private String remark;

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getName() {
    return name;
  }

  public String getRemark() {
    return remark;
  }
}