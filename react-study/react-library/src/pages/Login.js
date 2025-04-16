import React from 'react'

const Login = () => {

  return (
    <div className='login'>
      <div className='login-box'>
        <h2>환영합니다</h2>
        <form>
          <input placeholder='이메일 주소'></input>
          <input placeholder='비밀번호'></input>
          <div>비밀번호를 잊으셨나요?</div>
          <button>로그인</button>
          <div>계정이 없으신가요? 회원가입</div>
        </form>
      </div>
    </div>
  )
}

export default Login