package com.example.swcoaching.board.jpa;

import com.example.swcoaching.board.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 게시물
 */
@Getter
@NoArgsConstructor
@Table(name = "post")
@Entity
public class PostEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 1000)
  private String title;

  @Column(columnDefinition = "text")
  private String contents;

  @ManyToOne
  @JoinColumn(name = "boardId")
  private BoardEntity board;

  @Builder
  public PostEntity(Long id, String title, String contents) {
    this.id = id;
    this.title = title;
    this.contents = contents;
  }

  // 게시글 업데이트를 수행하는 로직
  public void updatePost(Post post) {
    this.title = post.getTitle();
    this.contents = post.getContents();
  }
}