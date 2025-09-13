import React from 'react'

const InputField = ({label, type, value, onChange, placeholder}) => {
  return (
    <div className="flex flex-col">
      <label className="mb-1 text-white font-medium">{label}</label>
      <input 
        type={type}
        value={value}
        onChange={onChange}
        placeholder={placeholder}
        className='border border-white/50 rounded-lg p-2 bg-white/10 text-white placeholder-white/70 focus:outline-none focus:ring-2 focus:ring-orange-400'
      
      />
      
    </div>
  )
}

export default InputField
