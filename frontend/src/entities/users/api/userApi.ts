import apiClient, { type ApiResponse } from '@/shared/api/apiClient'
import type { UserRequestDTO, UserResponseDTO } from '@/entities/users/model/user.types'

export const userApi = {
  register: (body: UserRequestDTO.SignUp) =>
    apiClient.post<ApiResponse<null>>('/auth/register', body),

  login: (body: UserRequestDTO.Login) =>
    apiClient.post<ApiResponse<UserResponseDTO.LoginInfo>>('/auth/login', body),

  getMyInfo: () =>
    apiClient.get<ApiResponse<UserResponseDTO.Profile>>('/auth/me'),
}
