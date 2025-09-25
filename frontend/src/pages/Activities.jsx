import React, { useState, useEffect } from 'react';
import AccountNavbar from '../components/AccountNavbar';
import axios from 'axios';

const Activities = () => {
  const [userActivities, setUserActivities] = useState([]);
  const [userId, setUserId] = useState();

  
  useEffect(() => {
    const token = localStorage.getItem("jwtToken");

    axios
      .get("http://localhost:8080/api/user/", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        const userDTO = response.data;
        if (userDTO) setUserId(userDTO.id);
      })
      .catch((error) => {
        console.error("Error fetching user:", error);
      });
  }, []);

  useEffect(() => {
    if (!userId) return;

    const token = localStorage.getItem("jwtToken");
    axios
      .get(`http://localhost:8080/api/user_activities/byUser/${userId}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then(async (response) => {
        const activities = response.data;

        
        const enrichedActivities = await Promise.all(
          activities.map(async (activity) => {
            try {
              const typeResponse = await axios.get(
                `http://localhost:8080/api/activity-types/${activity.activityTypeId}`,
                { headers: { Authorization: `Bearer ${token}` } }
              );
              const categoryResponse = await axios.get(
                `http://localhost:8080/api/categories/${typeResponse.data.categoryId}`,
                { headers: { Authorization: `Bearer ${token}` } }
              ) 
              return {
                ...activity,
                activityTypeName: typeResponse.data.name,
                categoryType: categoryResponse.data.categoryType,
              };
            } catch (err) {
              console.error("Erreur fetch activityType:", err);
              return activity;
            }
          })
        );

        setUserActivities(enrichedActivities);
      })
      .catch((error) =>
        console.log("Erreur lors du chargement des User activities:", error)
      );
  }, [userId]);

  const deleteActivity = (activityId) => {
    const token = localStorage.getItem("jwtToken")
    axios
      .delete(`http://localhost:8080/api/user_activities/${activityId}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then(() => {
        setUserActivities(userActivities.filter((activity) => activity.id !== activityId))
      })
      .catch((error) => console.log("Erreur lors de la suppression de activity:", error))
  }

  return (
    <div>
      <AccountNavbar />
      <div className="flex flex-col items-center">
        <h2 className="font-bold text-orange-600 text-lg underline">Activities List</h2>
        <div className="flex-col shadow-xl w-96 min-h-screen mt-6 text-green-600 w-screen">
          <ol className="list-decimal pl-5">
            {userActivities.length > 0 ? (
              userActivities.map((activity) => (
                <li key={activity.id} className="shadow-xl p-6 rounded-lg bg-white border-l-4 border-orange-600 mt-2">
                  <p><span className="font-semibold">Type:</span> {activity.activityTypeName}</p>
                  <p><span className="font-semibold">Category:</span> {activity.categoryType}</p>
                  <p><span className="font-semibold">Quantity:</span> {activity.quantity}</p>
                  <p><span className="font-semibold">Share with:</span> {activity.nbrPersonnes}</p>
                  <p><span className="font-semibold">Date:</span> {activity.date}</p>
                  <button
                    onClick={()=>deleteActivity(activity.id)}
                    className="text-white bg-red-600 rounded-lg p-2 ml-2"
                  >
                    Delete
                  </button>
                  <button
                    
                    className="text-white bg-blue-500 rounded-lg p-2 ml-2"
                  >
                    Update
                  </button>
                </li>
              ))
            ) : (
              <p className="text-gray-500 italic">No activity for now</p>
            )}
          </ol>
        </div>
      </div>
    </div>
  );
};

export default Activities;
