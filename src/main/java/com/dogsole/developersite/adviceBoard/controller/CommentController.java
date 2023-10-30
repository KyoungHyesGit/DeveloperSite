package com.dogsole.developersite.adviceBoard.controller;

import com.dogsole.developersite.adviceBoard.entity.AdviceBoard;
import com.dogsole.developersite.adviceBoard.entity.Comment;
import com.dogsole.developersite.adviceBoard.dto.CommentReqDTO;
import com.dogsole.developersite.adviceBoard.dto.CommentResDTO;
import com.dogsole.developersite.adviceBoard.service.CommentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.results.ResultBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/comments")
public class CommentController {

//
//
//    @GetMapping("/delete/{seq}")
//    public String delete(@PathVariable("seq") Long seq) {
//        commentService.deleteBySeq(seq);
//        return "redirect:/adviceboard/list";
//    }
//
//    @PostMapping("/update/{seq}")
//    public String update(@PathVariable("seq") Long seq,@ModelAttribute @Valid CommentReqDTO comment) {
//        commentService.update(seq, comment);
//        return "redirect:/adviceboard/list";
//    }
//
//    @PostMapping("/writedo")
//    public String writedo(@Valid CommentReqDTO comment, Model model) {
//
//        commentService.write(comment);
//        model.addAttribute("message", "댓글 작성이 완료되었습니다.");
//        model.addAttribute("searchUrl", "/adviceboard/list");
//        return "adviceboard/message";
//    }
//
//    @GetMapping("/list")
//    public String list(Model model,
//                       @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
//                       String searchKeyword
//    ) {
//        Page<Comment> list = null;
//
//        if (searchKeyword == null) {
//            list = commentService.list(pageable);
//        } else {
//            list = commentService.searchList(searchKeyword, pageable);
//        }
//        int nowPage = list.getPageable().getPageNumber() + 1;
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 5, list.getTotalPages());
//
//        model.addAttribute("list", list);
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        return "redirect:/adviceboard/list";
//    }

    @Autowired
    private CommentService commentService;
    @Autowired
    private ModelMapper modelMapper;

    /*
    @GetMapping("/comments")
    public String showAddComments(Long id, Model model) {
        List<CommentResDTO> comments = commentService.getComment(id);
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new CommentReqDTO());
        return "redirect:/adviceboard/view";
    }
     */

    @PostMapping("/addComment")
    public String addComment(@ModelAttribute @Valid CommentReqDTO commentReqDTO, RedirectAttributes attributes) {
        Comment comment = modelMapper.map(commentReqDTO, Comment.class);
        AdviceBoard adviceBoard = new AdviceBoard();
        adviceBoard.setId(commentReqDTO.getAdviceboardId());
        comment.setAdviceboards(adviceBoard);
        commentService.addComment(comment);
        attributes.addAttribute("id", comment.getAdviceboards().getId());
        return "redirect:/adviceboard/view/{id}";
    }

    @PostMapping("/updateComment")
    public String updateComment(@ModelAttribute @Valid CommentReqDTO commentReqDTO, RedirectAttributes attributes) {
        Comment comment = modelMapper.map(commentReqDTO, Comment.class);
        AdviceBoard adviceBoard = new AdviceBoard();
        adviceBoard.setId(commentReqDTO.getAdviceboardId());
        comment.setAdviceboards(adviceBoard);
        commentService.updateComment(comment);
        attributes.addAttribute("id", comment.getAdviceboards().getId());
        return "redirect:/adviceboard/view/{id}";
    }

//    @PostMapping("/addComment")
//    public ModelAndView addComment(@ModelAttribute @Valid CommentReqDTO commentReqDTO, ResultBuilder resultBuilder, Model model) {
//       List<CommentResDTO> commentResDTO = commentService.getAllComments();
//        System.out.println(commentReqDTO);
//        return new ModelAndView("/adviceboard/view", "commentss", commentResDTO);
//    }

    @GetMapping("/editComment/{id}")
    public String editComment(@PathVariable Long id, Model model) {
        CommentResDTO comment = commentService.getCommentById(id);
        model.addAttribute("commentToEdit", comment);
        return "editComment";
    }


//    @PostMapping("/updateComment/{id}")
//    public String updateComment(@PathVariable Long id, @ModelAttribute CommentReqDTO comment) {
//        commentService.updateComment(id, comment);
//        return "redirect:/comments";
//    }

    @GetMapping("/deleteComment/{commentId}/{boardId}")
    public String deleteComment(@PathVariable Long commentId, @PathVariable Long boardId, RedirectAttributes attributes) {
        commentService.deleteComment(commentId);
        attributes.addAttribute("boardId", boardId);
        return "redirect:/adviceboard/view/{boardId}";
    }

//    @GetMapping("/usercoments")
//    public String showUserComments(Long userId, Model model){
//        List<CommentResDTO> comments = commentService.getCommendByUserId(userId);
//        model.addAttribute("comments", comments);
//        model.addAttribute("comments",new CommentReqDTO());
//        return "redirect:/adviceboard/view";
//    }





}



