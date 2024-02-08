package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog._core.PagingUtil;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardRepository boardRepository;

    @GetMapping({"/", "/board"})
    public String index(HttpServletRequest request, @RequestParam(defaultValue = "0") int page) {
        List<Board> boardList = boardRepository.findAll(page);
        request.setAttribute("boardList", boardList);

        int currentPage = page;
        int nextPage = currentPage + 1;
        int prevPage = currentPage - 1;
        request.setAttribute("nextPage", nextPage);
        request.setAttribute("prevPage", prevPage);

        boolean firstPage = PagingUtil.isFirst(currentPage);
        boolean lastPage = PagingUtil.isLast(currentPage, boardRepository.count());

        request.setAttribute("firstPage", firstPage);
        request.setAttribute("lastPage", lastPage);

        ArrayList pageNumber = PagingUtil.getPageNumber(boardRepository.count());
        request.setAttribute("pageNumber", pageNumber);

//        ArrayList urlNumber = PagingUtil.getURLNumber(boardRepository.count());
//        request.setAttribute("urlNumber", urlNumber);

        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO requestDTO, HttpServletRequest request) {

        if (requestDTO.getTitle().length() > 20) {
            request.setAttribute("status", 400);
            request.setAttribute("msg", "제목이 20자가 넘네요");
            return "error/40x";
        }

        if (requestDTO.getContent().length() > 20) {
            request.setAttribute("status", 400);
            request.setAttribute("msg", "내용이 20자가 넘어요");
            return "error/40x";
        }

        boardRepository.save(requestDTO);

        return "redirect:/";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {
        Board board = boardRepository.findById(id);
        request.setAttribute("board", board);

        return "board/updateForm";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id, HttpServletRequest request, BoardRequest.UpdateDTO requestDTO) {
        Board board = boardRepository.findById(id);
        boardRepository.update(requestDTO, id);
        request.setAttribute("board", board);
        return "redirect:/";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id, HttpServletRequest request) {
        Board board = boardRepository.findById(id);
        request.setAttribute("board", board);
        boardRepository.deleteById(id);
        return "redirect:/";
    }
}
