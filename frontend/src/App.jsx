import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import HomePage from "./pages/HomePage"
import Activities from "./pages/Activities"
import AddActivity from "./pages/AddActivity"
import Badge from "./pages/Badge"

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/HomePage" element={<HomePage />} />
      <Route path="/activities" element={<Activities />} />
      <Route path="/addActivity" element={<AddActivity />} />
      <Route path="/badge" element={<Badge />} />
    </Routes>
  );
}

export default App;
