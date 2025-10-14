import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import HomePage from "./pages/HomePage"
import Activities from "./pages/Activities"
import AddActivity from "./pages/AddActivity"
import Badge from "./pages/Badge"
import Account from "./pages/Account"
import Goals from "./pages/Goals"
import Review from "./pages/Review"
import Advices from "./pages/Advices"
import Statistics from "./pages/Statistics"
import Notification from "./pages/Notification"
import UnreadNotifications from "./pages/UnreadNotifications"
import NotificationById from "./pages/NotificationById"

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/homePage" element={<HomePage />} />
      <Route path="/activities" element={<Activities />} />
      <Route path="/addActivity" element={<AddActivity />} />
      <Route path="/badge" element={<Badge />} />
      <Route path="/myAccount" element={<Account />} />
      <Route path="/goals" element={<Goals />} />
      <Route path="/review" element={<Review />} />
      <Route path="/advices" element={<Advices />} />
      <Route path="/statistics" element={<Statistics />} />
      <Route path="/notifications" element={<Notification />} />
      <Route path="/unreadNotifications" element={<UnreadNotifications />} />
       <Route path="/notification/:id" element={<NotificationById />} />
    </Routes>
  );
}

export default App;
