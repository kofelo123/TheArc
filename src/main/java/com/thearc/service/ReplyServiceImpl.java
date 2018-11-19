package com.thearc.service;

import com.thearc.domain.Criteria;
import com.thearc.domain.ReplyVO;
import com.thearc.mapper.BoardMapper;
import com.thearc.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;

	@Autowired
	private BoardMapper boardMapper;

	@Transactional
	@Override
	public void addReply(ReplyVO vo) throws Exception {

		replyMapper.create(vo);
		boardMapper.updateReplyCnt(vo.getBno(), 1);
	}

	@Transactional
	@Override
	public void removeReply(Integer rno) throws Exception {

		int bno = replyMapper.getBno(rno);/// 댓글번호로 소속 게시글번호 얻어오기
		replyMapper.delete(rno);/// 해당 댓글 삭제
		boardMapper.updateReplyCnt(bno, -1);/// 게시글의 댓글수감소
	}

	@Override
	public List<ReplyVO> listReply(Integer bno) throws Exception {

		return replyMapper.list(bno);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {

		replyMapper.update(vo);
	}

	@Override
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception {

		return replyMapper.listPage(bno, cri);
	}

	@Override
	public int count(Integer bno) throws Exception {

		return replyMapper.count(bno);
	}

}
