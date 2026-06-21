export type MenuItem = 'dashboard' | 'kanban' | 'room' | 'chat'

export interface MenuState {
  activeMenu: MenuItem
  mobileCurrentView: MenuItem
}
