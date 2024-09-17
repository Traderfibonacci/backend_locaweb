package br.com.fiap.localweb.controller;

import br.com.fiap.localweb.dto.UserAccountExhibitDto;
import br.com.fiap.localweb.dto.UserAccountRegisterDto;
import br.com.fiap.localweb.model.UserAccount;
import br.com.fiap.localweb.repository.UserAccountRepository;
import br.com.fiap.localweb.service.UserAccountService;
import java.util.List;

import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserAccountExhibitDto saveUserAccount(@RequestBody @Valid UserAccountRegisterDto userAccount){
        return userAccountService.saveUserAccount(userAccount);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserAccountExhibitDto findUserAccountById(@PathVariable Long id){
        return userAccountService.findUserAccountById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserAccount> listAllUserAccount(){
        return userAccountService.listAllUserAccount();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserAccountExhibitDto updateUserAccount(@RequestBody @Valid UserAccount userAccount){
        return userAccountService.updateUserAccount(userAccount);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserAccount(@PathVariable Long id){
        userAccountService.deleteUserAccount(id);
    }

}
