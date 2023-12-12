import axios from "axios";

const Logout = ({ onLogoutSuccess }) => {
    const handleLogout = async () =>
    {
        try {
            const response = await axios.get('/api/logout');
            console.log(response.data);
            onLogoutSuccess();

        } catch (error) {
            console.error('Logout failed', error);
        }

    }

    return <button onClick={handleLogout}>Logout</button>
};

export default Logout;
