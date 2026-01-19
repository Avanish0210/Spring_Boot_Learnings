package com.Module4.Module4.repositories;

import com.Module4.Module4.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity , Long> {
}
