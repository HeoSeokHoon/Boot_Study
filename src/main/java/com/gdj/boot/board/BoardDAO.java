package com.gdj.boot.board;

import java.util.List;

import com.gdj.boot.util.Pager;

public interface BoardDAO {
	
	
	public List<BoardVO> getList(Pager pager)throws Exception;
	
	public int add(BoardVO boardVO)throws Exception;
	
	public Long getTotalCount(Pager pager)throws Exception;
	
	public int fileAdd(FileVO fileVO)throws Exception;
	
	public BoardVO getDetail(BoardVO boardVO)throws Exception;
	
	public FileVO getFileDetail(FileVO fileVO)throws Exception;
}
