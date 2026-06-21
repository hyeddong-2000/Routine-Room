import { useState, type FormEvent } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { MdVisibility, MdVisibilityOff, MdCheck, MdClose } from 'react-icons/md'
import { toast } from 'sonner'
import type { AxiosError } from 'axios'
import { userApi } from '@/entities/users/api/userApi'
import type { ApiResponse } from '@/shared/api/apiClient'

interface FormState {
  id: string
  password: string
  passwordConfirm: string
  nickname: string
}

const PW_RULES = [
  { label: '8자 이상', test: (pw: string) => pw.length >= 8 },
  { label: '영문 포함', test: (pw: string) => /[a-zA-Z]/.test(pw) },
  { label: '숫자 포함', test: (pw: string) => /[0-9]/.test(pw) },
]

const strengthConfig = [
  { label: '약함', barClass: 'w-1/3 bg-red-400' },
  { label: '약함', barClass: 'w-1/3 bg-red-400' },
  { label: '보통', barClass: 'w-2/3 bg-yellow-400' },
  { label: '강함', barClass: 'w-full bg-green-500' },
]

function SignupPage() {
  const navigate = useNavigate()

  const [form, setForm] = useState<FormState>({ id: '', password: '', passwordConfirm: '', nickname: '' })
  const [showPw, setShowPw] = useState(false)
  const [showPwConfirm, setShowPwConfirm] = useState(false)
  const [isLoading, setIsLoading] = useState(false)

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }))
  }

  const pwResults = PW_RULES.map((r) => ({ ...r, valid: r.test(form.password) }))
  const passedCount = pwResults.filter((r) => r.valid).length
  const strength = strengthConfig[passedCount]

  const pwConfirmStatus =
    form.passwordConfirm.length === 0
      ? null
      : form.password === form.passwordConfirm
        ? 'match'
        : 'mismatch'

  const validate = (): string | null => {
    if (!/^[a-zA-Z0-9_]{3,20}$/.test(form.id))
      return '아이디는 3~20자 영문, 숫자, 언더스코어만 사용 가능합니다.'
    if (passedCount < 3)
      return '비밀번호는 영문, 숫자를 포함하여 8자 이상이어야 합니다.'
    if (pwConfirmStatus !== 'match')
      return '비밀번호가 일치하지 않습니다.'
    if (form.nickname.length < 2 || form.nickname.length > 15)
      return '닉네임은 2~15자여야 합니다.'
    return null
  }

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault()
    const validationError = validate()
    if (validationError) {
      toast.error(validationError)
      return
    }

    setIsLoading(true)
    try {
      await userApi.register({ id: form.id, password: form.password, nickname: form.nickname })
      toast.success('회원가입이 완료되었습니다! 로그인해주세요.')
      navigate('/login', { replace: true })
    } catch (err) {
      const axiosErr = err as AxiosError<ApiResponse>
      toast.error(axiosErr.response?.data?.message ?? '회원가입에 실패했습니다.')
    } finally {
      setIsLoading(false)
    }
  }

  const inputClass = 'w-full border border-gray-200 rounded-lg px-4 py-2.5 text-sm outline-none focus:ring-2 focus:ring-violet-300 focus:border-transparent transition'

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 px-4 py-8">
      <div className="w-full max-w-sm">
        <div className="text-center mb-8">
          <h1 className="text-3xl font-bold text-violet-600 tracking-tight">Routine Room</h1>
          <p className="mt-2 text-sm text-gray-500">갓생을 함께 만들어가요</p>
        </div>

        <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-8">
          <h2 className="text-xl font-semibold text-gray-800 mb-6">회원가입</h2>

          <form onSubmit={handleSubmit} className="space-y-4">
            {/* 아이디 */}
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">아이디</label>
              <input
                name="id"
                type="text"
                value={form.id}
                onChange={handleChange}
                placeholder="3~20자 영문, 숫자, _"
                className={inputClass}
                autoComplete="username"
              />
            </div>

            {/* 닉네임 */}
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">닉네임</label>
              <input
                name="nickname"
                type="text"
                value={form.nickname}
                onChange={handleChange}
                placeholder="2~15자"
                className={inputClass}
              />
            </div>

            {/* 비밀번호 */}
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">비밀번호</label>
              <div className="relative">
                <input
                  name="password"
                  type={showPw ? 'text' : 'password'}
                  value={form.password}
                  onChange={handleChange}
                  placeholder="영문, 숫자 포함 8자 이상"
                  className={`${inputClass} pr-10`}
                  autoComplete="new-password"
                />
                <button
                  type="button"
                  onClick={() => setShowPw((v) => !v)}
                  className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400 hover:text-gray-600"
                  tabIndex={-1}
                >
                  {showPw ? <MdVisibilityOff size={18} /> : <MdVisibility size={18} />}
                </button>
              </div>

              {/* 비밀번호 강도 바 */}
              {form.password.length > 0 && (
                <div className="mt-2 space-y-1.5">
                  <div className="flex items-center gap-2">
                    <div className="flex-1 h-1.5 bg-gray-100 rounded-full overflow-hidden">
                      <div className={`h-full rounded-full transition-all duration-300 ${strength.barClass}`} />
                    </div>
                    <span className={`text-xs font-medium ${passedCount === 3 ? 'text-green-600' : passedCount === 2 ? 'text-yellow-600' : 'text-red-500'}`}>
                      {strength.label}
                    </span>
                  </div>
                  <div className="flex gap-3">
                    {pwResults.map((r) => (
                      <span key={r.label} className={`flex items-center gap-0.5 text-xs ${r.valid ? 'text-green-600' : 'text-gray-400'}`}>
                        {r.valid ? <MdCheck size={13} /> : <MdClose size={13} />}
                        {r.label}
                      </span>
                    ))}
                  </div>
                </div>
              )}
            </div>

            {/* 비밀번호 확인 */}
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">비밀번호 확인</label>
              <div className="relative">
                <input
                  name="passwordConfirm"
                  type={showPwConfirm ? 'text' : 'password'}
                  value={form.passwordConfirm}
                  onChange={handleChange}
                  placeholder="비밀번호를 다시 입력하세요"
                  className={`${inputClass} pr-10 ${pwConfirmStatus === 'mismatch' ? 'border-red-300 focus:ring-red-200' : pwConfirmStatus === 'match' ? 'border-green-300 focus:ring-green-200' : ''}`}
                  autoComplete="new-password"
                />
                <button
                  type="button"
                  onClick={() => setShowPwConfirm((v) => !v)}
                  className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400 hover:text-gray-600"
                  tabIndex={-1}
                >
                  {showPwConfirm ? <MdVisibilityOff size={18} /> : <MdVisibility size={18} />}
                </button>
              </div>
              {pwConfirmStatus === 'match' && (
                <p className="mt-1 text-xs text-green-600 flex items-center gap-0.5">
                  <MdCheck size={13} /> 비밀번호가 일치합니다.
                </p>
              )}
              {pwConfirmStatus === 'mismatch' && (
                <p className="mt-1 text-xs text-red-500 flex items-center gap-0.5">
                  <MdClose size={13} /> 비밀번호가 일치하지 않습니다.
                </p>
              )}
            </div>

            <button
              type="submit"
              disabled={isLoading}
              className="w-full bg-violet-600 text-white rounded-lg py-2.5 text-sm font-semibold hover:bg-violet-700 transition disabled:opacity-50 disabled:cursor-not-allowed"
            >
              {isLoading ? '처리 중...' : '회원가입'}
            </button>
          </form>

          <p className="mt-6 text-center text-sm text-gray-500">
            이미 계정이 있으신가요?{' '}
            <Link to="/login" className="text-violet-600 font-medium hover:underline">
              로그인
            </Link>
          </p>
        </div>
      </div>
    </div>
  )
}

export default SignupPage
