package com.gdj.boot.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdj.boot.board.BoardService;
import com.gdj.boot.board.BoardVO;
import com.gdj.boot.util.Pager;

@Service
public class NoticeService implements BoardService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public int add(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		
		return noticeDAO.add(boardVO);
	}
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		Long totalCount = noticeDAO.getTotalCount(pager);
		pager.makeIndex();
		pager.makeNum(totalCount);
		return noticeDAO.getList(pager);
	}
	
}
