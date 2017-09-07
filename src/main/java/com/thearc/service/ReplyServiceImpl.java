package com.thearc.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thearc.domain.Criteria;
import com.thearc.domain.ReplyVO;
import com.thearc.persistence.BoardDAO;
import com.thearc.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO replyDAO;

	@Inject
	private BoardDAO boardDAO;

	@Transactional
	@Override
	public void addReply(ReplyVO vo) throws Exception {

		replyDAO.create(vo);
		boardDAO.updateReplyCnt(vo.getBno(), 1);
	}

	@Transactional
	@Override
	public void removeReply(Integer rno) throws Exception {

		int bno = replyDAO.getBno(rno);/// 댓글번호로 소속 게시글번호 얻어오기
		replyDAO.delete(rno);/// 해당 댓글 삭제
		boardDAO.updateReplyCnt(bno, -1);/// 게시글의 댓글수감소
	}

	@Override
	public List<ReplyVO> listReply(Integer bno) throws Exception {

		return replyDAO.list(bno);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {

		replyDAO.update(vo);
	}

	@Override
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception {

		return replyDAO.listPage(bno, cri);
	}

	@Override
	public int count(Integer bno) throws Exception {

		return replyDAO.count(bno);
	}

}
