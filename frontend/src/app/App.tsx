import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { QueryClientProvider } from '@tanstack/react-query'
import { ReactQueryDevtools } from '@tanstack/react-query-devtools'
import { Toaster } from 'sonner'
import queryClient from './providers/queryClient'
import DesktopLayout from './layouts/DesktopLayout'
import MobileLayout from './layouts/MobileLayout'
import ProtectedRoute from './routes/ProtectedRoute'
import LoginPage from '@/pages/auth/ui/LoginPage'
import SignupPage from '@/pages/auth/ui/SignupPage'
import useBreakpoint from '@/shared/lib/useBreakpoint'

function App() {
  const isDesktop = useBreakpoint('lg')

  return (
    <QueryClientProvider client={queryClient}>
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<SignupPage />} />

          <Route element={<ProtectedRoute />}>
            <Route path="/*" element={isDesktop ? <DesktopLayout /> : <MobileLayout />} />
          </Route>

          <Route path="*" element={<Navigate to="/login" replace />} />
        </Routes>
      </BrowserRouter>
      <Toaster position="top-center" richColors duration={3000} />
      <ReactQueryDevtools initialIsOpen={false} />
    </QueryClientProvider>
  )
}

export default App
