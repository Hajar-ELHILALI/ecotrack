import React from 'react'

const About = () => {
  return (
    <div className='flex flex-col items-center justify-center container mx-auto p-14 md:px-20 lg:px-32 w-full overflow-hidden text-orange-500' id="About">
      <h1 className='text-2xl sm:text-4xl font-bold mb-2 text-orange-500'>
        About <span className='underline underline-offset-4 decoration-2 font-light'>EcoTrack</span>
      </h1>

      <p className=' max-w-80 text-center mb-8'>EcoTrack is a platform designed to help individuals and communities monitor their carbon footprint. 
      By tracking daily activities such as transportation, energy use, and consumption, EcoTrack provides 
      insights and personalized recommendations to reduce environmental impact. Our mission is to empower 
      everyone to make sustainable choices for a greener future.</p>

      <div className='flex flex-col md:flex-row items-center md:items-start md:gap-20'>

      </div>
    </div>
  )
}

export default About
