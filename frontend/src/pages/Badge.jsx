import React, {useEffect, useState} from 'react'
import AccountNavbar from '../components/AccountNavbar'
import axios from 'axios'
import {welcome, target, planetHero} from '../assets'

const Badge = () => {
  const [badge, setBadge] = useState({})
  const [loading, setLoading] = useState(true)
  const [badgeImage, setBadgeImage] = useState()
  const [badgeId, setBadgeId] = useState()

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const token = localStorage.getItem("jwtToken");
        const response = await axios.get("http://localhost:8080/api/user/", {
          headers: { Authorization: `Bearer ${token}` },
        });

        const userDTO = response.data;
        if (userDTO && userDTO.badgeId !== undefined) {
          setBadgeId(userDTO.badgeId);
        }
      } catch (error) {
        console.error("Error fetching user:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchUser();
  }, []);

  useEffect(() => {
    if (!badgeId) return;

    const fetchBadge = async () => {
      try {
        const token = localStorage.getItem("jwtToken");
        const response = await axios.get(
          `http://localhost:8080/api/badges/${badgeId}`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );

        const badgeDTO = response.data;
        setBadge(badgeDTO);

        if (badgeDTO) {
          switch (badgeDTO.id) {
            case 1:
              setBadgeImage(welcome);
              break;
            case 2:
              setBadgeImage(target);
              break;
            case 3:
              setBadgeImage(planetHero);
              break;
            default:
              setBadgeImage(null);
          }
        }
      } catch (error) {
        console.error("Error fetching badge:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchBadge();
  }, [badgeId]);


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
            <img src={badgeImage} alt={badge.label} className="w-64 h-64"/>
          </div>
          
          <h2 className="text-2xl font-bold text-green-600">{badge.label}</h2>
          <p className="text-green-600">{badge.description}</p>
        </div>
      </div>
    </div>
  );
};

export default Badge;