import React, {useEffect, useState} from 'react'
import AccountNavbar from '../components/AccountNavbar'
import axios from 'axios'
import {welcome, target, planetHero} from '../assets'

const Badge = () => {
  const [label, setLabel] = useState("")
  const [description, setDescription] = useState('')
  const [loading, setLoading] = useState(true)
  const [badge, setBadge] = useState()

  useEffect(() => {
    const token = localStorage.getItem("jwtToken");

    axios
      .get("http://localhost:8080/api/user/", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        const userDTO = response.data;

        if (userDTO && userDTO.badgeId !== undefined) {
          switch (userDTO.badgeId) {
            case 1:
              setLabel("Eco_Novice");
              setDescription("Welcome to the eco team! Every action counts.");
              setBadge(welcome);
              break;
            case 2:
              setLabel("Green_Achiever");
              setDescription("Well done! You make a difference every day.");
              setBadge(target)
              break;
            case 3:
              setLabel("Planet_Hero");
              setDescription("You are a role model for the planet.");
              setBadge(planetHero)
              break;
            default:
              setLabel("Unknown Badge");
              setDescription("Badge not recognized.");
          }
        }
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching user:", error);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return (
      <div>
        <AccountNavbar />
        <p className="text-center mt-6 text-gray-600">Loading badge...</p>
      </div>
    );
  }

  return (
    <div>
      <AccountNavbar />
      <div className="flex flex-col justify-center items-center h-screen bg-gray-100">  
          <h2 className="text-orange-600 font-bold text-center text-3xl underline mb-4">My badge</h2>
        <div className="p-6 bg-white rounded-lg shadow-lg text-center space-y-4">
          <div className="flex justify-center">
            <img src={badge} alt={label} className="w-64 h-64"/>
          </div>
          
          <h2 className="text-2xl font-bold text-green-600">{label}</h2>
          <p className="text-green-600">{description}</p>
        </div>
      </div>
    </div>
  );
};

export default Badge;