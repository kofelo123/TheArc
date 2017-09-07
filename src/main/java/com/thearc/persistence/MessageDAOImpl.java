package com.thearc.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.thearc.domain.MessageVO;
import com.thearc.domain.SearchCriteria;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace ="com.thearc.mapper.MessageMapper";	
	



	@Override
	public void updateState(Integer mid) throws Exception {

		session.update(namespace+".upldateState", mid);

	}

	
	
	@Override
	public void addCountList() throws Exception {
		// TODO Auto-generated method stub
		session.update(namespace+".addCountList");
	}

	@Override
	public List<MessageVO> listmail(MessageVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".listmail",vo);
	}

	
	  @Override
	  public List<MessageVO> listSearch(SearchCriteria cri,String targetid) throws Exception {

		  Map<String ,Object> paramMap = new HashMap<String, Object>();
		  paramMap.put("targetid",targetid);
		  paramMap.put("cri", cri);
		  
	    return session.selectList(namespace + ".listSearch", paramMap);
	  }

	  @Override
	  public int listSearchCount(SearchCriteria cri) throws Exception {

	    return session.selectOne(namespace + ".listSearchCount", cri);
	  }

	  
	  //읽기
	@Override
	public MessageVO readMessage(Integer mid) throws Exception {

		return session.selectOne(namespace+".readMessage", mid);
	}
		
	  
	@Override
	public void updateReadCheck(Integer mid) throws Exception {
		// TODO Auto-generated method stub
		session.update(namespace+".updateReadCheck", mid);
	}

	
	@Override
	public void create(MessageVO message) throws Exception {
		
		session.insert(namespace+".create", message);
	}

	
	
	  
	  
}


