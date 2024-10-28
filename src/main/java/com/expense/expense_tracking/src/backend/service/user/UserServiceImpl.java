package com.expense.expense_tracking.src.backend.service.user;

import com.expense.expense_tracking.src.backend.data.user.User;
import com.expense.expense_tracking.src.backend.data.user.UserBalance;
import com.expense.expense_tracking.src.backend.model.user.*;
import com.expense.expense_tracking.src.app.common.enums.ApiErrorCode;
import com.expense.expense_tracking.src.app.common.utils.StringUtil;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Value("${security.jwt.secret-key}")
    private String jwtSecretKey;
    @Value("${security.jwt.issuer}")
    private String jwtIssuer;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserBalanceRepository userBalanceRepository;
    @Override
    public UserResponse login(UserRequest request) {
        UserResponse.Builder responseBuilder = UserResponse.newBuilder();

        if (StringUtil.isNullEmpty(request.getUser().getEmailAddress())) {
            responseBuilder.withAddError(ApiErrorCode.USER_EMAIL_EMPTY);
        }

        String password = request.getUser().getPassword();
        if (StringUtil.isNullEmpty(password) || password.length() < 8) {
            responseBuilder.withAddError(ApiErrorCode.PASSWORD_INVALID);
        }
        if (responseBuilder.hasError()) {
            return responseBuilder.build();
        }
        User user = null;
        try {
            Optional<User> loginUser = userRepository.
                    findByEmailAddressAndPassword(request.getUser().getEmailAddress(),password);
            if (! loginUser.isPresent()) {
                responseBuilder.withAddError(ApiErrorCode.UNABLE_TO_LOGIN);
                return responseBuilder.build();
            }
            user = loginUser.get();
        } catch (Exception e) {
            responseBuilder.withAddError(ApiErrorCode.RUNTIME_ERROR);
        }
        UserDto newUser = UserDto.builder()
                .emailAddress(user.getEmailAddress())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .build();
        String jwtToken = createJwtToken(user);
        newUser.setAuthToken(jwtToken);
        responseBuilder.withUser(newUser);
        return responseBuilder.build();
    }

    @Override
    public UserResponse signup(UserRequest request) {
        UserResponse.Builder responseBuilder = UserResponse.newBuilder();
        User user = null;
        if (!responseBuilder.hasError()) {
            try {
                Optional<User> existingUser =
                        userRepository.findByEmailAddress(request.getUser().getEmailAddress());
                if (existingUser.isPresent()) {
                    responseBuilder.withAddError(ApiErrorCode.EMAIL_ALREADY_REGISTERED);
                } else {
                    // create a user entity
                    user = userRepository.save(User.fromUserDto(request.getUser()));

                    // create new user balance
                    UserBalance userBalance = UserBalance.builder()
                            .user(user)
                            .amount(0.0)
                            .build();
                    userBalanceRepository.save(userBalance);

                    UserDto userDto = User.toUserDto(user);
                    String jwtToken = createJwtToken(user);
                    userDto.setAuthToken(jwtToken);

                    responseBuilder.withUser(userDto);
                }
            } catch (Exception e) {
                responseBuilder.withAddError(ApiErrorCode.RUNTIME_ERROR);
            }
        }
        return responseBuilder.build();
    }

    @Override
    public UserResponse userProfile(UserRequest request) {
        UserResponse.Builder responseBuilder = UserResponse.newBuilder();
        //TODO: User Profile API isn't needed for now
        responseBuilder.withAddError(ApiErrorCode.RUNTIME_ERROR);

        return responseBuilder.build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<User> existingUser = userRepository.findByEmailAddress(username);
            if (existingUser.isPresent()) {
                return org.springframework.security.core.userdetails.User
                        .withUsername(existingUser.get().getEmailAddress())
                        .password(existingUser.get().getPassword())
                        .build();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    private String createJwtToken(User user) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtIssuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(24 * 3600))
                .subject(user.getEmailAddress())
                .build();
        var encoder = new NimbusJwtEncoder( new ImmutableSecret<>(jwtSecretKey.getBytes()));
        var params = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(),claims);
        return encoder.encode(params).getTokenValue();
    }
}
