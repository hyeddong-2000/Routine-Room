import { Routes, Route, Navigate } from 'react-router-dom'
import BottomTabBar from '../navigations/BottomTabBar'
import DashboardPage from '../../pages/Dashboard'
import KanbanPage from '../../pages/Kanban'
import RoomPage from '../../pages/Room'
import ChatPage from '../../pages/Chat'

function MobileLayout() {
  return (
    <div className="flex flex-col h-dvh bg-gray-50">
      <main className="flex-1 overflow-y-auto pb-16">
        <Routes>
          <Route index element={<Navigate to="/dashboard" replace />} />
          <Route path="/dashboard" element={<DashboardPage />} />
          <Route path="/kanban" element={<KanbanPage />} />
          <Route path="/room" element={<RoomPage />} />
          <Route path="/chat" element={<ChatPage />} />
        </Routes>
      </main>
      <BottomTabBar />
    </div>
  )
}

export default MobileLayout
