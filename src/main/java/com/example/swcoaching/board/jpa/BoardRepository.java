package com.example.swcoaching.board.jpa;

import org.springframework.data.repository.CrudRepository;

// JpaRepository<Entity클래스, PK 타입>을 상속받으면 기본 CRUD 메소드가 자동 생성됨
public interface BoardRepository extends CrudRepository<BoardEntity, Long> {
}