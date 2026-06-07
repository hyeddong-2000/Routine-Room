import type { ReactNode } from 'react'
import { useNavigate, useLocation } from 'react-router-dom'
import { MdDashboard, MdViewKanban, MdMeetingRoom, MdChat } from 'react-icons/md'
import useMenuStore from '../../stores/useMenuStore'
import type { MenuItem } from '../../types/menu.types'

interface TabItem {
  key: MenuItem
  label: string
  path: string
  icon: ReactNode
}

const TAB_ITEMS: TabItem[] = [
  { key: 'dashboard', label: 'Dashboard', path: '/dashboard', icon: <MdDashboard size={22} /> },
  { key: 'kanban', label: 'Kanban', path: '/kanban', icon: <MdViewKanban size={22} /> },
  { key: 'room', label: 'Room', path: '/room', icon: <MdMeetingRoom size={22} /> },
  { key: 'chat', label: 'Chat', path: '/chat', icon: <MdChat size={22} /> },
]

function BottomTabBar() {
  const navigate = useNavigate()
  const { pathname } = useLocation()
  const setActiveMenu = useMenuStore((state) => state.setActiveMenu)

  const handleTabPress = (item: TabItem) => {
    setActiveMenu(item.key)
    navigate(item.path)
  }

  const isActive = (item: TabItem) =>
    pathname === item.path || (pathname === '/' && item.key === 'dashboard')

  return (
    <nav className="fixed bottom-0 left-0 right-0 h-16 bg-white border-t flex items-center safe-area-pb">
      {TAB_ITEMS.map((item) => (
        <button
          key={item.key}
          type="button"
          onClick={() => handleTabPress(item)}
          className={`flex-1 flex flex-col items-center justify-center gap-0.5 transition-colors h-full ${
            isActive(item) ? 'text-violet-600' : 'text-gray-400 active:text-violet-400'
          }`}
        >
          {item.icon}
          <span className="text-[10px] font-medium">{item.label}</span>
        </button>
      ))}
    </nav>
  )
}

export default BottomTabBar
