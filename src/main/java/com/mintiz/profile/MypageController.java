package com.mintiz.profile;

import com.mintiz.domain.Post;
import com.mintiz.domain.User;
import com.mintiz.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MypageController {

    private final UserService userService;

    private long id = 1L;

    @GetMapping("/mypage")
    public String mypage(Model model){

        User user = userService.findUser(id);
        model.addAttribute("user",user);
        List<Post> posts = user.getPosts();

        List<Integer> postMonth = new ArrayList<>();
        List<Integer> postDate = new ArrayList<>();
        List<Integer> postYear = new ArrayList<>();
        LocalDateTime createdDate;
        for (Post post : posts) {
            createdDate = post.getCreatedAt();
            postMonth.add(createdDate.getMonth().getValue());
            postDate.add(createdDate.getDayOfMonth());
            postYear.add(createdDate.getYear());
        }

        model.addAttribute("postMonth",postMonth);
        model.addAttribute("postDate",postDate);
        model.addAttribute("postYear",postYear);

        return "MyPage";
    }

    @GetMapping("/writing")
    public String writing(Model model){
        return "WritingList";
    }

    @GetMapping("/bookmark")
    public String bookmark(Model model){
        return "BookMark";
    }

}
