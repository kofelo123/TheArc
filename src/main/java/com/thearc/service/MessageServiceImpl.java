package com.thearc.service;

import com.thearc.domain.MessageVO;
import com.thearc.domain.SearchCriteria;
import com.thearc.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper mapper;

	@Override
	public void regist(MessageVO message) throws Exception {
		mapper.create(message);
	}

	/*@Override
	public void addMessage(MessageVO vo) throws Exception {

		mapper.create(vo);
	}*/

	@Override
	public MessageVO readMessage(String uid, Integer mid) throws Exception {

		mapper.updateState(mid);

		return mapper.readMessage(mid);
	}

/*	@Override
	public List<MessageVO> listmail(MessageVO vo) throws Exception {
		return mapper.listmail(vo);
	}*/

	@Override
	public List<MessageVO> listSearchCriteria(SearchCriteria cri, String targetid) throws Exception {
		return mapper.listSearch(cri, targetid);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return mapper.listSearchCount(cri);
	}

	@Override
	public MessageVO readMessage(Integer mid) throws Exception {
		mapper.updateReadCheck(mid);
		return mapper.readMessage(mid);
	}
}
