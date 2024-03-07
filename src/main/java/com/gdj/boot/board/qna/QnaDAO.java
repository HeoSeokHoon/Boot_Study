package com.gdj.boot.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.gdj.boot.board.BoardDAO;
import com.gdj.boot.board.BoardVO;

@Mapper
public interface QnaDAO extends BoardDAO {
	
	public int refUpdate(BoardVO boardVO)throws Exception;
	
}
