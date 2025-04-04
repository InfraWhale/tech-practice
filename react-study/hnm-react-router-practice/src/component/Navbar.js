import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faUser } from '@fortawesome/free-regular-svg-icons'
import { faSearch } from '@fortawesome/free-solid-svg-icons'
import '../css/Navbar.css'
import { useNavigate } from 'react-router-dom';

const Navbar = () => {
    const menuList = ['여성', 'Divided', '남성', '신생아/유아', '아동', 'H&M Home', 'Sale', '지속가능성' ]

    const navigate = useNavigate();

    const goToLogin = () => {
        navigate('/login')
    }
  return (
    <div>
        <div>
            <div className='login-button' onClick={goToLogin}>
                <FontAwesomeIcon icon={faUser} />
                <div>로그인</div>
            </div>
        </div>
        <div>
            <div className='nav-section'>
                <img width={100} src='https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/H%26M-Logo.svg/800px-H%26M-Logo.svg.png' onClick={() => navigate('/')} />
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
                    <input type="text" placeholder="검색어를 입력하세요" />
                </div>
            </div>
        </div>

    </div>
  )
}

export default Navbar