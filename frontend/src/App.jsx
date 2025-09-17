import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import HomePage from "./pages/HomePage"
import Activities from "./pages/Activities"
import AddActivity from "./pages/AddActivity"

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/HomePage" element={<HomePage />} />
      <Route path="/activities" element={<Activities />} />
      <Route path="/addActivity" element={<AddActivity />} />
    </Routes>
  );
}

export default App;
