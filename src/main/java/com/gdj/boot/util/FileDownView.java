package com.gdj.boot.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.gdj.boot.board.FileVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileDownView extends AbstractView {
	@Value("${app.upload.basePath}")
	private String base;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		log.info("==========={}==========","fileDownView");
		log.info("{}",model);
		log.info("{}",request.getRequestURI());
		
		FileVO fileVO = (FileVO)model.get("fileVO");
		String uri = request.getRequestURI();
		uri = uri.substring(0, uri.lastIndexOf("/")+1);
		log.info(base+uri);
		File file = new File(base+uri, fileVO.getFileName());
		
		//응답 시 한글처리
		response.setCharacterEncoding("UTF-8");
		
		//총 파일 크기
		response.setContentLengthLong(file.length()); 
		
		//한글파일명 인코딩
		String oriName = URLEncoder.encode(fileVO.getOriName(),"UTF-8");
		
		//Header 설정
		response.setHeader("Content-Disposition", "attachment;fileName=\""+oriName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		//Server 의 HDD에서 파일을 읽어 오는 작업
		FileInputStream fi = new FileInputStream(file);
		
		//읽어온 파일을 클라이언트로 전송
		OutputStream os = response.getOutputStream();
		FileCopyUtils.copy(fi, os);
		
		//연결 해제
		os.close();
		fi.close();
	}
	
}
