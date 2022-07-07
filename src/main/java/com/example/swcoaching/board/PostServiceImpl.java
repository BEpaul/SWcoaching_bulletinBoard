package com.example.swcoaching.board;
import com.example.swcoaching.board.jpa.PostEntity;
import com.example.swcoaching.board.jpa.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // create
    @Transactional
    @Override
    public PostEntity createPost(PostEntity postEntity) {
        return postRepository.save(postEntity);
    }

    // read
    @Transactional
    @Override
    public Post findById(long id) {
        return postRepository.findById(id).map(Post::of).orElseThrow(() -> new PostNotFoundException(id));
    }

    // 전체 read
    @Override
    @Transactional
    public List<Post> findPostAll() {
        // Map - stream을 이용한 문법으로 리팩토링 하기!!!
//        StreamSupport.stream(postRepository.findAll().spliterator(), false).map();

        Iterable<PostEntity> postEntities = postRepository.findAll();
        List<Post> postList = new ArrayList<>();

        Iterator<PostEntity> iterator = postEntities.iterator();
        for(;;){
            if(iterator.hasNext()){
                postList.add(Post.of(iterator.next()));
            }
            else {
                break;
            }
        }
        return postList;

    }


    // update
    @Transactional
    @Override
    public void updateBoard(Long id, Post post) {

        Optional<PostEntity> optPost = postRepository.findById(id);
        PostEntity postEntity = optPost.get();
        postEntity.updatePost(post);

//        Optional<PostEntity> entity = this.postRepository.findById(id);
//        entity.ifPresent(t -> {
//            if (post.getContents() != null) {
//                 t.setContents(post.getContents());
//
//            } else if (post.getTitle() != null) {
//                 t.setTitle(post.getTitle());
//            }
//            this.postRepository.save(t);
//        });
//
//        return entity;

    }

    // delete
    @Transactional
    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // 페이징 처리
    @Override
    public Page<Post> pageList(Pageable pageable) {
        Page<PostEntity> result = postRepository.findAll(pageable);
        return result.map(Post::of);
    }

}
