package com.gdj.boot.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gdj.boot.board.BoardService;
import com.gdj.boot.board.BoardVO;
import com.gdj.boot.board.FileVO;
import com.gdj.boot.util.FileManager;
import com.gdj.boot.util.Pager;

@Service
public class QnaService implements BoardService {
	
	@Autowired
	private QnaDAO qnaDAO;
	@Value("${app.upload.board.qna}")
	private String uploadPath;
	@Autowired
	private FileManager fileManager;
	
	
	@Override
	public int add(BoardVO boardVO, MultipartFile[] attach) throws Exception {
		// TODO Auto-generated method stub
		int result = qnaDAO.add(boardVO);
		for(MultipartFile multipartFile:attach) {
			if(multipartFile.isEmpty()) {
				continue;
			}
			String fileName = fileManager.fileSave(uploadPath, multipartFile);
			FileVO fileVO = new FileVO();
			fileVO.setBoardNum(boardVO.getBoardNum());
			fileVO.setFileName(fileName);
			fileVO.setOriName(multipartFile.getOriginalFilename());
			qnaDAO.fileAdd(fileVO);
		}
		return result;
	}
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		pager.makeIndex();
		pager.makeNum(qnaDAO.getTotalCount(pager));
		return qnaDAO.getList(pager);
	}
	
	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.getDetail(boardVO);
	}
	
	@Override
	public FileVO getFileDetail(FileVO fileVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
