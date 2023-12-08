import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import PetList from './components/PetList'

function App() {
  const [count, setCount] = useState(0)

  return (
   <div>
    <PetList />    
   </div> 
  )
}

export default App
