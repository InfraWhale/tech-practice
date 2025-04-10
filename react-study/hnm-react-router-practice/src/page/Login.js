import React, { useState } from 'react';
import '../css/Login.css';
import { Button, Container, Form } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

const Login = ({ setAuthenticate }) => {
  const navigate = useNavigate();
  const [showPassword, setShowPassword] = useState(false); // 비밀번호 보기 토글

  const loginUser = (event) => {
    event.preventDefault();
    setAuthenticate(true);
    navigate('/');
  };

  return (
    <div className="login-wrapper">
      <div className="login-box">
        <h1>로그인</h1>
        <Form onSubmit={loginUser}>
          {/* 이메일 */}
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>이메일 <span className="required">*</span></Form.Label>
            <Form.Control type="email" placeholder="email" className="custom-input" required />
          </Form.Group>

          {/* 비밀번호 */}
          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>비밀번호 <span className="required">*</span></Form.Label>
            <div className="password-wrapper">
              <Form.Control
                type={showPassword ? "text" : "password"}
                placeholder="Password"
                className="custom-input"
                required
              />
              <button
                type="button"
                className="show-button"
                onClick={() => setShowPassword(!showPassword)}
              >
                {showPassword ? "HIDE" : "SHOW"}
              </button>
            </div>
          </Form.Group>

          {/* 옵션 */}
          <Form.Group className="mb-3 d-flex justify-content-between align-items-center options">
            <Form.Check type="checkbox" label="로그인 상태 유지" />
            <a href="#">비밀번호를 잊으셨나요?</a>
          </Form.Group>

          {/* 버튼 */}
          <Button type="submit" className="login-btn w-100">
            로그인
          </Button>
          <Button type="button" className="signup-btn w-100 mt-2">
            회원 가입하기
          </Button>
        </Form>
      </div>
    </div>
  );
};

export default Login;
