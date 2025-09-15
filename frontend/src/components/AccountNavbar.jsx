import React, { useState, useEffect } from 'react';
import { profil, accountMenu, accountCross } from '../assets';

const AccountNavbar = () => {
  const [showMenu, setShowMenu] = useState(false);
  const [showProfileMenu, setShowProfileMenu] = useState(false);

  useEffect(() => {
    document.body.style.overflow = showMenu ? 'hidden' : 'auto';
    return () => {
      document.body.style.overflow = 'auto';
    };
  }, [showMenu]);

  return (
    <>
      
      <div className="fixed top-2 left-2 z-50">
        {!showMenu && (
          <button onClick={() => setShowMenu(true)}>
            <img src={accountMenu} className="h-10 w-10 rounded-full" alt="Menu" />
          </button>
        )}
      </div>

     
      {showMenu && (
        <div className="fixed top-0 left-0 md:flex flex-col text-white bg-green-600 shadow-md h-screen w-40 gap-4 p-2 z-40">
          <div className="flex justify-start cursor-pointer">
            <img
              onClick={() => setShowMenu(false)}
              src={accountCross}
              className="w-10"
              alt="Close menu"
            />
          </div>
          <a href="#myAccount">Account</a>
          <a href="#addActivity">Add activity</a>
          <a href="#activities">My activities</a>
          <a href="#statistiques">Statistics</a>
          <a href="#advices">Advices</a>
          <a href="#goals">My goals</a>
          <a href="#badges">My badges</a>
          <a href="#Review">Add review</a>
          <a href="#settings">Settings</a>
          <a href="#Login">Sign out</a>
        </div>
      )}

     
      <div className="fixed top-2 right-2 z-50">
        <button onClick={() => setShowProfileMenu(!showProfileMenu)}>
          <img src={profil} className="h-10 w-10 rounded-full" alt="Profile" />
        </button>

        {showProfileMenu && (
          <div className="absolute right-0 mt-2 text-green-600 w-40 bg-white shadow-lg rounded-lg py-2 z-50">
            <a href="#account" className="block px-4 py-2 hover:bg-gray-100">Account</a>
            <a href="#Login" className="block px-4 py-2 hover:bg-gray-100">Sign out</a>
          </div>
        )}
      </div>
    </>
  );
};

export default AccountNavbar;
