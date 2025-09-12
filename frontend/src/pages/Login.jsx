import React, { useState } from "react";
import { loginBackground } from "../assets";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Email:", email, "Password:", password);
  };

  return (
    <div
      className="min-h-screen flex items-center justify-center relative"
      style={{
        backgroundImage: `url(${loginBackground})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    >
      {/* Overlay sombre léger pour lisibilité */}
      <div className="absolute inset-0 bg-black/30"></div>

      {/* Formulaire style glassmorphism */}
      <div className="relative z-10 w-full max-w-md p-10 rounded-xl bg-white/20 backdrop-blur-md shadow-lg">
        <h2 className="text-3xl font-bold text-orange-400 mb-6 text-center">
          Login
        </h2>
        <form onSubmit={handleSubmit} className="flex flex-col gap-4">
          <div className="flex flex-col">
            <label className="mb-1 text-white font-medium">Email</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="Enter your email"
              className="border border-white/50 rounded-lg p-2 bg-white/10 text-white placeholder-white/70 focus:outline-none focus:ring-2 focus:ring-orange-400"
            />
          </div>

          <div className="flex flex-col">
            <label className="mb-1 text-white font-medium">Password</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Enter your password"
              className="border border-white/50 rounded-lg p-2 bg-white/10 text-white placeholder-white/70 focus:outline-none focus:ring-2 focus:ring-orange-400"
            />
          </div>

          <button
            type="submit"
            className="bg-orange-400 text-white rounded-lg py-2 mt-2 hover:bg-orange-500 transition-colors"
          >
            Login
          </button>
        </form>

        <p className="mt-4 text-center text-white/80">
          Don't have an account?{" "}
          <a
            href="/register"
            className="underline text-orange-300 hover:text-orange-400"
          >
            Sign up
          </a>
        </p>
      </div>
    </div>
  );
};

export default Login;
