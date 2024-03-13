package com.gdj.boot.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gdj.boot.board.BoardService;
import com.gdj.boot.board.BoardVO;
import com.gdj.boot.board.FileVO;
import com.gdj.boot.util.FileManager;
import com.gdj.boot.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
//@Transactional(rollbackFor = Exception.class)
public class NoticeService implements BoardService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	@Value("${app.upload.board.notice}")
	private String uploadPath;
	@Autowired
	private FileManager fileManager;
	
	
	@Override
	public int add(BoardVO boardVO, MultipartFile[] attach) throws Exception {
		// TODO Auto-generated method stub
		int result = noticeDAO.add(boardVO);
		
		for(MultipartFile multipartFile:attach) {
			if(multipartFile.isEmpty()) {
				continue;
			}
			
			String fileName = fileManager.fileSave(uploadPath, multipartFile);
			FileVO fileVO = new FileVO();
			fileVO.setBoardNum(boardVO.getBoardNum());
			fileVO.setFileName(fileName);
			fileVO.setOriName(multipartFile.getOriginalFilename());
			result = noticeDAO.fileAdd(fileVO);
			
			if(result==0) {
				throw new Exception();
			}
		}
		return result;
	}
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		pager.makeNum(noticeDAO.getTotalCount(pager));
		pager.makeIndex();
		
		return noticeDAO.getList(pager);
	}
	
	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.getDetail(boardVO);
	}
	
	@Override
	public FileVO getFileDetail(FileVO fileVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.getFileDetail(fileVO);
	}
}
