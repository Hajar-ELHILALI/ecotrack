import React, { useState, useEffect } from 'react'
import AccountNavbar from '../components/AccountNavbar'
import AddGoal from '../pages/AddGoal'
import axios from 'axios'

const Goals = () => {
  const [goals, setGoals] = useState([])
  const [editingGoal, setEditingGoal] = useState(null)
  const [formData, setFormData] = useState({
    startDate: "",
    endDate: "",
    emissionTarget: "",
  })

  useEffect(() => {
    const token = localStorage.getItem("jwtToken")
    axios
      .get("http://localhost:8080/api/user_goals", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => setGoals(response.data))
      .catch((error) => console.log("Erreur lors du chargement des goals:", error))
  }, [])

  const deleteGoal = (goalId) => {
    const token = localStorage.getItem("jwtToken")
    axios
      .delete(`http://localhost:8080/api/user_goals/${goalId}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then(() => {
        setGoals(goals.filter((goal) => goal.id !== goalId))
      })
      .catch((error) => console.log("Erreur lors de la suppression de goal:", error))
  }

  const startEditing = (goal) => {
    setEditingGoal(goal.id)
    setFormData({
      id: goal.id,
      startDate: goal.startDate,
      endDate: goal.endDate,
      emissionTarget: goal.emissionTarget,
    })
  }

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    })
  }

  const saveUpdate = (goalId) => {
    const token = localStorage.getItem("jwtToken")
    axios
      .put(`http://localhost:8080/api/user_goals`, formData, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        setGoals(goals.map((g) => (g.id === goalId ? response.data : g)))
        setEditingGoal(null)
      })
      .catch((error) => console.log("Erreur lors de la mise a jour de goal:", error))
  }

  return (
    <div className=" min-h-screen ml-4">
      <AccountNavbar />
      <AddGoal />
      <div className="min-h-screen">
        <h2 className="text-orange-600 font-bold text-lg underline">My Goals</h2>
        <ol>
          {[...goals].reverse().map((goal) => (
            <li key={goal.id}>
              {editingGoal === goal.id ? (
                <div>
                  <input
                    type="date"
                    name="startDate"
                    value={formData.startDate}
                    onChange={handleChange}
                    className="w-32 text-center text-orange-800 border border-green-600 rounded p-1 mr-2"
                  />
                  <input
                    type="date"
                    name="endDate"
                    value={formData.endDate}
                    onChange={handleChange}
                    className="w-32 text-center text-orange-800 border border-green-600 rounded p-1 mr-2"
                  />
                  <input
                    type="number"
                    name="emissionTarget"
                    value={formData.emissionTarget}
                    onChange={handleChange}
                    className="w-32 text-center text-orange-800 border border-green-600 rounded p-1 mr-2"
                  />
                  <button
                    onClick={() => saveUpdate(goal.id)}
                    className="text-white bg-green-600 rounded-lg p-2 mr-2"
                  >
                    Save
                  </button>
                  <button
                    onClick={() => setEditingGoal(null)}
                    className="text-white bg-gray-500 rounded-lg p-2"
                  >
                    Cancel
                  </button>
                </div>
              ) : (
                <div className="shadow-xl p-6 rounded-lg bg-white border-l-4 border-orange-600 text-green-600 mt-2">
                  <span className="mb-1 text-orange-600 font-medium mr-2">Start date:</span> {goal.startDate} <span className="mb-1 text-orange-600 font-medium mr-2">End Date:</span> {goal.endDate} <span className="mb-1 text-orange-600 font-medium mr-2">emission target:</span> {goal.emissionTarget}
                  <button
                    onClick={() => startEditing(goal)}
                    className="text-white bg-blue-500 rounded-lg p-2 ml-2"
                  >
                    Update
                  </button>
                  <button
                    onClick={() => deleteGoal(goal.id)}
                    className="text-white bg-red-600 rounded-lg p-2 ml-2"
                  >
                    Delete
                  </button>
                </div>
              )}
            </li>
          ))}
        </ol>
      </div>
    </div>
  )
}

export default Goals
