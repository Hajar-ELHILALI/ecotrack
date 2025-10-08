import React, { useState, useEffect } from "react";
import AccountNavbar from "../components/AccountNavbar";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Notification = () => {
  const [notifications, setNotifications] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchNotifications();
  }, []);
  const fetchNotifications = () => {
    axios
      .get("/api/notifications/me")
      .then((response) => setNotifications(response.data))
      .catch((error) =>
        console.log("Erreur lors du chargement des notifications:", error)
      );
  };

  const handleNotificationClick = (id) => {
    navigate(`/notification/${id}`);
  };

  const handleNotificationClick2 = async (notification) => {
    try {
      await axios.put(
        "/api/notifications/",
        { ...notification, read: true }
      );
      fetchNotifications();
    } catch (error) {
      console.error("Erreur lors de la mise à jour :", error);
    }
  };

  const deleteNotification = (notificationId) => {
    axios
      .delete(`/api/notifications/${notificationId}`)
      .then(() => {
        setNotifications(notifications.filter((notification) => notification.id !== notificationId))
      })
      .catch((error) => console.log("Erreur lors de la suppression de notification:", error))
  }

  return (
    <div className="min-h-screen bg-gray-100">
      <AccountNavbar />

      <div className="flex justify-end px-6 pt-6">
        <button
          onClick={() => navigate("/unreadNotifications")}
          className="bg-orange-600 text-white px-4 py-2 rounded-lg shadow hover:bg-orange-700 transition"
        >
          Unread
        </button>
      </div>

      <div className="px-6 py-4">
        <h2 className="text-2xl font-semibold text-orange-600 mb-4 underline">
          My Notifications
        </h2>

        <ol className="space-y-3">
          {[...notifications].reverse().map((notification) => (
            <li
              key={notification.id}
              className="bg-white shadow-md rounded-lg p-4 border border-gray-200 hover:shadow-lg transition cursor-pointer"
              onClick={() => {
                handleNotificationClick(notification.id) 
                handleNotificationClick2(notification)
              }}
            >
              <div className="flex flex-col">
                <p className={`${notification.read ? "text-black" : "font-bold text-orange-600"}`}>
                  {notification.type}
                </p>
                <div className="flex items-start justify-between">
                  <p className={`${notification.read ? "text-black" : "font-bold"}`}>
                    {notification.content}
                  </p>
                  <div className="flex flex-col gap-4">
                    <span className="text-sm text-gray-400 ">{notification.date}</span>

                    <button
                      onClick={(e) => {
                        e.stopPropagation()
                        deleteNotification(notification.id)
                      }}
                      className="text-white bg-red-600 rounded-lg p-2"
                    >
                      Delete
                    </button>
                  </div>
                </div>
              </div>
            </li>
          ))}
        </ol>
      </div>
    </div>
  );
};

export default Notification;