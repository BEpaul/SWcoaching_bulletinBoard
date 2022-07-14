package com.example.swcoaching.board;
import com.example.swcoaching.board.jpa.PostEntity;
import com.example.swcoaching.board.jpa.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class PostServiceImpl implements PostService{

    private static final int BLOCK_NUM_COUNT = 10; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 5; // 한 페이지에 존재하는 게시글 수

    @Autowired
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    @Override
    public Long createPost(Post post) {
        return postRepository.save(post.toEntity()).getId();
    }

    // read
    @Transactional
    @Override
    public Post findById(long id) {
        return postRepository.findById(id).map(Post::of).orElseThrow(() -> new PostNotFoundException(id));
    }

    // 전체 read
    @Transactional
    @Override
    public List<Post> getPostList(Integer pageNum) {
        // 페이징 처리 (limit, offset(몇 개를 가져올 것인가?), 정렬 방식)
        Page<PostEntity> page = postRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));

        // Map - stream을 이용한 문법으로 리팩토링 하기!!!
//        StreamSupport.stream(postRepository.findAll().spliterator(), false).map();
        List<PostEntity> postEntities = page.getContent();
        List<Post> postList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            postList.add(this.convertEntityToDto(postEntity));
            }
        return postList;
        }


    // 상세보기
    @Transactional
    @Override
    public Post getPost(Long id) {
        Optional<PostEntity> postEntityWrapper = postRepository.findById(id);
        PostEntity postEntity = postEntityWrapper.get();
        
        return this.convertEntityToDto(postEntity);
    }

    // delete
    @Transactional
    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // 검색 기능
    @Transactional
    @Override
    public List<Post> searchPost(String keyword) {
        List<PostEntity> postEntities = postRepository.findByTitleContaining(keyword);
        List<Post> postList = new ArrayList<>();

        if(postEntities.isEmpty()){
            return postList;
        }
        else {
            for (PostEntity postEntity : postEntities) {
                postList.add(this.convertEntityToDto(postEntity));
            }
        }
        return postList;
    }


//    // 페이징 처리
//    @Transactional
//    @Override
//    public Page<Post> pageList(Pageable pageable) {
//        Page<PostEntity> result = postRepository.findAll(pageable);
//        return result.map(Post::of);
//    }

    // 총 게시글 개수
    @Transactional
    @Override
    public Long getPostCount() {
        return postRepository.count();
    }

    // 페이징 처리
    @Override
    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_NUM_COUNT];

        //총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getPostCount());

        //총 게시글을 기준으로 계산한 마지막 페이지 번호 계산 (올림)
        Integer totalLastPageNum = (int) (Math.ceil(postsTotalCount / PAGE_POST_COUNT));

        //현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_NUM_COUNT) ? curPageNum + BLOCK_NUM_COUNT : totalLastPageNum;

        //페이지 시작 번호 조정
        curPageNum = (curPageNum <= 4) ? 1 : curPageNum - 3;

        //페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }

        return pageList;
    }

    // 조회수
//    @Transactional
//    @Override
//    public int updateHit(Long id) {
//        return postRepository.updateHit(id);
//    }

    // Entity -> Dto 메소드
    private Post convertEntityToDto(PostEntity postEntity) {
        return Post.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .contents(postEntity.getContents())
                .writer(postEntity.getWriter())
                .createdDate(postEntity.getCreatedDate())
                .build();
    }
}
