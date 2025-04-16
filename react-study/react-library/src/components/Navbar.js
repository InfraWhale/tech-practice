import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Navbar = ({ authenticate, setAuthenticate }) => {
    const [menuOpen, setMenuOpen] = useState(false);
    const navigate = useNavigate();

    const goToMain = () => {
        navigate('/');
    };

    const goToMybook = () => {
        navigate('/mybook');
    };

    const goToLogin = () => {
        navigate('/login');
    };

    return (
        <div className="navbar">
            <h3> 코딩알려주는 누나 도서관 </h3>
            <div className="button-box">
                <div className="main-button" onClick={goToMain}>
                    메인
                </div>

                <div className="my-book-button" onClick={goToMybook}>
                    나의 책
                </div>

                <div className="login-button" onClick={goToLogin}>
                    로그인
                    {/* <div>{authenticate ? '로그아웃' : '로그인'}</div> */}
                </div>
            </div>
        </div>
    );
};

export default Navbar;
