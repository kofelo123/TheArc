package com.thearc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thearc.domain.MessageVO;
import com.thearc.domain.SearchCriteria;
import com.thearc.persistence.MessageDAO;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDAO;

	@Override
	public void addMessage(MessageVO vo) throws Exception {

		messageDAO.create(vo);
	}

	@Override
	public MessageVO readMessage(String uid, Integer mid) throws Exception {

		messageDAO.updateState(mid);

		return messageDAO.readMessage(mid);
	}

	@Override
	public List<MessageVO> listmail(MessageVO vo) throws Exception {
		return messageDAO.listmail(vo);
	}

	@Override
	public List<MessageVO> listSearchCriteria(SearchCriteria cri, String targetid) throws Exception {
		return messageDAO.listSearch(cri, targetid);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return messageDAO.listSearchCount(cri);
	}

	@Override
	public MessageVO readMessage(Integer mid) throws Exception {
		messageDAO.updateReadCheck(mid);
		return messageDAO.readMessage(mid);
	}

	@Override
	public void regist(MessageVO message) throws Exception {
		messageDAO.create(message);
	}
}
