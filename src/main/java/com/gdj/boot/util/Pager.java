package com.gdj.boot.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Pager {
	
	private Long page;
	private Long perPage;
	private Long startIndex;
	
	private Long totalPage;
	private Long startNum;
	private Long lastNum;
	
	private boolean start;
	private boolean last;
	
	private String search;
	private String kind;
	
	public void makeIndex() {
		this.startIndex = (this.getPage()-1)*this.getPage();
	}
	
	public void makeNum(Long totalCount) {
		if(totalCount<1) {
			totalCount=1L;
		}
		//1. 총 페이지 구하기
		this.totalPage = totalCount/this.getPerPage();
		if(totalCount%this.getPerPage()!=0) {
			this.totalPage++;
		}
		this.setTotalPage(totalPage);
		
		//2. 총블럭의 수 구하기
		Long perBlock = 5L;
		Long totalBlock = this.getTotalPage()/perBlock;
		if(this.getTotalPage()%perBlock!=0) {
			totalBlock++;
		}
		
		//3. Page의 값으로 현재 블럭 번호 구하기
		Long curBlock = this.getPage()/perBlock;
		if(this.getPage()%perBlock!=0) {
			curBlock++;
		}
		
		//4. 현재 블럭번호로 시작번호와 끝번호 구하기
		Long lastNum=curBlock*perBlock;
		Long startNum=lastNum-perBlock+1L;
		this.setLastNum(lastNum);
		this.setStartNum(startNum);
		
		//5. 이전, 다음 블럭 유무
		if(curBlock==1) {
			this.setStart(true);
		}
		if(curBlock==totalBlock) {
			this.setLastNum(this.getTotalPage());
			this.setLast(true);
		}
	}
	
	public Long getPage() {
		if(this.page==null||this.page<1) {
			this.page=1L;
		}
		return this.page;
	}
	
	public Long getPerPage() {
		if(this.perPage==null||this.perPage<1) {
			this.perPage=10L;
		}
		return this.perPage;
	}
	
	public String getSearch() {
		if(this.search==null) {
			this.search="";
		}
		return this.search;
	}
	
}
