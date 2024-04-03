package org.itpu.service;

import org.itpu.entity.Comment;
import org.itpu.entity.Post;

import java.util.function.Consumer;

public interface SocialMediaService {

    Post getPostById(Integer id);

    Comment getCommentById(Integer id);

    Integer createPost(Consumer<Post> postConfig);

    Integer addComment(Integer postId, Consumer<Comment> commentConfig);

    void removePost(Integer postId);

    void removeComment(Integer commentId);
}
