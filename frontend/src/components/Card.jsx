import React from 'react'

const Card = ({ image, title,href }) => {
  return (
    <div className="flex flex-col items-center bg-white shadow-md rounded-xl p-4 hover:shadow-lg transition duration-300 h-64">
      <a href="href">
      <img 
        src={image} 
        alt={title} 
        className="h-40 w-40 object-cover rounded-lg"
      />
      </a>
      <p className="mt-2 text-lg font-bold text-orange-500" >
        {title}
      </p>
    </div>
  )
}

export default Card
