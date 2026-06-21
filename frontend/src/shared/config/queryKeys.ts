export const queryKeys = {
  users: {
    me: () => ['users', 'me'] as const,
    profile: (userNo: number) => ['users', 'profile', userNo] as const,
  },
  tasks: {
    list: (userId: number) => ['tasks', 'list', userId] as const,
    detail: (taskId: number) => ['tasks', 'detail', taskId] as const,
  },
  routines: {
    list: (userNo: number) => ['routines', 'list', userNo] as const,
  },
  evaluations: {
    daily: (userNo: number, date: string) => ['evaluations', 'daily', userNo, date] as const,
  },
} as const
