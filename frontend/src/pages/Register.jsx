import React, { useState } from 'react'
import { loginBackground } from '../assets'
import InputField from '../components/InputField'

const Register = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [country, setCountry] = useState("")

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("name:", name,"Email:", email, "Password:", password, "Country:", country);
  };

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
          value={name}
          onChange={(e) => setName(e.target.value)}
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
          <div className="flex flex-col">
            <label className="mb-1 text-white font-medium">Country</label>
            <select 
              name="country"
              value={country}
              onChange={(e) => setCountry(e.target.value)}
              className="border border-white/50 rounded-lg p-2 bg-white/10 text-white placeholder-white/70 focus:outline-none focus:ring-2 focus:ring-orange-400"
            >
              <option value="">Select country</option>
              <option value="Morocco">Morocco</option>
              <option value="France">France</option>
              <option value="USA">USA</option>
            </select>
            <button
            type="submit"
            className="bg-orange-400 text-white rounded-lg py-2 mt-2 hover:bg-orange-500 transition-colors"
          >
            Sign up
          </button>
          </div>
        </form>

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
