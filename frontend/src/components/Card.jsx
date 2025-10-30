import React from 'react'
import { Link } from "react-router-dom";

const Card = ({ image, title,href }) => {
  return (
    <div className="flex flex-col items-center bg-white shadow-md rounded-xl p-4 hover:shadow-lg transition duration-300 h-48 w-48">
      <Link to={href}>
      <img 
        src={image} 
        alt={title} 
        className="h-32 w-32 object-cover rounded-lg"
      />
      <p className="mt-2 text-lg font-bold text-orange-500 text-center" >
        {title}
      </p>
      </Link>
      
    </div>
  )
}

export default Card
