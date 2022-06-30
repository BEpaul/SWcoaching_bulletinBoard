package com.example.swcoaching.board;

import com.example.swcoaching.board.jpa.BoardEntity;
import com.example.swcoaching.board.jpa.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

  private final BoardRepository boardRepository;

  public BoardServiceImpl(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  // 게시판 생성(create)
  @Transactional
  @Override
  public BoardEntity createBoard(BoardEntity boardEntity) {
    return boardRepository.save(boardEntity);
  }

  // 게시글 조회(read)
  @Override
  @Transactional(readOnly = true)
  public Board findById(long id) {
    return boardRepository.findById(id)
            .map(Board::of)
            .orElseThrow(() -> new BoardNotFoundException(id));
  }

  // 전체 조회(read)
  @Transactional
  @Override
  public List<Board> findBoardAll() {
    Iterable<BoardEntity> boards = boardRepository.findAll();
    List<Board> boardList = new ArrayList<>();

    Iterator<BoardEntity> iterator = boards.iterator();
    for(;;) {
      if(iterator.hasNext())
      {
        boardList.add(Board.of(iterator.next()));
      }
      else{
        break;
      }
    }
    return boardList;
  }

  // 게시글 수정(update)

  @Override
  public void updateBoard(Long id, Board board) {

  }

  // 게시글 삭제(delete)
  @Transactional
  @Override
  public void deleteBoard(Long id) {
    boardRepository.deleteById(id);
  }

  // 페이징 처리

  @Transactional(readOnly = true)
  @Override
  public Page<Board> pageList(Pageable pageable) {
    return boardRepository.findAll(pageable);
  }
}