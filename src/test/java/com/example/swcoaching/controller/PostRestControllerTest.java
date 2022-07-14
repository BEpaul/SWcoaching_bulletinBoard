package com.example.swcoaching.controller;

import com.example.swcoaching.board.Post;
import com.example.swcoaching.board.PostNotFoundException;
import com.example.swcoaching.board.PostService;
import com.example.swcoaching.board.jpa.PostEntity;
import com.example.swcoaching.board.jpa.PostRepository;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class PostRestControllerTest {
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") // 해당 경고문자 무시
    @Autowired
    private MockMvc mockMvc;

    // 게시판 생성
    @Test
    void 게시판_생성(){
        Long id = 10L;
        String title = "제목1";
        String contents = "네네1";
        String writer = "김민혁1";

        postService.createPost(Post.builder()
                .id(id)
                .title(title)
                .contents(contents)
                .writer(writer)
                .build()
        );

    }

    @Test
    void 게시판_조회() throws Exception {
        mockMvc.perform(get("/post/{postId}", 1)) // 해당 url에 GET request
                .andDo(print()) // 화면에 결과를 출력
                .andExpect(jsonPath("$.id").value(is(1))); // GET request하여 얻은 value를 예상
    }

    @Test
    void 게시판_수정() {

    }

    @Test
    void 게시판_삭제() throws Exception{

        mockMvc.perform(get("/deletePost/{postId}", 1))
                .andDo(print());
//                .andExpect(jsonPath("$.id").value(is(1)));
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "myTitle";
        String contents = "myContents";
        String writer = "paulGod";

        postService.createPost(Post.builder()
                .id(0L)
                .title(title)
                .contents(contents)
                .writer(writer)
                .build()
        );

        //when
        List<PostEntity> postEntityList = postRepository.findAll();

        //then
        PostEntity postEntity = postEntityList.get(2);
        assertThat(postEntity.getTitle()).isEqualTo(title);
        assertThat(postEntity.getContents()).isEqualTo(contents);
        assertThat(postEntity.getWriter()).isEqualTo(writer);
    }


}