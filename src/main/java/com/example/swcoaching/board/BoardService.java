package com.example.swcoaching.board;

import com.example.swcoaching.board.jpa.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {


  // create
  BoardEntity createBoard(BoardEntity boardEntity);

  // read
  Board findById(long id);

  List<Board> findBoardAll();


  // update
  void updateBoard(Long id, Board board);

  // delete
  void deleteBoard(Long id);

  // 페이징 처리
  Page<Board> pageList(Pageable pageable);
}