package com.company.service;

import com.company.dto.ProfileDTO;
import com.company.dto.Registrationdto;
import com.company.dto.auth.AuthorizationDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exceptions.BadRequestException;
import com.company.repository.ProfileRepository;
import com.company.util.JwtUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO authorization(AuthorizationDTO dto) {
        String pswd=DigestUtils.md5Hex(dto.getPassword());
        Optional<ProfileEntity> optional = profileRepository
                .findByLoginAndPswd(dto.getLogin(), pswd);
        if (!optional.isPresent()) {
            throw new RuntimeException("Login or Password incorrect");
        }

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setName(optional.get().getName());
        profileDTO.setSurname(optional.get().getSurname());
        profileDTO.setJwt(JwtUtil.createJwt(optional.get().getId(), optional.get().getRole()));
        return profileDTO;
    }

    public void registration(Registrationdto dto){
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()){
            throw new BadRequestException("Email invalid");
        }
        Optional<ProfileEntity> optional1 = profileRepository.findByLogin(dto.getLogin());
        if (optional1.isPresent()){
            throw new BadRequestException("Login invalid");
        }
        String pswd= DigestUtils.md5Hex(dto.getPassword());
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setLogin(dto.getLogin());
        entity.setPswd(pswd);
        entity.setEmail(dto.getEmail());
        entity.setRole(ProfileRole.USER_ROLE);
        entity.setProfileStatus(ProfileStatus.CREATED);
        profileRepository.save(entity);
        StringBuilder builder=new StringBuilder();
        builder.append("Salom jigar");
        builder.append("http://localhost:8080/auth/verification/"+entity.getId());

    }

    public void verification(Integer id) {
    Optional<ProfileEntity>optional=profileRepository.findById(id);
    if (optional.isPresent()){
        optional.get().setProfileStatus(ProfileStatus.ACTIVE);
    }
    }
}
