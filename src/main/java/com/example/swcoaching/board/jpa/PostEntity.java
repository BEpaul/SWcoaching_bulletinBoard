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
public class PostEntity extends TimeEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 1000)
  private String title;

  @Column(columnDefinition = "text")
  private String contents;

  @Column(length = 100)
  private String writer;

  @Column(length = 10)
  private Long hit;

  @ManyToOne
  @JoinColumn(name = "boardId")
  private BoardEntity board;

  @Builder
  public PostEntity(Long id, String title, String contents, String writer, Long hit) {
    this.id = id;
    this.title = title;
    this.contents = contents;
    this.writer = writer;
    this.hit = hit;
  }

  public void updateHit(Long hit) {
    this.hit = hit;
  }

}