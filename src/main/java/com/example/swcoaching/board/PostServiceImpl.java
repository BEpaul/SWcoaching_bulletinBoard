package com.example.swcoaching.board;

import com.example.swcoaching.board.jpa.PostEntity;
import com.example.swcoaching.board.jpa.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class PostServiceImpl implements PostService{

    @Autowired
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // create

    // read

    // update


    @Transactional
    @Override
    public Optional<PostEntity> updateBoard(Long id, Post post) {

        Optional<PostEntity> entity = this.postRepository.findById(id);
        entity.ifPresent(t -> {
            if (post.getContents() != null) {
                // t.setContent(post.getContent());

            } else if (post.getTitle() != null) {
                // t.setTitle(post.getTitle());
            }
            this.postRepository.save(t);
        });

        return entity;
    }



    // delete
}
