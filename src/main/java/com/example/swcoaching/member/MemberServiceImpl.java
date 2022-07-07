package com.example.swcoaching.member;

import com.example.swcoaching.member.jpa.MemberEntity;
import com.example.swcoaching.member.jpa.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;


  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  // 유저이름 검색
  @Override
  public Member findByUsername(String username) {
    MemberEntity memberEntity = memberRepository.findByUsername(username);
    return Member.of(memberEntity);
  }



  /**
   * 회원 가입
   */

  @Override
  public void save(MemberEntity memberEntity) {
    memberRepository.save(memberEntity);
  }

}