package com.example.swcoaching.board;

import com.example.swcoaching.board.jpa.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PostService {

    // create
    Long createPost(Post post);

    // read
    Post findById(long id);

    // 전체 read
    List<Post> getPostList(Integer pageNum);

    // 상세 보기
    Post getPost(Long id);

    // delete
    void deletePost(Long id);

    // 검색 기능
    List<Post> searchPost(String keyword);

    // 페이징 처리
    Long getPostCount();

    Integer[] getPageList(Integer curPageNum);

    // 조회수
//    int updateHit(Long id);

}
