package com.routineroom.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestDTO {

    @Getter
    @NoArgsConstructor
    public static class SignUp {
        @NotBlank(message = "아이디는 필수입니다.")
        @Size(min = 3, max = 20, message = "아이디는 3~20자여야 합니다.")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "아이디는 영문, 숫자, 언더스코어만 사용 가능합니다.")
        private String id;

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Pattern(
            regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,30}$",
            message = "비밀번호는 영문, 숫자를 포함하여 8~30자여야 합니다."
        )
        private String password;

        @NotBlank(message = "닉네임은 필수입니다.")
        @Size(min = 2, max = 15, message = "닉네임은 2~15자여야 합니다.")
        private String nickname;
    }

    @Getter
    @NoArgsConstructor
    public static class Login {
        @NotBlank(message = "아이디는 필수입니다.")
        private String id;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;
    }

    @Getter
    @NoArgsConstructor
    public static class UpdateProfile {
        @Size(min = 2, max = 15, message = "닉네임은 2~15자여야 합니다.")
        private String nickname;

        private String bgmUseYn;
    }
}
