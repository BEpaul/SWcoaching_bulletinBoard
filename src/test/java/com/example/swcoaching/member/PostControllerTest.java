package com.example.swcoaching.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") // 해당 경고문자 무시
    @Autowired
    private MockMvc mockMvc;

    // 페이징 처리
    @GetMapping("/post")
    public String index(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//    model.addAttribute("post", p);

        return "index";
    }

}
