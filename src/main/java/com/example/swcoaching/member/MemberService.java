package com.example.swcoaching.member;

import com.example.swcoaching.member.jpa.MemberEntity;

import java.util.Optional;

public interface MemberService {
  Member findByUsername(String username);

  // 회원가입
  public void save(MemberEntity memberEntity);

}