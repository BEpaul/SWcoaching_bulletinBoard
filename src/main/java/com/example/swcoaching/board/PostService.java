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
    PostEntity createPost(PostEntity postEntity);

    // read
    Post findById(long id);

    // 전체 read
    List<Post> findPostAll();


    // update
    void updateBoard(Long id, Post post);

    // delete
    void deletePost(Long id);

    // 페이징 처리
    Page<Post> pageList(Pageable pageable);
}
