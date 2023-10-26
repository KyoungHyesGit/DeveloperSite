package com.dogsole.developersite.adviceBoard.service;

import com.dogsole.developersite.adviceBoard.dto.AdviceBoardReqDTO;
import com.dogsole.developersite.adviceBoard.dto.AdviceBoardResDTO;
import com.dogsole.developersite.adviceBoard.entity.AdviceBoard;
import com.dogsole.developersite.adviceBoard.repository.AdviceBoardRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class AdviceBoardService {
    private final AdviceBoardRepository adviceBoardRepository;
    private final ModelMapper modelMapper;

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

}