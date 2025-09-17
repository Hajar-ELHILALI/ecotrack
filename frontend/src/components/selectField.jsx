import React from 'react';

const SelectField = ({ label, value, onChange, options, valueKey = 'id', labelKey = 'name' }) => {
  return (
    <div className="mb-4 text-orange-700">
      <label className="mb-1 block text-orange-600 font-medium">{label}</label>
      <select
        value={value}
        onChange={onChange}
        className="border border-green-700 rounded-lg p-2 w-full"
      >
        <option value="">Select {label}</option>
        {options.map(option => (
          <option key={option[valueKey]} value={option[valueKey]}>
            {option[labelKey]}
          </option>
        ))}
      </select>
    </div>
  );
};

export default SelectField;
