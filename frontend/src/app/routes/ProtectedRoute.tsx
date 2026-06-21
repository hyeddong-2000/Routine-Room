import { Navigate, Outlet } from 'react-router-dom'
import useAuthStore from '@/shared/model/useAuthStore'

function ProtectedRoute() {
  const isAuthenticated = useAuthStore((state) => state.isAuthenticated())

  if (!isAuthenticated) {
    return <Navigate to="/login" replace />
  }

  return <Outlet />
}

export default ProtectedRoute
