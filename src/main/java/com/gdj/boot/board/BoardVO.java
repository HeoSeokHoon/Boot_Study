package com.gdj.boot.board;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
	private Date boarDate;
	private Long boardHit;
	private String boardContents;
	
}
