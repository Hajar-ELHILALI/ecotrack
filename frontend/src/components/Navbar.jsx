import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { menu, cross_icon } from '../assets'

const Navbar = () => {
  const [showMenu, setShowMenu] = useState(false);

  useEffect(() => {
    document.body.style.overflow = showMenu ? 'hidden' : 'auto';
    return () => {
      document.body.style.overflow = 'auto';
    };
  }, [showMenu]);

  return (
    <div className='absolute top-0 left-0 w-full z-10'>
      <div className='container mx-auto flex justify-between items-center py-4 px-6 md:px-20 lg:px-32 bg-transparent'>
        
        <ul className='hidden md:flex gap-7 text-orange-500 absolute left-1/2 transform -translate-x-1/2'>
          <a href="#Header" className='cursor-pointer hover:text-orange-600'>Home</a>
          <a href="#About" className='cursor-pointer hover:text-orange-600'>About</a>
          <a href="#Contact" className='cursor-pointer hover:text-orange-600'>Contact</a>
        </ul>

        <div className="hidden md:flex gap-4 ml-auto">
          <Link to="/register" className='text-white bg-orange-500 px-8 py-2 rounded-full'>Sign up</Link>
          <Link to="/login" className='text-white bg-orange-500 px-8 py-2 rounded-full'>Login</Link>
        </div>

        <img onClick={() => setShowMenu(true)} src={menu} className='md:hidden w-10 cursor-pointer' alt="" />

        <div className={`md:hidden ${showMenu ? 'fixed w-full' : 'h-0 w-0'} right-0 top-0 bottom-0 overflow-hidden bg-white transition-all`}>
          <div className='flex justify-end p-6 cursor-pointer'>
            <img onClick={() => setShowMenu(false)} src={cross_icon} className='w-10' alt="" />
          </div>
          <ul className='flex flex-col items-center gap-2 mt-5 px-5 text-lg font-medium text-orange-500'>
            <a href="#Header" onClick={() => setShowMenu(false)}>Home</a>
            <a href="#About" onClick={() => setShowMenu(false)}>About</a>
            <a href="#Contact" onClick={() => setShowMenu(false)}>Contact</a>
            <Link to="/login" onClick={() => setShowMenu(false)} className='mt-4 bg-orange-500 text-white px-6 py-2 rounded-full'>Login</Link>
          </ul>
        </div>
      </div>
    </div>
  )
}

export default Navbar
