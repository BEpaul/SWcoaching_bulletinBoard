package com.example.swcoaching.board;

import com.example.swcoaching.board.jpa.BoardEntity;
import com.example.swcoaching.board.jpa.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl implements BoardService {

  // board는 QnA 등의 게시판 종류를 의미할 때 사용 --> 게시글에 대한 모든 서비스들은 Post에서 작성!
  private final BoardRepository boardRepository;

  public BoardServiceImpl(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }






}