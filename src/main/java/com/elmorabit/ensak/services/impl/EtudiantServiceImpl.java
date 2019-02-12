package com.elmorabit.ensak.services.impl;

import com.elmorabit.ensak.domain.AppUser;
import com.elmorabit.ensak.domain.Etudiant;
import com.elmorabit.ensak.dto.ExcelUserRow;
import com.elmorabit.ensak.dto.ImportEtudiantList;
import com.elmorabit.ensak.repository.AuthorityRepository;
import com.elmorabit.ensak.repository.EtudiantRepository;
import com.elmorabit.ensak.repository.UserRepository;
import com.elmorabit.ensak.security.AuthoritiesConstants;
import com.elmorabit.ensak.services.AccountService;
import com.elmorabit.ensak.services.EtudiantService;
import com.elmorabit.ensak.services.util.NullAwareBeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Optional;


@Service
@Transactional
public class EtudiantServiceImpl implements EtudiantService {

    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    AccountService accountService;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    NullAwareBeanUtilsBean nullAwareBeanUtilsBean;

    @Override
    public Etudiant save(Etudiant etudiant) {
        etudiant.getUser().setPassword(String.valueOf(etudiant.getCne()));
        AppUser user=this.accountService.saveUser(etudiant.getUser());
        user.setAuthorities(new ArrayList<>());
        user.setEnabled(true);
        accountService.addAuthorityToUser(user.getId(), AuthoritiesConstants.USER);
        accountService.addAuthorityToUser(user.getId(), AuthoritiesConstants.STUDENT);
        etudiant.setUser(user);
        return this.etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant update(Etudiant etudiant) {
        Optional<AppUser> optionalUser = userRepository.findById(etudiant.getUser().getId());
        optionalUser.ifPresent(user -> {
            try {
                nullAwareBeanUtilsBean.copyProperties(user, etudiant.getUser());
                etudiant.setUser(userRepository.save(user));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        });
        return etudiantRepository.save(etudiant);
    }

    @Override
    public void importEtudiants(ImportEtudiantList list) {
        for (ExcelUserRow row:list.getRows()){
            Etudiant etudiant=new Etudiant();
            AppUser appUser=new AppUser(row.getUsername(),String.valueOf(row.getCneOrSomme()),row.getFirstName(),row.getLastName(),row.getTel());
            appUser.setEnabled(true);
            appUser=accountService.saveUser(appUser);
            accountService.addAuthorityToUser(appUser.getId(), AuthoritiesConstants.USER);
            accountService.addAuthorityToUser(appUser.getId(), AuthoritiesConstants.STUDENT);
            etudiant.setUser(appUser);
            etudiant.setNiveau(list.getNiveau());
            etudiant.setCne(row.getCneOrSomme());
            this.etudiantRepository.save(etudiant);
        }
    }
}
