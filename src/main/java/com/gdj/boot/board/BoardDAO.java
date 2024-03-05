package com.gdj.boot.board;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface BoardDAO {
	
	
	public List<BoardVO> getList()throws Exception;
	
	public int add(BoardVO boardVO)throws Exception;
}
