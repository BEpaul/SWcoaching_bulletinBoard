package com.example.swcoaching.board;

import com.example.swcoaching.board.jpa.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 게시물
 */
@ToString
@Getter
public class Post {
  private final Long id; // PK

  private final String title; // 제목

  private final String contents; // 내용

  private final String writer;

  private final Long hit;

  private final LocalDateTime createdDate;
  private final LocalDateTime modifiedDate;


  @Builder
  public Post(Long id, String title, String contents, String writer, Long hit, LocalDateTime createdDate, LocalDateTime modifiedDate) {
    this.id = id;
    this.title = title;
    this.contents = contents;
    this.writer = writer;
    this.hit = hit;
    this.createdDate = createdDate;
    this.modifiedDate = modifiedDate;
  }

  public PostEntity toEntity() {
    PostEntity postEntity = PostEntity.builder()
            .id(id)
            .title(title)
            .contents(contents)
            .writer(writer)
            .hit(hit)
            .build();
    return postEntity;
  }


  public static Post of(PostEntity postEntity) {
    return new Post(postEntity.getId(), postEntity.getTitle(), postEntity.getContents(), postEntity.getWriter(), postEntity.getHit(),postEntity.getCreatedDate(), postEntity.getModifiedDate());
  }
}