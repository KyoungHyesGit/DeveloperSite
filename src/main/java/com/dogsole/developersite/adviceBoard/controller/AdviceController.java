package com.dogsole.developersite.adviceBoard.controller;

import com.dogsole.developersite.adviceBoard.dto.AdviceBoardReqDTO;
import com.dogsole.developersite.adviceBoard.dto.CommentReqDTO;
import com.dogsole.developersite.adviceBoard.dto.CommentResDTO;
import com.dogsole.developersite.adviceBoard.entity.AdviceBoard;
import com.dogsole.developersite.adviceBoard.service.AdviceBoardService;
import com.dogsole.developersite.adviceBoard.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("adviceboard", adviceBoardService.view(id));
        model.addAttribute("comment", new CommentReqDTO());
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
    public String update(@PathVariable("id") Long id,@ModelAttribute @Valid AdviceBoardReqDTO board) {
        adviceBoardService.update(id, board);
        return "redirect:/adviceboard/list";
    }

    @GetMapping("/list")
    public String list(Model model,
                       @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       String searchKeyword
    ) {
        Page<AdviceBoard> list = null;

        if (searchKeyword == null) {
            list = adviceBoardService.list(pageable);
        } else {
            list = adviceBoardService.searchList(searchKeyword, pageable);
        }
        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "adviceboard/list";
    }

    @PostMapping("/writedo")
    public String writedo(@Valid AdviceBoardReqDTO adviceBoard, Model model) {

        adviceBoardService.write(adviceBoard);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/adviceboard/list");
        return "adviceboard/message";
    }
}