package com.gdj.boot.board.qna;

import com.gdj.boot.board.BoardVO;

import lombok.Data;

@Data
public class QnaVO extends BoardVO{
	
	private Long boardRef;
	private Long boardStep;
	private Long boardDepth;
	
}
