package com.pbc.pmtool.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbc.pmtool.entity.Comment;

@Repository("commentRepository")
public interface CommentRepository extends JpaRepository<Comment, Serializable> {

}
