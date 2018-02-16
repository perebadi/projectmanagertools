package com.pbc.pmtool.component;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.pbc.pmtool.entity.Comment;
import com.pbc.pmtool.model.CommentModel;

@Component("commentConverter")
public class CommentConverter {

	public CommentModel Comment2CommentModel(Comment commentEntity) {
		CommentModel commentModel = new CommentModel();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		commentModel.setDetail(commentEntity.getDetail());
		commentModel.setDatecomment(formatter.format(commentEntity.getDatecomment()));
		commentModel.setId(commentEntity.getId());
		
		return commentModel;
	}
}
