package com.ptit.controller.login;

import com.ptit.Dto.UserDto;
import com.ptit.Service.MailerService;
import com.ptit.Service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
@AllArgsConstructor
public class SignUpController {
    @Autowired
    private MailerService mailerService;

    private UserService userService;

    @ModelAttribute("userdto")
    public UserDto userSignUpDto(){
        return new UserDto();
    }

    @GetMapping("/signUp")
    public String showSignUpForm(){
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUpUserAccount(@Valid @ModelAttribute("userdto") UserDto userDto, BindingResult bindingResult,  HttpSession session){
        if (bindingResult.hasErrors()) {

            return "signUp";
        }
        if(userService.checkUserByEmail(userDto.getEmail())){
            return "redirect:/signUp?emailexist";
        }

        Random rand = new Random();
        int randomNum = 1000 + rand.nextInt(9000);
        String codeVerify = String.valueOf(randomNum);
        System.out.println(userDto.getPassWord());
        session.setAttribute("userdto", userDto);
        session.setAttribute("codeVerify", codeVerify);
        mailerService.send( "New24h-noreply", userDto.getEmail().trim(), "Mã xác nhân từ New24h",mailerService.bodyReply(codeVerify));

        return "verify";
    }

    @GetMapping("/verify-signup")
    public String enterVerifySignUp(){
        return "verify";
    }

    @PostMapping("/verify-signup")
    public String VerifySignUp(HttpSession session, @RequestParam("code") String code){
        UserDto userDto = (UserDto) session.getAttribute("userdto");
        String codeVerify = (String)session.getAttribute("codeVerify");

        if(code.equals(codeVerify)){

            userService.save(userDto);
            return "/login";
        }

        return "/signUp";

    }
}
