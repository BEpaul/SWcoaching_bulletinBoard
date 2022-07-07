package com.example.swcoaching.board;

public class PostNotFoundException extends RuntimeException{
  public PostNotFoundException(long id) {
    super(String.format("(%s)의 게시물을 찾을 수 없습니다.", id));
  }
}