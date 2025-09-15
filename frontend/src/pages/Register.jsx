import React, { useState, useEffect } from 'react'
import { loginBackground } from '../assets'
import InputField from '../components/InputField'
import Popup from '../components/Popup'
import axios from 'axios'

const Register = () => {
  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [countryId, setCountryId] = useState("") 
  const [countries, setCountries] = useState([])
  const [showPopup, setShowPopup] = useState(false)
  const [popupMessage, setPopupMessage] = useState("")
  const [popupType, setPopupType] = useState("")

  useEffect(()=> {
    axios.get('http://localhost:8080/api/countries/')
      .then(response => setCountries(response.data))
      .catch(error => console.log("Erreur lors du chargement du pays:", error));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/auth/register",{
        userName,
        email,
        password,
        countryId 
      }, {
        headers: {
          'Content-Type': 'application/json'
        }
      });
      setPopupMessage("Registration successful!")
      setPopupType("success")
      setShowPopup(true)
      console.log(response.data);
    } catch(error){
      setPopupMessage("Registration failed: " + (error.response?.data || error.message))
      setPopupType("error")
      setShowPopup(true)
      console.log(error);
    }
  };
  const closePopup = () => setShowPopup(false)
  return (
    <div className="min-h-screen flex items-center justify-center relative" 
        style={{
                backgroundImage: `url(${loginBackground})`,
                backgroundSize: "cover",
                backgroundPosition: "center",
              }}
          >
      <div className="relative z-10 w-full max-w-md p-10 rounded-xl bg-white/20 backdrop-blur-md shadow-lg">
        <h2 className='text-3xl font-bold text-orange-500 text-center'>Sign up</h2>
        <form onSubmit={handleSubmit} className="flex flex-col gap-4">
          <InputField
            label='Name'
            type='text'
            value={userName}
            onChange={(e) => setUserName(e.target.value)}
            placeholder='Enter your name'
          />
          <InputField
            label='Email'
            type='email'
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder='Enter your email'
          />
          <InputField
            label='Password'
            type='password'
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder='Enter your password'
          />
          <label className="mb-1 text-white font-medium">Country</label>
          <select 
            name="countryId"
            value={countryId}
            onChange={(e) => setCountryId(e.target.value)}
            className="border border-white/50 rounded-lg p-2 bg-white/10 text-white placeholder-white/70 focus:outline-none focus:ring-2 focus:ring-orange-400"
          >
            <option value="">Select country</option>
            {countries.map((c) => (
              <option key={c.id} value={c.id}>{c.name}</option>
            ))}
          </select>
          <button
            type="submit"
            className="bg-orange-400 text-white rounded-lg py-2 mt-2 hover:bg-orange-500 transition-colors"
          >
            Sign up
          </button>
        </form>

        <Popup 
          isOpen={showPopup}
          message={popupMessage}
          type={popupType}
          onClose={closePopup}
        />
        <p className="mt-4 text-center text-white/80">
          have an account?{" "}
          <a
            href="/login"
            className="underline text-orange-300 hover:text-orange-400"
          >
            Login
          </a>
        </p>
      </div>
    </div>
  )
}

export default Register