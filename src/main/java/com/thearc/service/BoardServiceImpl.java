package com.thearc.service;

import com.thearc.domain.*;
import com.thearc.mapper.BoardMapper;
import com.thearc.util.ApiExplorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;

	@Transactional
	@Override
	public void regist(BoardVO board) throws Exception {

		mapper.create(board);

		String[] files = board.getFiles();

		if (files == null) {
			return;
		}

		for (String fileName : files) {
			addAttach(fileName); //board없었는데 썸네일 필요로 인해서 넣음
		}

	}

	public void addAttach(String fullName) throws Exception {
        mapper.addAttach(fullName);
        //
        int count= mapper.countImgNum();
        System.out.println("countTest"+count);

        //
     /*   Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("count", count);
        paramMap.put("fullName", fullName);*/

        mapper.updateImgNum(count,fullName);
    }
/*
	@Transactional
	@Override
	public void regist(BoardVO board) throws Exception {

		dao.create(board);

		String[] files = board.getFiles();

		if (files == null) {
			return;
		}

		for (String fileName : files) {
			dao.addAttach(fileName); //board없었는데 썸네일 필요로 인해서 넣음
		}
	}
*/

	@Transactional(isolation = Isolation.READ_COMMITTED)///격리레벨-커밋된  데이터에 대한 읽기허용 ->업데이트중인,커밋되지 않은 데이터에 접근불가,읽는 유저는 커밋이전만 볼수있다.
	@Override
	public BoardVO read(Integer bno) throws Exception {
		mapper.updateViewCnt(bno);
		return mapper.read(bno);
	}

	@Transactional
	@Override
	public void modify(BoardVO board) throws Exception {
		mapper.update(board);

		Integer bno = board.getBno();

		mapper.deleteAttach(bno);

		String[] files = board.getFiles();

		if (files == null) {
			return;
		}

		for (String fileName : files) {
			mapper.replaceAttach(fileName, bno);
		}
	}

	@Transactional
	@Override
	public void remove(Integer bno) throws Exception {
		mapper.deleteAttach(bno);
		mapper.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return mapper.listAll();
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {

		return mapper.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {

		return mapper.countPaging(cri);
	}

	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri,String category) throws Exception {
		return mapper.listSearch(cri,category);
	}
	
	@Override
	public List<String> listThumnail(SearchCriteria cri, String category) throws Exception {
		return mapper.listThumnail(cri,category);
	}

	@Override
	public int listSearchCount(SearchCriteria cri,String category) throws Exception {

		return mapper.listSearchCount(cri,category);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {

		return mapper.getAttach(bno);
	}
	
	@Override
	public String getAttachOne(Integer bno) throws Exception {
		return mapper.getAttachOne(bno);
	}

	@Override
	public void addlike(int bno) throws Exception {
		mapper.addlike(bno);
	}

	@Override
	public void sublike(int bno) throws Exception {
		mapper.sublike(bno);
	}

	@Override
	public LikeVO checklike(String uid, int bno) throws Exception {
		return mapper.checklike(uid, bno);
	}

	@Override
	public void insertlikedefault(String uid, int bno) throws Exception {
		mapper.insertlikedefault(uid, bno);
	}

	@Override
	public void updatelikey(String uid, int bno) throws Exception {
		mapper.updatelikey(uid, bno);
	}

	@Override
	public void updateliken(String uid, int bno) throws Exception {
		mapper.updateliken(uid, bno);
	}

	@Override
	public List<JsonVO> getWeather() throws Exception {
		List<JsonVO> jlist = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today=sdf.format(new Date());
        
        return new ApiExplorer().forecast(today);
	}

}
