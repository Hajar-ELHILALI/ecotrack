import React, { useState, useEffect } from 'react';
import axios from 'axios';
import AccountNavbar from '../components/AccountNavbar';

const Advices = () => {
  const [advices, setAdvices] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchAdvicesWithContent();
  }, []);

  const fetchAdvices = async () => {
    const token = localStorage.getItem("jwtToken");
    const response = await axios.get("http://localhost:8080/api/historic-advice/me", {
      headers: { Authorization: `Bearer ${token}` }
    });
    return response.data; 
  };

  const fetchAdviceDetails = async (adviceId) => {
    const response = await axios.get(`/api/advices/${adviceId}`);
    return response.data; 
  };

  const fetchAdvicesWithContent = async () => {
    setLoading(true);
    setError(null);
    try {
      const advicesHistory = await fetchAdvices();

      const advicesWithContent = await Promise.all(
        advicesHistory.map(async (adv) => {
          const adviceDetail = await fetchAdviceDetails(adv.adviceId);
          return {
            ...adv,
            adviceContent: adviceDetail.content,
            type: adviceDetail.type
          };
        })
      );

      setAdvices(advicesWithContent);
    } catch (err) {
      console.error(err);
      setError("Unable to load advices.");
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <p className="text-center mt-10">Loading...</p>;
  if (error) return <p className="text-center mt-10 text-red-500">{error}</p>;

  return (
    <div>
      <AccountNavbar />
      <div className="max-w-4xl mx-auto mt-10 bg-white shadow rounded-lg p-6">
        <h1 className="text-2xl font-bold mb-4 text-orange-600 underline">My Advices</h1>
        {advices.length === 0 ? (
          <p className="text-orange-600">No advices found.</p>
        ) : (
          <ul className="divide-y divide-gray-200">
            {advices.map((adv) => (
              <li key={adv.id} className="py-4">
                <div className="flex justify-between">
                  <div>
                    <p className="font-medium text-gray-900">{adv.adviceContent || "Advice"}</p>
                    <p className="text-sm text-gray-500">Type: {adv.type || "N/A"}</p>
                  </div>
                  <div>
                    <p className="text-sm text-gray-400">
                      Sent on {adv.sentDate ? new Date(adv.sentDate).toLocaleDateString("en-US") : "N/A"}
                    </p>
                    {adv.isApplied ? (
                      <span className="text-green-600 text-sm">Applied ✅</span>
                    ) : (
                      <span className="text-red-600 text-sm">Not applied ❌</span>
                    )}
                  </div>
                </div>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
};

export default Advices;
