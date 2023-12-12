import React, { useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import Logout from './LogoutComponent';
import Login from './LoginComponent';
import { AuthContext } from '../contexts/AuthContext'


const NavBar = () => {
    const navigate =  useNavigate();
    const { isLoggedIn, setIsLoggedIn } = useContext(AuthContext);

    const handleLogin = () =>
        setIsLoggedIn(true);

    const handleLogout = () => {
        setIsLoggedIn(false);
        navigate("/");
    }

    return (
        <nav className="container">
            <ul>
                <li><Link to="/">Home</Link></li>
<<<<<<< HEAD
=======
                <li>{isLoggedIn && <Link to='/shelters'>Shelters</Link>}</li>
>>>>>>> 7d6f89bb899781de93a9eb7c98cd58eaa7f29678
            </ul>
            <ul>
                <div>
                    {isLoggedIn
                     ? (<Logout onLogoutSuccess={handleLogout} />)
                     : (<Login onLoginSuccess={handleLogin} />)
                    }
                </div>
            </ul>
        </nav>
    )
}

export default NavBar;