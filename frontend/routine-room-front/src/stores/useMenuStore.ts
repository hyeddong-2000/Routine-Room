import { create } from 'zustand'
import type { MenuItem, MenuState } from '../types/menu.types'

interface MenuStore extends MenuState {
  setActiveMenu: (menu: MenuItem) => void
  setMobileCurrentView: (view: MenuItem) => void
}

const useMenuStore = create<MenuStore>((set) => ({
  activeMenu: 'dashboard',
  mobileCurrentView: 'dashboard',
  setActiveMenu: (menu) => set({ activeMenu: menu }),
  setMobileCurrentView: (view) => set({ mobileCurrentView: view }),
}))

export default useMenuStore
