import { Routes, Route, Navigate } from 'react-router-dom'
import TopNavigation from '../navigations/TopNavigation'
import ChatPanel from '../shared/ChatPanel'
import DashboardPage from '../../pages/Dashboard'
import KanbanPage from '../../pages/Kanban'
import RoomPage from '../../pages/Room'

function DesktopLayout() {
  return (
    <div className="flex flex-col h-screen bg-gray-50">
      <TopNavigation />
      <div className="flex flex-1 overflow-hidden">
        <main className="flex-1 overflow-y-auto">
          <Routes>
            <Route index element={<Navigate to="/dashboard" replace />} />
            <Route path="/dashboard" element={<DashboardPage />} />
            <Route path="/kanban" element={<KanbanPage />} />
            <Route path="/room" element={<RoomPage />} />
          </Routes>
        </main>
        <ChatPanel />
      </div>
    </div>
  )
}

export default DesktopLayout
