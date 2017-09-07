package com.thearc.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.thearc.domain.BoardVO;
import com.thearc.domain.Criteria;
import com.thearc.domain.LikeVO;
import com.thearc.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

  @Inject
  private SqlSession session;

  private static String namespace = "com.thearc.mapper.BoardMapper";

  @Override
  public void create(BoardVO vo) throws Exception {
    session.insert(namespace + ".create", vo);
  }

  @Override
  public BoardVO read(Integer bno) throws Exception {
    return session.selectOne(namespace + ".read", bno);
  }

  @Override
  public void update(BoardVO vo) throws Exception {
    session.update(namespace + ".update", vo);
  }

  @Override
  public void delete(Integer bno) throws Exception {
    session.delete(namespace + ".delete", bno);
  }

  @Override
  public List<BoardVO> listAll() throws Exception {
    return session.selectList(namespace + ".listAll");
  }

  @Override
  public List<BoardVO> listPage(int page) throws Exception {

    if (page <= 0) {
      page = 1;
    }

    page = (page - 1) * 10;

    return session.selectList(namespace + ".listPage", page);
  }

  @Override
  public List<BoardVO> listCriteria(Criteria cri) throws Exception {

    return session.selectList(namespace + ".listCriteria", cri);
  }

  @Override
  public int countPaging(Criteria cri) throws Exception {

    return session.selectOne(namespace + ".countPaging", cri);
  }

  @Override
  public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {

    return session.selectList(namespace + ".listSearch", cri);
  }

  @Override
  public int listSearchCount(SearchCriteria cri) throws Exception {

    return session.selectOne(namespace + ".listSearchCount", cri);
  }

  @Override
  public void updateReplyCnt(Integer bno, int amount) throws Exception {

    Map<String, Object> paramMap = new HashMap<String, Object>();

    paramMap.put("bno", bno);
    paramMap.put("amount", amount);

    session.update(namespace + ".updateReplyCnt", paramMap);
  }

  @Override
  public void updateViewCnt(Integer bno) throws Exception {
    
    session.update(namespace+".updateViewCnt", bno);
    
  }


  @Override
  public void addAttach(String fullName) throws Exception {
    
    session.insert(namespace+".addAttach", fullName);
    
  }
  
  @Override
  public List<String> getAttach(Integer bno) throws Exception {
    
    return session.selectList(namespace +".getAttach", bno);
  }
 

  @Override
  public void deleteAttach(Integer bno) throws Exception {

    session.delete(namespace+".deleteAttach", bno);
    
  }

  @Override
  public void replaceAttach(String fullName, Integer bno) throws Exception {
    
    Map<String, Object> paramMap = new HashMap<String, Object>();
    
    paramMap.put("bno", bno);
    paramMap.put("fullName", fullName);
    
    session.insert(namespace+".replaceAttach", paramMap);
    
  }

@Override
public void addlike(int bno) throws Exception {
	// TODO Auto-generated method stub
	 session.update(namespace+".addlike",bno);
}

@Override
public void sublike(int bno) throws Exception {
	// TODO Auto-generated method stub
	session.update(namespace+".sublike",bno);
}



@Override
public LikeVO checklike(String uid, int bno) throws Exception {
	// TODO Auto-generated method stub
	Map<String, Object> paramMap = new HashMap<String,Object>();
	
	paramMap.put("uid", uid);
	paramMap.put("bno", bno);
	return session.selectOne(namespace+".checklike",paramMap);
}

@Override
public void insertlikedefault(String uid, int bno) throws Exception {
	// TODO Auto-generated method stub
	Map<String,Object> paramMap = new HashMap<String,Object>();
	
	paramMap.put("uid", uid);
	paramMap.put("bno", bno);
	
	session.insert(namespace+".insertlikedefault",paramMap);
}

@Override
public void updatelikey(String uid, int bno) throws Exception {
	// TODO Auto-generated method stub
	Map<String,Object> paramMap = new HashMap<String,Object>();
	
	paramMap.put("uid", uid);
	paramMap.put("bno", bno);
	
	session.update(namespace+".updatelikey",paramMap);
}



@Override
public void updateliken(String uid, int bno) throws Exception {
	// TODO Auto-generated method stub
	
	Map<String,Object> paramMap = new HashMap<String,Object>();
	
	paramMap.put("uid", uid);
	paramMap.put("bno", bno);
	
	session.update(namespace+".updateliken",paramMap);
}

@Override
public void create2(BoardVO vo) throws Exception {
  session.insert(namespace + ".create2", vo);
}
@Override
public void addAttach2(String fullName) throws Exception {
  
  session.insert(namespace+".addAttach2", fullName);
  
}

}
