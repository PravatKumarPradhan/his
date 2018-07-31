package com.param.billing.global.transaction.config.dto;

import java.util.Comparator;

public class MySequenceDto implements Comparator{
	
	private Integer seqNo;
	
	private String seqDesc;
	
	public MySequenceDto(Integer seqNo, String seqDesc){
		this.seqNo = seqNo;
		this.seqDesc = seqDesc;
	}
	
	
	public MySequenceDto() {
		// TODO Auto-generated constructor stub
	}


	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getSeqDesc() {
		return seqDesc;
	}

	public void setSeqDesc(String seqDesc) {
		this.seqDesc = seqDesc;
	}

	@Override
	public int compare(Object o1, Object o2) {
		MySequenceDto s1 = (MySequenceDto) o1;
		MySequenceDto s2 = (MySequenceDto) o2;
		 return s1.getSeqNo().compareTo(s2.getSeqNo());
	}

}
