import { QueryClient } from '@tanstack/react-query'

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      staleTime: 1000 * 60 * 3,  // 3분간 fresh (재요청 없음)
      gcTime: 1000 * 60 * 10,    // 10분간 캐시 유지 후 GC
      retry: 1,
      refetchOnWindowFocus: false,
    },
  },
})

export default queryClient
