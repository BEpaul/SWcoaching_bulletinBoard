package com.example.swcoaching.controller;

import com.example.swcoaching.board.Post;
import com.example.swcoaching.board.PostNotFoundException;
import com.example.swcoaching.board.PostService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
;import java.util.Map;

@AllArgsConstructor // lombok : 모든 필드 값을 파라미터로 받는 생성자 생성
@Controller
@RequestMapping
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PostService postService;

    // 리스트 화면
    @GetMapping("/post")
    public String postList(){
        return "post/list";
//        logger.info("list" );
    }

    // 게시글 생성화면
    @GetMapping("/post/create")
    public String savePost(){
        return "post/create";
    }

    // 게시글 상세보기
    @GetMapping("/post/detail")
    public String detailPost() {
        return "post/detail";
    }

    // 게시글 수정화면
    @GetMapping("/post/update")
    public String revisePost() {
        return "post/update";
    }

}
