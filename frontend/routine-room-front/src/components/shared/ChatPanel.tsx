import { useState } from 'react'
import { MdChevronLeft, MdChevronRight, MdSend } from 'react-icons/md'

function ChatPanel() {
  const [isExpanded, setIsExpanded] = useState(true)
  const [inputValue, setInputValue] = useState('')

  const handleSend = () => {
    if (!inputValue.trim()) return
    setInputValue('')
  }

  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') handleSend()
  }

  return (
    <aside
      className={`bg-white border-l flex flex-col transition-all duration-300 shrink-0 ${
        isExpanded ? 'w-72' : 'w-12'
      }`}
    >
      <div className="flex items-center justify-between px-3 py-3 border-b shrink-0">
        {isExpanded && (
          <span className="font-semibold text-sm text-gray-700">Live Chat</span>
        )}
        <button
          type="button"
          onClick={() => setIsExpanded((prev) => !prev)}
          className="ml-auto p-1 rounded hover:bg-gray-100 text-gray-400 hover:text-gray-600"
        >
          {isExpanded ? <MdChevronRight size={18} /> : <MdChevronLeft size={18} />}
        </button>
      </div>

      {isExpanded && (
        <>
          <div className="flex-1 overflow-y-auto p-3 space-y-2" />
          <div className="p-3 border-t flex gap-2 shrink-0">
            <input
              type="text"
              value={inputValue}
              onChange={(e) => setInputValue(e.target.value)}
              onKeyDown={handleKeyDown}
              placeholder="메시지를 입력하세요"
              className="flex-1 text-sm border border-gray-200 rounded-lg px-3 py-2 outline-none focus:ring-2 focus:ring-violet-300 focus:border-transparent"
            />
            <button
              type="button"
              onClick={handleSend}
              className="p-2 bg-violet-600 text-white rounded-lg hover:bg-violet-700 transition-colors"
            >
              <MdSend size={16} />
            </button>
          </div>
        </>
      )}
    </aside>
  )
}

export default ChatPanel
