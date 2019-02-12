package com.elmorabit.ensak.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.elmorabit.ensak.domain.AppUser;
import com.elmorabit.ensak.domain.Etudiant;
import com.elmorabit.ensak.domain.Professeur;
import com.elmorabit.ensak.dto.PasswordChangeDTO;
import com.elmorabit.ensak.dto.RegisterForm;
import com.elmorabit.ensak.repository.DepartementRepository;
import com.elmorabit.ensak.repository.EtudiantRepository;
import com.elmorabit.ensak.repository.NiveauRepository;
import com.elmorabit.ensak.repository.ProfesseurRepository;
import com.elmorabit.ensak.security.AuthoritiesConstants;
import com.elmorabit.ensak.security.SecurityConsts;
import com.elmorabit.ensak.security.SecurityUtils;
import com.elmorabit.ensak.services.AccountService;
import com.elmorabit.ensak.web.rest.errors.InvalidPasswordException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private NiveauRepository niveauRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private ProfesseurRepository professeurRepository;
    @PostMapping("/register")
    public AppUser register(@Valid @RequestBody RegisterForm userForm){
        System.out.println("#####################\n"+userForm+"\n##################");
        if (!userForm.getPassword().equals(userForm.getRepeatedPassword()))
            throw new RuntimeException("Passwords do not match");
        if (accountService.findUserByUsername(userForm.getUsername())!=null)
            throw new RuntimeException("username already in use");
        AppUser appUser=new AppUser(userForm.getUsername(),userForm.getPassword(),userForm.getFirstName(),userForm.getLastName(),"");
        appUser=accountService.saveUser(appUser);
        if (userForm.getType().equals("STUDENT")) {
            accountService.addAuthorityToUser(appUser.getId(), AuthoritiesConstants.USER);
            accountService.addAuthorityToUser(appUser.getId(), AuthoritiesConstants.STUDENT);
            Etudiant etudiant=new Etudiant();
            etudiant.setCne(userForm.getCneOrSomme());
            this.niveauRepository.findById(userForm.getNiveauOrDepartement()).ifPresent(etudiant::setNiveau);
            etudiant.setUser(appUser);
            etudiantRepository.save(etudiant);
        }else if(userForm.getType().equals("TEACHER")){
            accountService.addAuthorityToUser(appUser.getId(), AuthoritiesConstants.USER);
            accountService.addAuthorityToUser(appUser.getId(), AuthoritiesConstants.TEACHER);
            Professeur professeur=new Professeur();
            professeur.setSomme(userForm.getCneOrSomme());
            professeur.setUser(appUser);
            this.departementRepository.findById(userForm.getNiveauOrDepartement()).ifPresent(professeur::setDepartement);
            professeurRepository.save(professeur);
        }else{
            throw new RuntimeException("Type Unknown");
        }

        return appUser;
    }

    @GetMapping("/checkUsername")
    public boolean checkUsername(@RequestParam("value") String value) {
        return this.accountService.findUserByUsername(value)!=null;
    }


    /**
     * POST  /account/change-password : changes the current user's password
     *
     * @param passwordChangeDto current and new password
     * @throws InvalidPasswordException 400 (Bad Request) if the new password is incorrect
     */
    @PostMapping(path = "/account/change-password")
    @Timed
    public void changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
        accountService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
    }

    @GetMapping("/refreshToken")
    public String refreshToken(){
         return this.accountService.refreshToken();
    }
}
