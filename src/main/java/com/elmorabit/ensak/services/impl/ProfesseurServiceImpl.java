package com.elmorabit.ensak.services.impl;

import com.elmorabit.ensak.domain.AppUser;
import com.elmorabit.ensak.domain.Professeur;
import com.elmorabit.ensak.dto.ExcelUserRow;
import com.elmorabit.ensak.dto.ImportProfesseurList;
import com.elmorabit.ensak.repository.AuthorityRepository;
import com.elmorabit.ensak.repository.ProfesseurRepository;
import com.elmorabit.ensak.repository.UserRepository;
import com.elmorabit.ensak.security.AuthoritiesConstants;
import com.elmorabit.ensak.services.AccountService;
import com.elmorabit.ensak.services.ProfesseurService;
import com.elmorabit.ensak.services.util.NullAwareBeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Optional;


@Service
@Transactional
public class ProfesseurServiceImpl implements ProfesseurService {

    @Autowired
    ProfesseurRepository professeurRepository;
    @Autowired
    AccountService accountService;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    NullAwareBeanUtilsBean nullAwareBeanUtilsBean;

    @Override
    public Professeur save(Professeur professeur) {
        professeur.getUser().setPassword(String.valueOf(professeur.getSomme()));
        AppUser user=this.accountService.saveUser(professeur.getUser());
        user.setAuthorities(new ArrayList<>());
        user.setEnabled(true);
        accountService.addAuthorityToUser(user.getId(), AuthoritiesConstants.USER);
        accountService.addAuthorityToUser(user.getId(), AuthoritiesConstants.TEACHER);
        professeur.setUser(user);
        return this.professeurRepository.save(professeur);
    }

    @Override
    public Professeur update(Professeur professeur) {
        Optional<AppUser> optionalUser = userRepository.findById(professeur.getUser().getId());
        optionalUser.ifPresent(user -> {
            try {
                System.out.println("#############################\n"+user);
                nullAwareBeanUtilsBean.copyProperties(user, professeur.getUser());
                System.out.println("#############################\n"+user);
                professeur.setUser(userRepository.save(user));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        });
        return professeurRepository.save(professeur);
    }

    @Override
    public void importProfesseurs(ImportProfesseurList list) {
        for (ExcelUserRow row:list.getRows()){
            Professeur professeur=new Professeur();
            AppUser appUser=new AppUser(row.getUsername(),String.valueOf(row.getCneOrSomme()),row.getFirstName(),row.getLastName(),row.getTel());
            appUser.setEnabled(true);
            appUser=accountService.saveUser(appUser);
            accountService.addAuthorityToUser(appUser.getId(), AuthoritiesConstants.USER);
            accountService.addAuthorityToUser(appUser.getId(), AuthoritiesConstants.TEACHER);
            professeur.setUser(appUser);
            professeur.setDepartement(list.getDepartement());
            professeur.setSomme(row.getCneOrSomme());
            this.professeurRepository.save(professeur);
        }
    }
}
