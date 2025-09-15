import React, {useEffect, useState} from 'react'
import {profil, list, advice, goals, addActivity, statistics, accountMenu, accountCross} from '../assets'
import Card from '../components/Card'


const HomePage = () => {
  const [showMenu, setShowMenu] = useState(false);
  const [showProfileMenu, setShowProfileMenu] = useState(false);

  useEffect(() => {
    document.body.style.overflow = showMenu ? 'hidden' : 'auto';
    return () => {
      document.body.style.overflow = 'auto';
    };
  }, [showMenu]);

  return (
    <div className="flex ">
      <div className="fixed  top-2 left-2">
        {!showMenu && 
          <button onClick={() => setShowMenu(true)}>
          <img  src={accountMenu} className="h-10 w-10 rounded-full"/>
        </button>
        }
        
      </div> 
      {showMenu && (
          <div className="hidden md:flex flex-col text-white bg-green-600 shadow-md h-screen w-40 gap-4 p-2 m-0">

            <div className='flex justify-start  cursor-pointer'>
              <img onClick={() => setShowMenu(false)} src={accountCross} className='w-10' alt="" />
            </div>
            <a href="#myAccount"> Account</a>
            <a href="#addActivity"> Add activity</a>
            <a href="#activities"> My activities</a>
            <a href="#statistiques"> Statistics</a>
            <a href="#advices"> Advices</a>
            <a href="#goals"> My goals</a>
            <a href="#badges"> My badges</a>
            <a href="#Review"> Add review</a>
            <a href="#settings"> settings</a> 
            <a href="#Login"> Sign out</a>
          </div>

          
      )}
      

       <div className="fixed top-2 right-2">
        <button onClick={() => setShowProfileMenu(!showProfileMenu)}>
          <img  src={profil} className="h-10 w-10 rounded-full"/>
        </button>

        {showProfileMenu && (
          <div className="absolute right-0 mt-2 text-green-600 w-40 bg-white shadow-lg rounded-lg py-2">
            <a href="#account" className="block px-4 py-2  hover:bg-gray-100">Account</a>
            <a href="#Login" className="block px-4 py-2  hover:bg-gray-100">Sign out</a>
          </div>
        )}
      </div> 

      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6 p-6 ">
          <Card image={addActivity} title="Add activity" href="#addActivity"/>
          <Card image={list} title="Activities list" href="#activities"/>
          <Card image={statistics} title="Statistics" href="#statistic"/>
          <Card image={advice} title="Advices" href="#advices"/>
          <Card image={goals} title="Goals" href="#goals"/>
      </div>

      {/* <div className="flex justify-center mt-6">
        <h3 className="font-bold text-orange-500 text-center text-lg">Latest activities</h3>
      </div> */}
    </div>
  )
}

export default HomePage
