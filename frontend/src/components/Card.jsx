import React from 'react'
import { Link } from "react-router-dom";

const Card = ({ image, title,href }) => {
  return (
    <div className="flex flex-col items-center bg-white shadow-md rounded-xl p-4 hover:shadow-lg transition duration-300 h-64">
      <Link to={href}>
      <img 
        src={image} 
        alt={title} 
        className="h-40 w-40 object-cover rounded-lg"
      />
      </Link>
      <p className="mt-2 text-lg font-bold text-orange-500" >
        {title}
      </p>
    </div>
  )
}

export default Card
