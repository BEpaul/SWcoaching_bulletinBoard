package com.example.swcoaching.board;

import com.example.swcoaching.board.jpa.BoardEntity;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@ToString
@Getter
public class Board {
  private final Long id; // pk

  private final String title; // 제목

  private final String remark; // 게시판 설명

  private final List<Post> posts; // 게시글 리스트



  @Builder
  public Board(Long id, String title, String remark, List<Post> posts) {
    this.id = id;
    this.title = title;
    this.remark = remark;
    this.posts = posts;

  }

  public static Board of(BoardEntity boardEntity) {
    List<Post> posts = boardEntity.getPosts()
            .stream().map(Post::of).collect(Collectors.toList());
    return new Board(boardEntity.getId(),
            boardEntity.getTitle(),
            boardEntity.getRemark(),
            posts);
  }



}