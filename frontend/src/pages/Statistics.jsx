import React, { useEffect, useState } from "react";
import axios from "axios";
import { LineChart, Line, CartesianGrid, XAxis, YAxis, Tooltip, Legend, ResponsiveContainer, BarChart, Bar, PieChart, Pie,Cell,AreaChart,Area} from "recharts";
import AccountNavbar from "../components/AccountNavbar";

const Statistics = () => {
  const [stats, setStats] = useState({
    transport: [],
    electricity: [],
    nutrition: [],
  });
  const [period, setPeriod] = useState("daily");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [activeChart, setActiveChart] = useState("line");

  useEffect(() => {
    const fetchStats = async () => {
      setLoading(true);
      setError(null);

      try {
        const token = localStorage.getItem("jwtToken");
        const response = await axios.get(
          `http://localhost:8080/api/scores/statistics?period=${period}`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );
        setStats(response.data);
      } catch (err) {
        console.error("Error loading statistics:", err);
        setError("Failed to load statistics");
      } finally {
        setLoading(false);
      }
    };

    fetchStats();
  }, [period]);

  const getPeriodLabel = () => {
    switch (period) {
      case "daily":
        return "Daily";
      case "monthly":
        return "Monthly";
      case "yearly":
        return "Yearly";
      default:
        return "Daily";
    }
  };

  const formatDate = (dateStr) => {
    if (period === "daily") {
      return new Date(dateStr).toLocaleDateString("en-US", {
        day: "2-digit",
        month: "short",
      });
    } else if (period === "monthly") {
      return new Date(dateStr + "-01").toLocaleDateString("en-US", {
        month: "short",
        year: "2-digit",
      });
    }
    return dateStr;
  };

  const getCombinedData = () => {
    if (!stats.transport?.length) return [];

    return stats.transport.map((item, index) => ({
      date: item.date,
      transport: item.totalCo2,
      electricity: stats.electricity[index]?.totalCo2 || 0,
      nutrition: stats.nutrition[index]?.totalCo2 || 0,
    }));
  };

  const getTotals = () => {
    const transportTotal =
      stats.transport?.reduce((sum, item) => sum + item.totalCo2, 0) || 0;
    const electricityTotal =
      stats.electricity?.reduce((sum, item) => sum + item.totalCo2, 0) || 0;
    const nutritionTotal =
      stats.nutrition?.reduce((sum, item) => sum + item.totalCo2, 0) || 0;

    return {
      transport: transportTotal,
      electricity: electricityTotal,
      nutrition: nutritionTotal,
      total: transportTotal + electricityTotal + nutritionTotal,
    };
  };

  const getPieData = () => {
    const totals = getTotals();
    return [
      { name: "Transport", value: totals.transport, color: "#3B82F6" },
      { name: "Electricity", value: totals.electricity, color: "#EF4444" },
      { name: "Nutrition", value: totals.nutrition, color: "#10B981" },
    ].filter((item) => item.value > 0);
  };

  const renderChart = (data, title, color, category) => {
    const chartTypes = {
      line: (
        <LineChart data={data}>
          <CartesianGrid strokeDasharray="3 3" stroke="#f0f0f0" />
          <XAxis dataKey="date" tickFormatter={formatDate} stroke="#6b7280" />
          <YAxis stroke="#6b7280" />
          <Tooltip
            formatter={(value) => [`${value.toFixed(2)} kg CO₂`, title]}
            labelFormatter={(label) => `Date: ${formatDate(label)}`}
          />
          <Line
            type="monotone"
            dataKey="totalCo2"
            stroke={color}
            strokeWidth={3}
            dot={{ fill: color, r: 4 }}
            activeDot={{ r: 6, stroke: color, strokeWidth: 2, fill: "#ffffff" }}
          />
        </LineChart>
      ),
      area: (
        <AreaChart data={data}>
          <defs>
            <linearGradient
              id={`areaGradient-${category}`}
              x1="0"
              y1="0"
              x2="0"
              y2="1"
            >
              <stop offset="5%" stopColor={color} stopOpacity={0.8} />
              <stop offset="95%" stopColor={color} stopOpacity={0.1} />
            </linearGradient>
          </defs>
          <CartesianGrid strokeDasharray="3 3" stroke="#f0f0f0" />
          <XAxis dataKey="date" tickFormatter={formatDate} stroke="#6b7280" />
          <YAxis stroke="#6b7280" />
          <Tooltip
            formatter={(value) => [`${value.toFixed(2)} kg CO₂`, title]}
            labelFormatter={(label) => `Date: ${formatDate(label)}`}
          />
          <Area
            type="monotone"
            dataKey="totalCo2"
            stroke={color}
            strokeWidth={2}
            fillOpacity={1}
            fill={`url(#areaGradient-${category})`}
          />
        </AreaChart>
      ),
      bar: (
        <BarChart data={data}>
          <CartesianGrid strokeDasharray="3 3" stroke="#f0f0f0" />
          <XAxis dataKey="date" tickFormatter={formatDate} stroke="#6b7280" />
          <YAxis stroke="#6b7280" />
          <Tooltip
            formatter={(value) => [`${value.toFixed(2)} kg CO₂`, title]}
            labelFormatter={(label) => `Date: ${formatDate(label)}`}
          />
          <Bar dataKey="totalCo2" fill={color} radius={[4, 4, 0, 0]} />
        </BarChart>
      ),
    };

    return (
      <div className="bg-white rounded-2xl shadow-lg p-6">
        <div className="flex items-center justify-between mb-4">
          <h3 className="text-xl font-bold text-gray-800">{title}</h3>
          <p className="text-lg font-semibold" style={{ color }}>
            {data?.reduce((sum, item) => sum + item.totalCo2, 0).toFixed(1)} kg
          </p>
        </div>
        <ResponsiveContainer width="100%" height={280}>
          {chartTypes[activeChart]}
        </ResponsiveContainer>
      </div>
    );
  };

  if (loading) return <p className="text-center p-8">Loading statistics...</p>;
  if (error) return <p className="text-center p-8 text-red-500">{error}</p>;

  const totals = getTotals();
  const combinedData = getCombinedData();
  const pieData = getPieData();

  return (
    <div className="min-h-screen bg-gray-50">
      <AccountNavbar />
      <div className="max-w-7xl mx-auto px-6 py-10">
        <div className="bg-white rounded-2xl shadow-lg p-6 mb-8 flex flex-col md:flex-row items-center justify-between">
          <div>
            <h1 className="text-3xl font-bold text-gray-900 mb-2">
              📊 CO₂ Emissions Statistics
            </h1>
            <p className="text-gray-600">
              Detailed analysis of your carbon footprint - {getPeriodLabel()}
            </p>
          </div>
          <div className="flex space-x-4 mt-4 md:mt-0">
            <select
              value={period}
              onChange={(e) => setPeriod(e.target.value)}
              className="border rounded-lg px-4 py-2"
            >
              <option value="daily">Daily</option>
              <option value="monthly">Monthly</option>
              <option value="yearly">Yearly</option>
            </select>
            <select
              value={activeChart}
              onChange={(e) => setActiveChart(e.target.value)}
              className="border rounded-lg px-4 py-2"
            >
              <option value="line">Line</option>
              <option value="area">Area</option>
              <option value="bar">Bar</option>
            </select>
          </div>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-4 gap-6 mb-8">
          <div className="bg-indigo-500 text-white rounded-2xl p-6">
            <p>Total</p>
            <h2 className="text-3xl font-bold">{totals.total.toFixed(1)} kg</h2>
          </div>
          <div className="bg-blue-500 text-white rounded-2xl p-6">
            <p>Transport</p>
            <h2 className="text-2xl font-bold">{totals.transport.toFixed(1)} kg</h2>
          </div>
          <div className="bg-red-500 text-white rounded-2xl p-6">
            <p>Electricity</p>
            <h2 className="text-2xl font-bold">{totals.electricity.toFixed(1)} kg</h2>
          </div>
          <div className="bg-green-500 text-white rounded-2xl p-6">
            <p>Nutrition</p>
            <h2 className="text-2xl font-bold">{totals.nutrition.toFixed(1)} kg</h2>
          </div>
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-3 gap-8 mb-8">
          {renderChart(stats.transport, "Transport", "#3B82F6", "transport")}
          {renderChart(stats.electricity, "Electricity", "#EF4444", "electricity")}
          {renderChart(stats.nutrition, "Nutrition", "#10B981", "nutrition")}
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
          <div className="bg-white rounded-2xl shadow-lg p-6">
            <h3 className="text-xl font-bold text-gray-800 mb-4">
               Comparative Evolution
            </h3>
            <ResponsiveContainer width="100%" height={350}>
              <LineChart data={combinedData}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="date" tickFormatter={formatDate} />
                <YAxis />
                <Tooltip />
                <Legend />
                <Line dataKey="transport" stroke="#3B82F6" />
                <Line dataKey="electricity" stroke="#EF4444" />
                <Line dataKey="nutrition" stroke="#10B981" />
              </LineChart>
            </ResponsiveContainer>
          </div>
          <div className="bg-white rounded-2xl shadow-lg p-6">
            <h3 className="text-xl font-bold text-gray-800 mb-4">
               Emissions Breakdown
            </h3>
            <ResponsiveContainer width="100%" height={350}>
              <PieChart>
                <Pie
                  data={pieData}
                  cx="50%"
                  cy="50%"
                  labelLine={false}
                  label={({ name, percent }) =>
                    `${name}: ${(percent * 100).toFixed(0)}%`
                  }
                  outerRadius={100}
                  fill="#8884d8"
                  dataKey="value"
                >
                  {pieData.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={entry.color} />
                  ))}
                </Pie>
              </PieChart>
            </ResponsiveContainer>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Statistics;