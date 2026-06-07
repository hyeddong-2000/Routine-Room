import { BrowserRouter } from 'react-router-dom'
import DesktopLayout from './components/layouts/DesktopLayout'
import MobileLayout from './components/layouts/MobileLayout'
import useBreakpoint from './hooks/useBreakpoint'

function App() {
  const isDesktop = useBreakpoint('lg')

  return (
    <BrowserRouter>
      {isDesktop ? <DesktopLayout /> : <MobileLayout />}
    </BrowserRouter>
  )
}

export default App
