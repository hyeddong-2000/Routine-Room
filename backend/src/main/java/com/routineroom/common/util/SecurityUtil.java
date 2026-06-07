package com.routineroom.common.util;

import com.routineroom.common.common.CommonApiException;
import com.routineroom.common.common.ErrorCode;
import com.routineroom.common.security.UserAuthenticationDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtil {

    private SecurityUtil() {}

    public static String getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public static Integer getCurrentUserNo() {
        return getCurrentUser().getUserNo();
    }

    public static UserAuthenticationDTO getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof UserAuthenticationDTO user)) {
            throw new CommonApiException(ErrorCode.UNAUTHORIZED);
        }
        return user;
    }
}
