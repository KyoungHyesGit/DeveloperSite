package com.dogsole.developersite.adviceBoard.service;

import com.dogsole.developersite.adviceBoard.dto.AdviceBoardReqDTO;
import com.dogsole.developersite.adviceBoard.dto.AdviceBoardResDTO;
import com.dogsole.developersite.adviceBoard.entity.AdviceBoard;
import com.dogsole.developersite.adviceBoard.entity.Comment;
import com.dogsole.developersite.adviceBoard.repository.AdviceBoardRepository;

import com.dogsole.developersite.adviceBoard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class AdviceBoardService {
    private final AdviceBoardRepository adviceBoardRepository;
    private final ModelMapper modelMapper;
    private CommentRepository commentRepository;

    // 글 작성 처리
    public void write(AdviceBoardReqDTO adviceBoardDTO) {
        AdviceBoard board = modelMapper.map(adviceBoardDTO, AdviceBoard.class);
        adviceBoardRepository.save(board);
    }

    // 게시글 리스트 처리
    public Page<AdviceBoard> list(Pageable pageable) {
        return adviceBoardRepository.findAll(pageable);
    }

    // 특정 게시글 상세보기
    public AdviceBoard view(Long id) {
        return adviceBoardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("없는 id입니다."));
    }

    public void deleteById(Long id) {
        adviceBoardRepository.deleteById(id);
    }

    public Page<AdviceBoard> searchList(String searchKeyword, Pageable pageable) {
        return adviceBoardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    public AdviceBoardResDTO update(Long id, AdviceBoardReqDTO boardReqDTO) {
        AdviceBoard adviceBoard = adviceBoardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("없는 id입니다."));

        adviceBoard.setTitle(boardReqDTO.getTitle());
        adviceBoard.setContent(boardReqDTO.getContent());

        adviceBoardRepository.save(adviceBoard);

        return modelMapper.map(adviceBoard, AdviceBoardResDTO.class);
    }

    public int getCommentCount(Long adviceBoardId) {
        List<Comment> comments = commentRepository.findByAdviceboardsId(adviceBoardId);
        return comments.size();
    }

    public Page<AdviceBoard> searchListByTitle(String searchKeyword, Pageable pageable) {
        return adviceBoardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    public Page<AdviceBoard> searchListByWriter(String searchKeyword, Pageable pageable) {
        return adviceBoardRepository.findByWriterContaining(searchKeyword, pageable);
    }


    public Page<AdviceBoard> listByDateAsc(Pageable pageable) {
        return adviceBoardRepository.findAllByOrderByRegDateAsc(pageable);
    }

    public Page<AdviceBoard> listByDateDesc(Pageable pageable) {
        return adviceBoardRepository.findAllByOrderByRegDateDesc(pageable);
    }

    public Page<AdviceBoard> listByCommentCountDesc(Pageable pageable) {
        return adviceBoardRepository.findAll(pageable);
    }

    public Page<AdviceBoard> listByCommentCountAsc(Pageable pageable) {
        return adviceBoardRepository.findAll(pageable);
    }

    public Page<AdviceBoard> listByViewsDesc(Pageable pageable) {
        return adviceBoardRepository.findAllByOrderByViewsDesc(pageable);
    }

    public Page<AdviceBoard> listByViewsAsc(Pageable pageable) {
        return adviceBoardRepository.findAllByOrderByViewsAsc(pageable);
    }
}