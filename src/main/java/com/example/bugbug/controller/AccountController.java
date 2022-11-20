package com.example.bugbug.controller;

import com.example.bugbug.entity.Users;
import com.example.bugbug.form.InputForm;
import com.example.bugbug.service.UserService;
import com.example.bugbug.validator.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

// アカウント登録用のコントローラー
@RequiredArgsConstructor
@Controller
public class AccountController {
    private final UserService service;
    private final PasswordValidator passValidator;
    private final PasswordValidator mailValidator;

    @Autowired
    private HttpSession session;

    // Form初期設定エリア
    @ModelAttribute
    public InputForm InputSetUpForm() {
        return new InputForm();
    }

    // 自作バリデーションの導入
    @InitBinder("inputForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(passValidator);
        webDataBinder.addValidators(mailValidator);
    }

    // 新規登録フォームへの遷移
    @GetMapping("sinup/form")
    public String viewAcountForm() {
        return "sinup";
    }

    // 新規アカウント登録処理
    @PostMapping("sinup")
    public String createAcount(@Validated InputForm f, BindingResult bindingResult, Model model) {
        // validation
        if (bindingResult.hasErrors()) {
            return "sinup/form";
        }
        // パスワードのハッシュ化
        String hash = service.hash(f.getPass());
        // 現在の日付を取得
        Date date = service.getDate();
        // 登録用のエンティティの定義
        Users user = new Users(null, f.getName(), null, f.getMail(), hash, date);
        // 登録処理
        service.addUser(user);
        // ユーザーIDの取得
        List<Users> list = service.findMail(f.getMail());
        // セッションへの追加
        session.setAttribute("user_id", list.get(0).getUser_id());
        session.setAttribute("user_name", f.getName());
        // トップページにリダイレクト
        return "redirect:/index";
    }

}
