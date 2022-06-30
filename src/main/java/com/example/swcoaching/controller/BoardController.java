package com.example.swcoaching.controller;

import com.example.swcoaching.board.Board;
import com.example.swcoaching.board.BoardNotFoundException;
import com.example.swcoaching.board.BoardService;
import com.example.swcoaching.board.BoardServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@AllArgsConstructor
//@RestController
@Controller
@RequestMapping
public class BoardController {
  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final BoardService boardService;

  @GetMapping("/board/{boardId}")
  public Board getBoard(@PathVariable long boardId) {
    Board board = boardService.findById(boardId);
    logger.info("Board: {}", board);
    return board;
  }

  // board 리스트
  @GetMapping("/board/list")
  public String list() {
    return "/board/list";
  }

  // 페이징 처리
  @GetMapping("/")
  public String index(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//    model.addAttribute("boards", ); boardService

    return "index";
  }

  // 4회차 미팅
  // 예외처리
  @ExceptionHandler(BoardNotFoundException.class)
  public Map<String, String> exception(BoardNotFoundException e) {
    return Map.of("message", "특정 코드를 탐색하는데 오류가 발생했습니다.", "details", e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public Map<String, String> exception(Exception e) {
    return Map.of("message", "알 수 없는 이유로 에러가 발생했습니다.", "details", e.getMessage());
  }

}