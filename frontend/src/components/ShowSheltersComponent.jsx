import axios from 'axios';
import { useState, useEffect } from 'react';

const ShowSheltersComponent = () => {
    const [shelters, setShelters] = useState([]);

    const handleEdit = () => {};
    const handleDelete = () => {};
    const handleShowPets = () => {};

    useEffect(() => {
        //get our shelters
        axios.get('/api/shelter')
        .then(response => {
            console.log(response)
            setShelters(response.data)
        })
        .catch(error => console.error("Error fetching shelters: ", error));
    }, []);


return (
    <>
        <h2>Shelters</h2>
        {shelters.map(shelter => (
            <div key={shelter.id}>
                <nav>
                    <ul>
                        <li>{shelter.name}</li>
                        <li><button onClick={() => handleEdit(shelter)}>Edit</button></li>
                        <li><button onClick={() => handleDelete(shelter)}>Delete</button></li>
                        <li><button onClick={() => handleShowPets(shelter)}>View Pets</button></li>
                    </ul>
                </nav>
            </div>
        ))}

    </>
)
};

export default ShowSheltersComponent;