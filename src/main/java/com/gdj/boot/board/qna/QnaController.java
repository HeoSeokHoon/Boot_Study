package com.gdj.boot.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.gdj.boot.board.BoardVO;
import com.gdj.boot.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	@Value("${board.qna.name}")
	private String name;
	
	@ModelAttribute("board")
	public String board() {
		return this.name;
	}
	
	@GetMapping("list")
	public String getList(Pager pager, Model model)throws Exception {
		List<BoardVO> ar = qnaService.getList(pager);
		model.addAttribute("list",ar);
		model.addAttribute("pager",pager);
		return "board/list";
	}
	
	@GetMapping("add")
	public String add()throws Exception {
		return "board/add";
	}
	
	@PostMapping("add")
	public String add(QnaVO qnaVO, MultipartFile[] attach)throws Exception {
		qnaService.add(qnaVO, attach);
		return "redirect:./list";
	}
	
	@GetMapping("detail")
	public String getDetail(BoardVO boardVO, Model model)throws Exception{
		boardVO = qnaService.getDetail(boardVO);
		model.addAttribute("vo",boardVO);
		return "board/detail"; 
	}
}
