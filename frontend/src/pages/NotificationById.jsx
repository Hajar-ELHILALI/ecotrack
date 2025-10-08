import React, { useEffect, useState } from "react";
import AccountNavbar from "../components/AccountNavbar";
import axios from "axios";
import { useParams } from "react-router-dom"; 

const NotificationById = () => {
  const [notification, setNotification] = useState(null);
  const { id } = useParams(); 

  useEffect(() => {
    axios
      .get(`/api/notifications/${id}`)
      .then((response) => setNotification(response.data))
      .catch((error) =>
        console.log("Erreur lors du chargement de la notification:", error)
      );
  }, [id]);

  if (!notification) {
    return <p className="text-center mt-10">Chargement...</p>;
  }

  return (
    <div className="min-h-screen bg-gray-100">
      <AccountNavbar />
      <div className="px-6 py-10 max-w-2xl mx-auto">
        <div className="bg-white shadow-lg rounded-lg p-6 border-l-4 border-orange-600">
          <h2 className="text-2xl font-bold text-orange-600 mb-4">
            Notification Details
          </h2>
         
          <p><span className="font-semibold">Type:</span> {notification.type}</p>
          <p><span className="font-semibold">Content:</span> {notification.content}</p>
          <p><span className="font-semibold">Date:</span> {notification.date}</p>
         
        </div>
      </div>
    </div>
  );
};

export default NotificationById;