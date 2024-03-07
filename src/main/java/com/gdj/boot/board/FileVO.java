package com.gdj.boot.board;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class FileVO {

	private Long fileNum;
	private Long boardNum;
	private String fileName;
	private String oriName;
	
}
