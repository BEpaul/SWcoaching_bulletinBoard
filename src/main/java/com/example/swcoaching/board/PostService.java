package com.example.swcoaching.board;

import com.example.swcoaching.board.jpa.PostEntity;

import java.util.Optional;

public interface PostService {

    // create

    // read

    // update
    Optional<PostEntity> updateBoard(Long id, Post post);


    // delete
}
