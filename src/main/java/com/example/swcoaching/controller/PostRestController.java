package com.example.swcoaching.controller;

import com.example.swcoaching.board.Post;
import com.example.swcoaching.board.PostNotFoundException;
import com.example.swcoaching.board.PostService;
import com.example.swcoaching.board.jpa.PostEntity;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping
public class PostRestController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PostService postService;

    // 게시판 찾기
    @GetMapping("/post/{postId}")
    public Post getPost(@PathVariable long postId){
        Post post = postService.findById(postId);
        logger.info("Post: {}", post);
        return post;
    }

    // 게시판 삭제
    @GetMapping("/deletePost/{postId}")
    public void deletePost(@PathVariable long postId) {
        postService.deletePost(postId);
    }


    // 게시판 저장
    @PostMapping("/createPost/{postId}")
    public String createPost(PostEntity postEntity) {
        postService.createPost(postEntity);
        return "";
    }

    //

    // 4회차 미팅
    // 예외처리
    @ExceptionHandler(PostNotFoundException.class)
    public Map<String, String> exception(PostNotFoundException e) {
        return Map.of("message", "특정 코드를 탐색하는데 오류가 발생했습니다.", "details", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Map<String, String> exception(Exception e) {
        return Map.of("message", "알 수 없는 이유로 에러가 발생했습니다.", "details", e.getMessage());
    }
}
