package com.thearc.persistence;

import java.util.List;

import com.thearc.domain.MessageVO;
import com.thearc.domain.SearchCriteria;

public interface MessageDAO {

	public void updateState(Integer mid) throws Exception;

	public void addCountList() throws Exception;

	public List<MessageVO> listmail(MessageVO vo) throws Exception;

	public List<MessageVO> listSearch(SearchCriteria cri, String targetid) throws Exception;

	public int listSearchCount(SearchCriteria cri) throws Exception;

	public MessageVO readMessage(Integer mid) throws Exception;

	public void updateReadCheck(Integer mid) throws Exception;

	public void create(MessageVO message) throws Exception;

}
