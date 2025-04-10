import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faBars } from '@fortawesome/free-solid-svg-icons';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import '../css/Navbar.css';
import { useNavigate } from 'react-router-dom';
import mashopLogo from '../image/mashop.png';

const Navbar = ({ authenticate, setAuthenticate }) => {
    const menuList = ['광고상품', '남성복', '상남자관', 'Sale'];
    const [menuOpen, setMenuOpen] = useState(false);
    const navigate = useNavigate();

    const goToLogin = () => {
        navigate('/login');
    };

    const handleLogout = () => {
        setAuthenticate(false);
    };

    const search = (event) => {
        if (event.key === 'Enter') {
            let keyword = event.target.value;
            navigate(`/?q=${keyword}`);
        }
    };

    const toggleMenu = () => {
        setMenuOpen(!menuOpen);
    };

    const closeMenu = () => {
        setMenuOpen(false);
    };

    return (
        <div className="navbar-wrapper">
            {/* 햄버거 아이콘 */}
            <div className="hamburger-icon" onClick={toggleMenu}>
                <FontAwesomeIcon icon={faBars} size="2x" />
            </div>

            {/* 로그인 버튼 */}
            <div className="login-button" onClick={authenticate ? handleLogout : goToLogin}>
                <FontAwesomeIcon icon={faUser} />
                <div>{authenticate ? '로그아웃' : '로그인'}</div>
            </div>

            {/* 로고 */}
            <div className="nav-section">
                <img width={200} src={mashopLogo} onClick={() => navigate('/')} />
            </div>

            {/* PC 메뉴 */}
            <div className="menu-area">
                <div className="menu-left" />
                <ul className="menu-list">
                    {menuList.map((menu, idx) => (
                        <li key={idx}>{menu}</li>
                    ))}
                </ul>
                <div className="menu-right">
                    <div className="search-area">
                        <FontAwesomeIcon icon={faSearch} />
                        <input type="text" placeholder="검색어를 입력하세요" onKeyUp={search} />
                    </div>
                </div>
            </div>

            {/* 모바일 오버레이 */}
            {menuOpen && <div className="overlay" onClick={closeMenu}></div>}

            {/* 모바일 메뉴 */}
            <div className={`mobile-menu-slide ${menuOpen ? 'open' : ''}`}>
                <ul className="mobile-menu-list">
                    {menuList.map((menu, idx) => (
                        <li key={idx} onClick={closeMenu}>{menu}</li>
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default Navbar;
