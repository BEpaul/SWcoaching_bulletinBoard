package com.example.swcoaching.board;

import com.example.swcoaching.board.jpa.PostEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 게시물
 */
@ToString
@Getter
public class Post {
  private final Long id; // PK

  private final String title; // 제목

  private final String contents; // 내용


  public Post(Long id, String title, String contents) {
    this.id = id;
    this.title = title;
    this.contents = contents;
  }

  public static Post of(PostEntity postEntity) {
    return new Post(postEntity.getId(), postEntity.getTitle(), postEntity.getContents());
  }
}