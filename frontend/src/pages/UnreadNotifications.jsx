import React, {useState, useEffect} from 'react'
import AccountNavbar from '../components/AccountNavbar'
import axios from 'axios'
import { useNavigate } from "react-router-dom";

const UnreadNotifications = () => {
  const [notifications, setNotifications] = useState([])
  const navigate = useNavigate();
  
   useEffect(() => {
    fetchNotifications();
  }, []);
   const fetchNotifications = () => {
    const token = localStorage.getItem("jwtToken");
    axios
      .get("http://localhost:8080/api/notifications/unread", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => setNotifications(response.data))
      .catch((error) =>
        console.log("Erreur lors du chargement des notifications:", error)
      );
  };

  const handleNotificationClick = (id) => {
    navigate(`/notification/${id}`);
  };

  const handleNotificationClick2 = async (notification) => {
    const token = localStorage.getItem("jwtToken");
    try {
      await axios.put(
        "http://localhost:8080/api/notifications/",
        { ...notification, read: true },
        { headers: { Authorization: `Bearer ${token}` } }
      );
      fetchNotifications();
    } catch (error) {
      console.error("Erreur lors de la mise à jour :", error);
    }
  };

  return (
    <div className="min-h-screen bg-gray-100">
      <AccountNavbar/>
       <div className="px-6 py-4">
        <h2 className="text-2xl font-semibold text-orange-600 m-4 underline">
          My Unread Notifications
        </h2>

        <ol className="space-y-3">
          {[...notifications].reverse().map((notification) => (
            <li
              key={notification.id}
              className="bg-white shadow-md rounded-lg p-4 border border-gray-200 hover:shadow-lg transition"

              onClick={() => {
                handleNotificationClick(notification.id) 
                handleNotificationClick2(notification)
              }}
            >
              <div className="flex flex-col">
                <p className="font-bold">{notification.type}</p>
                <div className="flex items-start justify-between">
                <p className="font-bold">{notification.content}
                 
                </p>
                 <span className="text-sm text-gray-400 ml-4">
                  {notification.date}
                </span>
                </div>
              </div>
             
            </li>
          ))}
        </ol>
      </div>
    </div>
  )
}

export default UnreadNotifications