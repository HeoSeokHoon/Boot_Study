package com.gdj.boot.board;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Setter
@Getter
public class BoardVO {
	
	private Long boardNum;
	private String boardWriter;
	private String boardHead;
	private Date boardDate;
	private Long boardHit;
	private String boardContents;
	
	private List<FileVO> fileVOs;
}
