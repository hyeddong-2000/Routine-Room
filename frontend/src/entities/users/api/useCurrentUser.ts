import { useQuery } from '@tanstack/react-query'
import { userApi } from './userApi'
import { queryKeys } from '@/shared/config/queryKeys'
import useAuthStore from '@/shared/model/useAuthStore'

export function useCurrentUser() {
  const isAuthenticated = useAuthStore((state) => state.isAuthenticated())

  return useQuery({
    queryKey: queryKeys.users.me(),
    queryFn: () => userApi.getMyInfo().then((res) => res.data.data),
    enabled: isAuthenticated,
    staleTime: 1000 * 60 * 5, // 5분 - 유저 정보는 잘 안 바뀜
  })
}
