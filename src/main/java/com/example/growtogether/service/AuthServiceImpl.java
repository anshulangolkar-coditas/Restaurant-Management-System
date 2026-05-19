package com.example.growtogether.service;

import com.example.growtogether.constants.ExceptionMessages;
import com.example.growtogether.constants.InvitationStatus;
import com.example.growtogether.constants.Role;
import com.example.growtogether.dto.auth.request.RegenerateAccessTokenDto;
import com.example.growtogether.dto.auth.request.RegisterUserRequestDto;
import com.example.growtogether.dto.auth.request.UserLoginRequestDto;
import com.example.growtogether.dto.auth.response.RegenerateAccessTokenResponse;
import com.example.growtogether.dto.auth.response.RegisterUserResponseDto;
import com.example.growtogether.dto.auth.response.UserLoginResponseDto;
import com.example.growtogether.dtomapping.auth.AuthMapping;
import com.example.growtogether.entity.*;
import com.example.growtogether.exception.*;
import com.example.growtogether.repository.*;
import com.example.growtogether.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthMapping authMapping;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final InvitationRepository invitationRepository;
    private final OwnerRepository ownerRepository;
    private final ManagerRepository managerRepository;
    private final StaffRepository staffRepository;

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto request) {

        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND);
        }

        Users user = (Users) authentication.getPrincipal();

        String accessToken = jwtUtil.generateToken(request.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user);

        return authMapping.loginToDto(accessToken,refreshToken);
    }

    @Override
    public RegenerateAccessTokenResponse regenerateAccessToken(RegenerateAccessTokenDto refreshToken) {

        String accessToken = null;

        String refresh = authMapping.toRefreshToken(refreshToken);

        RefreshToken token = refreshTokenRepository.findByToken(refresh)
                .orElseThrow(() -> new RefreshTokenNotValidException(ExceptionMessages.REFRESH_TOKEN_NOT_VALID));

        Users user = token.getUser();

        if(jwtUtil.isRefreshTokenValid(token)){
            accessToken = jwtUtil.generateToken(user.getEmailId());
        }
        return authMapping.responseRefreshToken(accessToken,token.getToken());
    }

    @Override
    public RegisterUserResponseDto registerUser(RegisterUserRequestDto request) {

        Invitation invitation = invitationRepository.findByUniqueKey(request.getUniqueKey())
                .orElseThrow(() -> new InvitationNotFoundException(ExceptionMessages.INVITATION_NOT_FOUND));

        if(invitation.getExpiresOn().before(new Date())){
            throw new InvitationExpiredException(ExceptionMessages.INVITATION_EXPIRED);
        }else if(!invitation.getEmailId().equalsIgnoreCase(request.getEmail())){
            throw new InvitationNotFoundException(ExceptionMessages.INVITATION_NOT_FOUND);
        }else if(invitation.getStatus().equals(InvitationStatus.ACCEPTED)){
            throw new InvitationAlreadyAcceptedException(ExceptionMessages.INVITATION_ACCEPTED);
        }else if(invitation.getStatus().equals(InvitationStatus.CANCELLED)){
            throw new InvitationNotFoundException(ExceptionMessages.INVITATION_NOT_FOUND);
        }

        Users owner = null;
        Users manager = null;
        Users staff = null;

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        if(invitation.getRole().equals(Role.OWNER)){

            owner = authMapping.registerUserDtoToEntity(request);
            owner.setRole((new HashSet<>(Arrays.asList(Role.OWNER,Role.MANAGER, Role.STAFF_WAITER,Role.STAFF_KITCHEN))));

            Users savedOwner = usersRepository.save(owner);

            Owner owner1 = new Owner();
            owner1.setUser(savedOwner);

            ownerRepository.save(owner1);
            invitation.setStatus(InvitationStatus.ACCEPTED);
            invitationRepository.save(invitation);

            return authMapping.registerUserResponseDto(savedOwner);
        }
        if(invitation.getRole().equals(Role.MANAGER)){

            manager = authMapping.registerUserDtoToEntity(request);
            manager.setRole((new HashSet<>(Arrays.asList(Role.MANAGER, Role.STAFF_WAITER,Role.STAFF_KITCHEN))));

            Users savedManager = usersRepository.save(manager);

            Manager manager1 = new Manager();
            manager1.setUser(savedManager);

            invitation.setStatus(InvitationStatus.ACCEPTED);
            invitationRepository.save(invitation);

            return authMapping.registerUserResponseDto(savedManager);
        }
        if(invitation.getRole().equals(Role.STAFF_WAITER)){

            staff = authMapping.registerUserDtoToEntity(request);
            staff.setRole((new HashSet<>(List.of(Role.STAFF_WAITER))));

            Users savedWaiterStaff = usersRepository.save(staff);

            Staff staff1 = new Staff();
            staff1.setUser(savedWaiterStaff);

            invitation.setStatus(InvitationStatus.ACCEPTED);
            invitationRepository.save(invitation);

            return authMapping.registerUserResponseDto(savedWaiterStaff);
        }
        if(invitation.getRole().equals(Role.STAFF_KITCHEN)){

            staff = authMapping.registerUserDtoToEntity(request);
            staff.setRole((new HashSet<>(List.of(Role.STAFF_KITCHEN))));

            Users savedWaiterStaff = usersRepository.save(staff);

            Staff staff1 = new Staff();
            staff1.setUser(savedWaiterStaff);

            invitation.setStatus(InvitationStatus.ACCEPTED);
            invitationRepository.save(invitation);

            return authMapping.registerUserResponseDto(savedWaiterStaff);
        }

        return null;
    }


}
