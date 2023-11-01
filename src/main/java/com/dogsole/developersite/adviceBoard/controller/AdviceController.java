package com.dogsole.developersite.adviceBoard.controller;

import com.dogsole.developersite.adviceBoard.dto.AdviceBoardReqDTO;
import com.dogsole.developersite.adviceBoard.dto.CommentReqDTO;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String view(Model model, @PathVariable("id") Long id, CommentReqDTO commentReqDTO, @RequestParam(required = false) Boolean isRedirect) {
        model.addAttribute("comment", new CommentReqDTO());

        AdviceBoard adviceBoard = adviceBoardService.view(id);

        if (isRedirect == null) {
            adviceBoard.incrementViews(); // 조회수 증가 메서드 호출
        } // 조회수 증가 메서드 호출

        // ModelMapper를 사용하여 엔티티를 DTO로 변환
        ModelMapper modelMapper = new ModelMapper();
        AdviceBoardReqDTO reqDTO = modelMapper.map(adviceBoard, AdviceBoardReqDTO.class);

        // 업데이트
        adviceBoardService.update(id, reqDTO);

        model.addAttribute("adviceboard", reqDTO); // 변환된 DTO를 모델에 추가

        return "/adviceboard/view";
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
    public String update(@PathVariable("id") Long id, @ModelAttribute @Valid AdviceBoardReqDTO board, BindingResult bindingResult, Model model, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "adviceboard/modify";
        }

        // 게시글 ID로 해당 게시글을 불러온 후 수정된 정보를 업데이트
        AdviceBoard adviceBoard = adviceBoardService.view(id);
        adviceBoard.setTitle(board.getTitle());
        adviceBoard.setWriter(board.getWriter());
        adviceBoard.setContent(board.getContent());
        adviceBoard.setCategory(board.getCategory());

        // 게시글 업데이트 메서드를 호출하여 수정을 적용
        adviceBoardService.update(id, board);
        attributes.addAttribute("isRedirect", true);
        return "redirect:/adviceboard/view/{id}";
    }

    @GetMapping("/list")
    public String list(Model model,
                       @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(name = "searchKeyword", required = false) String searchKeyword,
                       @RequestParam(name = "searchType", required = false, defaultValue = "title") String searchType,
                       @RequestParam(name = "filterCategory", required = false) String filterCategory, // 카테고리 필터 추가
                       @RequestParam(name = "sort", required = false) String sort // 정렬 옵션 추가
    ) {
        Page<AdviceBoard> list = null;

        if (searchKeyword == null) {
            if ("all".equalsIgnoreCase(filterCategory) || filterCategory == null) {
                list = adviceBoardService.list(pageable);
            } else {
                list = adviceBoardService.listByCategory(filterCategory, pageable);
            }
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
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("searchType", searchType);
        model.addAttribute("filterCategory", filterCategory); // 카테고리 필터 정보를 뷰로 전달
        model.addAttribute("sort", sort); // 정렬 옵션을 뷰로 전달

        return "adviceboard/list";
    }

    @PostMapping("/writedo")
    public String writedo(@ModelAttribute @Valid AdviceBoardReqDTO adviceBoardReqDTO, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "adviceboard/write";
        }

        String category = request.getParameter("category");
        adviceBoardReqDTO.setCategory(category);

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

    @GetMapping("/listByCategory/{filterCategory}")
    public String listByCategory(Model model,
                                 @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                 @PathVariable("filterCategory") String filterCategory) {
        Page<AdviceBoard> list = null;

        if ("all".equalsIgnoreCase(filterCategory)) {
            // "모든 카테고리"를 선택한 경우
            list = adviceBoardService.list(pageable);
        } else {
            // 특정 카테고리를 선택한 경우
            list = adviceBoardService.listByCategory(filterCategory, pageable);
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
        model.addAttribute("filterCategory", filterCategory); // 카테고리 필터 정보를 뷰로 전달

        return "adviceboard/list";
    }
}