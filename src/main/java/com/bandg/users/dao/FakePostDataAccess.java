package com.bandg.users.dao;


import com.bandg.users.models.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FakePostDataAccess  implements PostDao {
    private final List<Post> db;

    public FakePostDataAccess() {
        this.db = new ArrayList<>();
    }

    @Override
    public int insertPost(UUID id, Post post) {

        return 1;
    }

    @Override
    public int deletePostById(Post post) {
        return 0;
    }

    @Override
    public int updatePost(Post post) {
        return 0;
    }

    @Override
    public Optional<Post> getPostById(UUID id) {

        return Optional.empty();
    }
}
