import React, {useEffect, useState} from 'react'
import AccountNavbar from '../components/AccountNavbar'
import axios from 'axios'

const Account = () => {
  const [name, setName] = useState("")
  const [email, setEmail] = useState("")
  const [badgeId, setBadgeId] = useState()
  const [label, setLabel] = useState("")
  const [countryId, setCountryId] = useState()
  const [country, setCountry] = useState("")

  useEffect(()=> {
    const token = localStorage.getItem("jwtToken");

     axios
      .get("http://localhost:8080/api/user/", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        const userDTO = response.data;

        if(userDTO && userDTO.userName!== undefined && userDTO.email!== undefined && userDTO.badgeId!==undefined && userDTO.countryId!==undefined){
          setName(userDTO.userName)
          setEmail(userDTO.email)
          setBadgeId(userDTO.badgeId)
          setCountryId(userDTO.countryId)
        }
      })
      .catch((error) => {
        console.error("Error fetching user:", error);
      });
  }, [])

  useEffect(()=>{
    if(badgeId===undefined) return
     const token = localStorage.getItem("jwtToken");

      axios
      .get(`http://localhost:8080/api/badges/${badgeId}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        const badgeDTO = response.data;

        if(badgeDTO){
          setLabel(badgeDTO.label)
        }
      })
      .catch((error) => {
        console.error("Error fetching badge:", error);
      });
  },[badgeId])

   useEffect(()=>{
    if(countryId===undefined) return
     const token = localStorage.getItem("jwtToken");

      axios
      .get(`http://localhost:8080/api/countries/${countryId}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        const countryDTO = response.data;

        if(countryDTO){
          setCountry(countryDTO.name)
        }
      })
      .catch((error) => {
        console.error("Error fetching country:", error);
      });
  },[countryId])

  return (
    <div className="flex flex-col justify-center items-center">
      <AccountNavbar/>
      <h2 className="text-orange-600 font-bold text-center text-3xl underline m-4 space-4">My information</h2>
      <div className="flex flex-col justify-center items-center shadow-xl  w-80 h-80 gap-4 text-green-600 mt-6">
        <p><strong>Name:</strong> {name}</p>
        <p><strong>Email:</strong> {email}</p>
        <p><strong>Country: </strong>{country}</p>
        <p><strong>Badge:</strong> {label}</p>
      </div>
    </div>
  )
}

export default Account
