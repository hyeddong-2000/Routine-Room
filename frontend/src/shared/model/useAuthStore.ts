import { create } from 'zustand'
import { persist } from 'zustand/middleware'
import type { UserResponseDTO } from '@/entities/users/model/user.types'

interface AuthState {
  accessToken: string | null
  user: UserResponseDTO.LoginInfo | null
  setAuth: (user: UserResponseDTO.LoginInfo) => void
  clearAuth: () => void
  isAuthenticated: () => boolean
}

const useAuthStore = create<AuthState>()(
  persist(
    (set, get) => ({
      accessToken: null,
      user: null,
      setAuth: (user) => set({ accessToken: user.accessToken, user }),
      clearAuth: () => set({ accessToken: null, user: null }),
      isAuthenticated: () => get().accessToken !== null,
    }),
    {
      name: 'routine-room-auth',
      partialize: (state) => ({ accessToken: state.accessToken, user: state.user }),
    }
  )
)

export default useAuthStore
