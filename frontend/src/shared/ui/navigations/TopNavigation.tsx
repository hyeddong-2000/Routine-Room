import { useNavigate, useLocation } from 'react-router-dom'
import useMenuStore from '@/shared/lib/useMenuStore'
import useAuthStore from '@/shared/model/useAuthStore'
import type { MenuItem } from '@/shared/config/menu.types'

const NAV_ITEMS: { key: MenuItem; label: string; path: string }[] = [
  { key: 'dashboard', label: 'Dashboard', path: '/dashboard' },
  { key: 'kanban', label: 'Kanban', path: '/kanban' },
  { key: 'room', label: 'Room', path: '/room' },
]

function TopNavigation() {
  const navigate = useNavigate()
  const { pathname } = useLocation()
  const setActiveMenu = useMenuStore((state) => state.setActiveMenu)
  const { user, clearAuth } = useAuthStore()

  const handleNavigate = (item: (typeof NAV_ITEMS)[number]) => {
    setActiveMenu(item.key)
    navigate(item.path)
  }

  const handleLogout = () => {
    clearAuth()
    navigate('/login', { replace: true })
  }

  const isActive = (item: (typeof NAV_ITEMS)[number]) =>
    pathname === item.path || (pathname === '/' && item.key === 'dashboard')

  return (
    <header className="h-14 bg-white border-b flex items-center px-6 gap-8 shrink-0 shadow-sm">
      <span className="font-bold text-lg text-violet-600 tracking-tight">Routine Room</span>
      <nav className="flex gap-1 flex-1">
        {NAV_ITEMS.map((item) => (
          <button
            key={item.key}
            type="button"
            onClick={() => handleNavigate(item)}
            className={`text-sm font-medium px-4 py-1.5 rounded-md transition-colors ${
              isActive(item)
                ? 'bg-violet-100 text-violet-700'
                : 'text-gray-500 hover:text-violet-600 hover:bg-gray-50'
            }`}
          >
            {item.label}
          </button>
        ))}
      </nav>
      <div className="flex items-center gap-3">
        <span className="text-sm text-gray-500">{user?.nickname}</span>
        <button
          type="button"
          onClick={handleLogout}
          className="text-sm text-gray-400 hover:text-gray-600 transition-colors"
        >
          로그아웃
        </button>
      </div>
    </header>
  )
}

export default TopNavigation
