import React, {useEffect, useState} from 'react'
import axios from "axios"
import Popup from "../components/Popup"

const AddGoal = () => {
  const [startDate, setStartDate]=useState("")
  const [endDate, setEndDate]=useState("")
  const [emissionTarget, setEmissionTarget]=useState(0.0)
  const [popup, setPopup] = useState({ isOpen: false, message: "", type: "success" });

  const handleSubmit = async () => {
     if (new Date(startDate) >= new Date(endDate)) {
    setPopup({ 
      isOpen: true, 
      message: "Start date must be earlier than end date", 
      type: "error" 
    });
    return;
  }
    const token = localStorage.getItem("jwtToken");
    const payload = {
      startDate,
      endDate,
      emissionTarget,
      goalAchieved: false,
    };

    try {
      await axios.post("http://localhost:8080/api/user_goals", payload, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setPopup({ isOpen: true, message: "Goal saved with success", type: "success" });
    } catch (error) {
      console.error("Erreur lors de l'envoi:", error);
      setPopup({ isOpen: true, message: "Erreur lors de l'ajout ", type: "error" });
    }
  };

  return (
    <div className="flex flex-col justify-center gap-4 mt-4 items-center w-96 h-96 shadow-xl ml-96">
     

      <h2 className="text-orange-600 font-bold text-lg underline">Add goal</h2>
      <div>
            <label className="mb-1 text-orange-600 font-medium mr-2">Start Date:</label>
            <input
              type="date"
              value={startDate}
              onChange={(e) => setStartDate(e.target.value)}
              className="w-24 text-center text-orange-800 border border-green-600 rounded p-1"
              placeholder="Start Date"
            />
          </div>
          <div>
            <label className="mb-1 mr-2 text-orange-600 font-medium">End Date:</label>
            <input
              type="date"
              value={endDate}
              onChange={(e) => setEndDate(e.target.value)}
              className="w-24 text-center text-orange-800 border border-green-600 rounded p-1"
              placeholder="End Date"
            />
          </div>

          <div>
            <label className="mb-1 mr-2 text-orange-600 font-medium">Emission Target:</label>
            <input
              type="number"
              step="0.1"
              min="0"
              value={emissionTarget}
              onChange={(e) => setEmissionTarget(e.target.value)}
              className="w-24 text-center text-orange-800 border border-green-600 rounded p-1"
              placeholder="Emission Target"
            />
          </div>

        <button
            type="button"
            onClick={handleSubmit}
            className="px-4 py-2 bg-green-600 text-white rounded-lg hover:bg-green-700 transition-colors w-24"
          >
            Save
          </button>

           <Popup
            isOpen={popup.isOpen}
            message={popup.message}
            type={popup.type}
            onClose={() => setPopup({ ...popup, isOpen: false })}
          />
        
    </div>
  )
}

export default AddGoal
