import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faUser } from '@fortawesome/free-regular-svg-icons'
import { faSearch } from '@fortawesome/free-solid-svg-icons'
import '../css/Navbar.css'
import { useNavigate } from 'react-router-dom';
import mashopLogo from '../image/mashop.png';

const Navbar = ({ authenticate, setAuthenticate }) => {
    const menuList = ['광고상품', '남성복', '상남자관', 'Sale' ];

    const navigate = useNavigate();

    const goToLogin = () => {
        navigate('/login')
    }

    const handleLogout = () => {
        setAuthenticate(false);
    };

    const search = (event) => {
        if(event.key === "Enter") {
            // 입력한 검색어를 읽어와서
            let keyword = event.target.value;
            console.log("keyword : ", keyword);
            // url을 바꿔준다.
            navigate(`/?q=${keyword}`);
        }
    }
  return (
    <div>
        <div>
            <div className='login-button' onClick={authenticate ? handleLogout : goToLogin}>
                <FontAwesomeIcon icon={faUser} />
                <div>{authenticate ? '로그아웃' : '로그인'}</div>
            </div>
        </div>
        <div>
            <div className='nav-section'>
                <img width={200} src={mashopLogo} onClick={() => navigate('/')} />
            </div>
        </div>
        <div className='menu-area'>
            <div className="menu-left" />
            <ul className='menu-list'>
                {menuList.map((menu, idx) => <li key={idx}>{menu}</li>)}
            </ul>
            <div className="menu-right">
                <div className='search-area'>
                    <FontAwesomeIcon icon={faSearch}/>
                    <input type="text" placeholder="검색어를 입력하세요" onKeyUp={(event) => search(event)}/>
                </div>
            </div>
        </div>

    </div>
  )
}

export default Navbar