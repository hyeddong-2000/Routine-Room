import { useNavigate, useLocation } from 'react-router-dom'
import useMenuStore from '../../stores/useMenuStore'
import type { MenuItem } from '../../types/menu.types'

const NAV_ITEMS: { key: MenuItem; label: string; path: string }[] = [
  { key: 'dashboard', label: 'Dashboard', path: '/dashboard' },
  { key: 'kanban', label: 'Kanban', path: '/kanban' },
  { key: 'room', label: 'Room', path: '/room' },
]

function TopNavigation() {
  const navigate = useNavigate()
  const { pathname } = useLocation()
  const setActiveMenu = useMenuStore((state) => state.setActiveMenu)

  const handleNavigate = (item: (typeof NAV_ITEMS)[number]) => {
    setActiveMenu(item.key)
    navigate(item.path)
  }

  const isActive = (item: (typeof NAV_ITEMS)[number]) =>
    pathname === item.path || (pathname === '/' && item.key === 'dashboard')

  return (
    <header className="h-14 bg-white border-b flex items-center px-6 gap-8 shrink-0 shadow-sm">
      <span className="font-bold text-lg text-violet-600 tracking-tight">Routine Room</span>
      <nav className="flex gap-1">
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
    </header>
  )
}

export default TopNavigation
