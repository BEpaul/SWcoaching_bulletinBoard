package com.example.swcoaching.controller;

import com.example.swcoaching.board.Post;
import com.example.swcoaching.board.PostNotFoundException;
import com.example.swcoaching.board.PostService;
import com.example.swcoaching.board.jpa.PostEntity;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
;import java.util.List;
import java.util.Map;

@AllArgsConstructor // lombok : 모든 필드 값을 파라미터로 받는 생성자 생성
@Controller
@RequestMapping
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PostService postService;


    // 게시글 생성화면
    @GetMapping("/post/create")
    public String savePost() {
        return "post/create";
    }

    // 게시판 저장
    @PostMapping("/post/create")
    public String createPost(Post post) {
        postService.createPost(post);
        return "redirect:/post";
    }

    // 게시판 목록
    @GetMapping("/post")
    public String postList(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) { // Model 객체를 통해 View에 데이터를 전달
        List<Post> postList = postService.getPostList(pageNum);
        Integer[] pageList = postService.getPageList(pageNum);
        Long totalPostCnt = postService.getPostCount();

        model.addAttribute("postList", postList);
        model.addAttribute("pageList", pageList);
        model.addAttribute("totalPostCnt", totalPostCnt);
        return "post/list";
    }

    // 게시글 상세보기
    @GetMapping("/post/detail/{id}") // URI 템플릿 "{변수}"와 PathVariable에 같은 변수명을 써야한다
    public String detailPost(@PathVariable("id") Long id, Model model) {
        Post post = postService.getPost(id);

        model.addAttribute("post", post);
        return "post/detail";
    }

    // 게시글 수정화면
    @GetMapping("/post/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "post/update";
    }

    // 게시글 수정
    @PutMapping("post/update/{id}")
    public String revisePost(Post post) {
        postService.createPost(post);
        return "redirect:/post";
    }

    // 게시글 삭제
    @DeleteMapping("/post/delete/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return "redirect:/post";
    }

    // 게시글 검색
    @GetMapping("/board/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<Post> postList = postService.searchPost(keyword);
        model.addAttribute("postList", postList);

        return "post/list";
    }

    // 예외 처리
    @ExceptionHandler(NullPointerException.class)
    public Map<String, String> exception(Exception e) {
        return Map.of("message", "값을 입력해주세요.", "details", e.getMessage());
    }


    // 조회수

}
