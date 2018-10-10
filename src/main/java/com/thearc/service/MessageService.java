package com.thearc.service;

import com.thearc.domain.MessageVO;
import com.thearc.domain.SearchCriteria;

import java.util.List;

public interface MessageService {

//	public void addMessage(MessageVO vo) throws Exception;

	public void regist(MessageVO message) throws Exception;

	public MessageVO readMessage(String uid, Integer mno) throws Exception;


//	public List<MessageVO> listmail(MessageVO vo) throws Exception;
	// 쪽지함 관련 로직

	public List<MessageVO> listSearchCriteria(SearchCriteria cri, String targetid) throws Exception;

	public int listSearchCount(SearchCriteria cri) throws Exception;

	public MessageVO readMessage(Integer mid) throws Exception;
}
