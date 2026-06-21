export namespace UserRequestDTO {
  export interface SignUp {
    id: string
    password: string
    nickname: string
  }

  export interface Login {
    id: string
    password: string
  }
}

export namespace UserResponseDTO {
  export interface LoginInfo {
    userNo: number
    id: string
    nickname: string
    authority: string
    accessToken: string
  }

  export interface Profile {
    userNo: number
    id: string
    nickname: string
    profileFileId: number | null
    bgmUseYn: string
    todayCnt: number
    totalCnt: number
  }
}
