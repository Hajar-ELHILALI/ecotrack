import React, { useState, useEffect } from "react"
import AccountNavbar from "../components/AccountNavbar"
import axios from 'axios'
import Popup from "../components/Popup"

const Review = () => {
  const [rating, setRating] = useState(0)
  const [hover, setHover] = useState(0)
  const [comment, setComment]=useState("")
  const [userId, setUserId] = useState()
  const [user, setUser] = useState()
  const [popup, setPopup] = useState({ isOpen: false, message: "", type: "success" })
  const [reviews, setReviews] = useState([])

  useEffect(() => {
    axios
      .get("/api/user/")
      .then((response) => {
        const userData = response.data
        setUser(userData)
        if (userData) setUserId(userData.id);
      })
      .catch((error) => {
        console.error("Error fetching user:", error);
      });
  }, []);

   useEffect(() => {
     axios
       .get("/api/user_reviews/byUser/")
      .then((response) => {
        setReviews(response.data)
      })
      .catch((error) => {
         console.error("Error fetching user reviews:", error);
       });
   }, []);

  const handleSubmit = async () => {
    const payload = {
      rating,
      comment,
      createdAT: new Date().toISOString().split("T")[0],
      userId,
    };

    try {
      await axios.post("/api/user_reviews/",payload);
      setPopup({ isOpen: true, message: "review added with success ", type: "success" });
    } catch (error) {
      console.error("Erreur lors de l'envoi:", error);
      setPopup({ isOpen: true, message: "Failed ", type: "error" });
    }
  };
  
  const deleteReview = (reviewId) => {
    axios
      .delete(`/api/user_goals/${reviewId}`)
      .then(() => {
        setReviews(reviews.filter((review) => review.id !== reviewId))
      })
      .catch((error) => console.log("Erreur lors de la suppression de review:", error))
  }

  return (
    <div className="min-h-screen">
      <AccountNavbar />
      <div className="p-6 text-center">
        <h2 className="text-2xl font-bold text-orange-600 mb-4">
          Rate this website
        </h2>

        <div className="flex justify-center space-x-2 mb-4">
          {[1, 2, 3, 4, 5].map((star) => (
            <button
              key={star}
              type="button"
              onClick={() => setRating(star)}
              onMouseEnter={() => setHover(star)}
              onMouseLeave={() => setHover(0)}
              className="text-4xl focus:outline-none"
            >
              <span
                className={`${
                  star <= (hover || rating)
                    ? "text-yellow-400"
                    : "text-gray-400"
                }`}
              >
                ★
              </span>
            </button>
          ))}
        </div>

        <p className="text-lg text-green-700">
          {rating > 0
            ? `You rated this site ${rating} star${rating > 1 ? "s" : ""}!`
            : "Click on the stars to give your review"}
        </p>


        <h2 className="text-2xl font-bold text-orange-600 mb-4">Add comment</h2>
        <textarea
          className="w-full border border-green-200 rounded-lg p-2 mb-4"
          rows="4"
          placeholder="Write your comment here..."
          onChange={(e) => setComment(e.target.value)}
        ></textarea>


        <button
            type="button"
            onClick={handleSubmit}
            className="px-4 py-2 bg-green-600 text-white rounded-lg hover:bg-green-700 transition-colors"
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

      <div className="min-h-screen text-green-600 ml-4">
        <h2 className="text-2xl font-bold text-orange-600 mb-4 underline">My reviews</h2>
        <ol className="space-y-4">
          {[...reviews].reverse().map((review) => (
            <li key={review.id} className="shadow-xl p-6 rounded-lg bg-white border-l-4 border-orange-600">
              <div className="space-y-2">
                <div>
                  <span className="text-orange-600 font-medium mr-2">Rating:</span>
                  <span className="text-lg">{review.rating}/5 ⭐</span>
                </div>
                <div>
                  <span className="text-orange-600 font-medium mr-2">Comment:</span>
                  <span>{review.comment}</span>
                </div>
                <div>
                  <span className="text-orange-600 font-medium mr-2">Date:</span>
                  <span className="text-gray-500">{review.createdAT}</span>
                </div>
                 <button
                    onClick={() => deleteReview(review.id)}
                    className="text-white bg-red-600 rounded-lg p-2 ml-2"
                  >
                    Delete
                  </button>
              </div>
            </li>
          ))}
        </ol>
      </div>
    </div>
  )
}

export default Review