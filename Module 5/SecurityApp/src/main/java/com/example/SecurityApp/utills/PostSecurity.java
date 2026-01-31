package com.example.SecurityApp.utills;

import com.example.SecurityApp.dto.PostDTO;
import com.example.SecurityApp.entities.PostEntity;
import com.example.SecurityApp.entities.User;
import com.example.SecurityApp.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSecurity {

    private final PostService postService;
    public boolean isOwnerOfPost(Long postId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostDTO post = postService.getPostById(postId);
        return post.getAuthor().getId().equals(user.getId());

    }
}
