import { useState, useEffect } from 'react'

const BREAKPOINTS = {
  sm: 640,
  md: 768,
  lg: 1024,
  xl: 1280,
} as const

type BreakpointKey = keyof typeof BREAKPOINTS

function useBreakpoint(breakpoint: BreakpointKey): boolean {
  const [isAbove, setIsAbove] = useState<boolean>(
    () => window.innerWidth >= BREAKPOINTS[breakpoint]
  )

  useEffect(() => {
    const mediaQuery = window.matchMedia(`(min-width: ${BREAKPOINTS[breakpoint]}px)`)
    setIsAbove(mediaQuery.matches)

    const handler = (event: MediaQueryListEvent) => setIsAbove(event.matches)
    mediaQuery.addEventListener('change', handler)

    return () => mediaQuery.removeEventListener('change', handler)
  }, [breakpoint])

  return isAbove
}

export default useBreakpoint
