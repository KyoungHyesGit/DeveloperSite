package com.dogsole.developersite.adviceBoard.controller;

import com.dogsole.developersite.adviceBoard.dto.AdviceBoardReqDTO;
import com.dogsole.developersite.adviceBoard.dto.CommentReqDTO;
import com.dogsole.developersite.adviceBoard.dto.CommentResDTO;
import com.dogsole.developersite.adviceBoard.entity.AdviceBoard;
import com.dogsole.developersite.adviceBoard.service.AdviceBoardService;
import com.dogsole.developersite.adviceBoard.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/adviceboard")
public class AdviceController {
    private final AdviceBoardService adviceBoardService;
    private final CommentService commentService;

    @GetMapping("/write")
    public String write(AdviceBoardReqDTO adviceBoardReqDTO) {
        return "adviceboard/write";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") Long id, CommentReqDTO commentReqDTO) {
        model.addAttribute("comment", new CommentReqDTO());

        AdviceBoard adviceBoard = adviceBoardService.view(id);
        adviceBoard.incrementViews(); // 조회수 증가 메서드 호출

        // ModelMapper를 사용하여 엔티티를 DTO로 변환
        ModelMapper modelMapper = new ModelMapper();
        AdviceBoardReqDTO reqDTO = modelMapper.map(adviceBoard, AdviceBoardReqDTO.class);

        // 업데이트
        adviceBoardService.update(id, reqDTO);

        model.addAttribute("adviceboard", reqDTO); // 변환된 DTO를 모델에 추가

        return "adviceboard/view";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        adviceBoardService.deleteById(id);
        return "redirect:/adviceboard/list";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long id, Model model) {
        model.addAttribute("adviceboard", adviceBoardService.view(id));
        return "adviceboard/modify";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute @Valid AdviceBoardReqDTO board, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "adviceboard/modify";
        }
        adviceBoardService.update(id, board);
        return "redirect:/adviceboard/view/{id}";
    }
    @GetMapping("/list")
    public String list(Model model,
                       @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(name = "searchKeyword", required = false) String searchKeyword,
                       @RequestParam(name = "searchType", required = false, defaultValue = "title") String searchType
    ) {
        Page<AdviceBoard> list = null;

        if (searchKeyword == null) {
            list = adviceBoardService.list(pageable);
        } else {
            if ("title".equals(searchType)) {
                list = adviceBoardService.searchListByTitle(searchKeyword, pageable);
            } else if ("writer".equals(searchType)) {
                list = adviceBoardService.searchListByWriter(searchKeyword, pageable);
            }
        }

        for (AdviceBoard board : list) {
            int commentCount = commentService.getCommentCount(board.getId());
            board.setCommentCount(commentCount);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("searchKeyword", searchKeyword); // 검색어를 뷰로 전달
        model.addAttribute("searchType", searchType); // 검색 유형을 뷰로 전달

        return "adviceboard/list";
    }

    @PostMapping("/writedo")
    public String writedo(@ModelAttribute @Valid AdviceBoardReqDTO adviceBoardReqDTO, BindingResult result, Model model, HttpServletRequest request){
            if (result.hasErrors()) {
            return "adviceboard/write";
        }

        adviceBoardService.write(adviceBoardReqDTO);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/adviceboard/list");
        return "adviceboard/message";
    }

    // 작성일 빠른순
    @GetMapping("/listByDateAsc")
    public String listByDateAsc(Model model, @PageableDefault(page = 0, size = 5, sort = "regDate", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<AdviceBoard> list = adviceBoardService.listByDateAsc(pageable);
        addAttributes(model, list);
        return "adviceboard/list";
    }

    // 작성일 느린순
    @GetMapping("/listByDateDesc")
    public String listByDateDesc(Model model, @PageableDefault(page = 0, size = 5, sort = "regDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<AdviceBoard> list = adviceBoardService.listByDateDesc(pageable);
        addAttributes(model, list);
        return "adviceboard/list";
    }

    // 댓글많은순
    @GetMapping("/listByCommentCountDesc")
    public String listByCommentCountDesc(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable) {
        Page<AdviceBoard> list = adviceBoardService.listByCommentCountDesc(pageable);
        addAttributes(model, list);
        return "adviceboard/list";
    }

    // 댓글적은순
    @GetMapping("/listByCommentCountAsc")
    public String listByCommentCountAsc(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable) {
        Page<AdviceBoard> list = adviceBoardService.listByCommentCountAsc(pageable);
        addAttributes(model, list);
        return "adviceboard/list";
    }

    // 조회수 많은순
    @GetMapping("/listByViewsDesc")
    public String listByViewsDesc(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable) {
        Page<AdviceBoard> list = adviceBoardService.listByViewsDesc(pageable);
        addAttributes(model, list);
        return "adviceboard/list";
    }

    // 조회수 적은순
    @GetMapping("/listByViewsAsc")
    public String listByViewsAsc(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable) {
        Page<AdviceBoard> list = adviceBoardService.listByViewsAsc(pageable);
        addAttributes(model, list);
        return "adviceboard/list";
    }

    private void addAttributes(Model model, Page<AdviceBoard> list) {
        for (AdviceBoard board : list) {
            int commentCount = commentService.getCommentCount(board.getId());
            board.setCommentCount(commentCount);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
    }
}