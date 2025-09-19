import React, {useState} from 'react'
import AccountNavbar from '../components/AccountNavbar'

const Activities = () => {
  const [userActivities, setUserActivities] = useState([])


  return (
    <div>
      <AccountNavbar/>
      <div className="flex flex-col items-center ">
        <h2 className="font-bold text-orange-600 text-lg underline">Activities List</h2>
        <div className="flex-col shadow-xl w-96 h-screen mt-6">
          <ul className="text-orange-400 text-center space-y-4">
            <li> Acitivity 1</li>
            <li>Activity 2</li>
            <li>Activity 3</li>
            <li>Activity 4</li>
            <li>Activity 5</li>
          </ul>
        </div>
      </div>
    </div>
  )
}

export default Activities
