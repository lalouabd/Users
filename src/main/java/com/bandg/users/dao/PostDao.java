package com.bandg.users.dao;

import com.bandg.users.models.Post;
import sun.misc.UUDecoder;

import java.util.Optional;
import java.util.UUID;

public interface PostDao {
    default int insertPost(Post post){
        UUID id = UUID.randomUUID();
        while(getPostById(id).isPresent())
            id = UUID.randomUUID();
        return  insertPost(id,post);
    }
    int insertPost(UUID id , Post post);

    int deletePostById(Post post);
    int updatePost(Post post);
    Optional<Post> getPostById(UUID id );

}
