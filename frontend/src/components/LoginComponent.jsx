import React, { useState } from 'react';
import axios from 'axios';

const Login = ({ onLoginSuccess }) => {
    const [userId, setUserId] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async () => {
        try {
            const response = await axios.post('/api/login', { userId, password });
            console.log(response.data);
            onLoginSuccess();

        } catch (error) {
            console.error("Login failed: ", error);
        }
    };

    return (
        <>
            <li><input type="text" placeholder='User ID' onChange={e => setUserId(e.target.value)} /></li>
            <li><input type="password" placeholder='Password' onChange={e => setPassword(e.target.value)} /></li>
            <li><button onClick={handleLogin}>Login</button></li>
        </>
    );

}




export default Login;