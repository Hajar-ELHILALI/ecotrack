import React from 'react'

const Popup = ({ isOpen, message, type, onClose }) => {
  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg p-6 max-w-sm w-full mx-4">
        <div className="text-center">
          <div className={`text-4xl mb-4 ${type === 'success' ? 'text-green-500' : 'text-red-500'}`}>
            {type === 'success' ? '✓' : '✗'}
          </div>
          <h3 className={`text-lg font-semibold mb-2 ${type === 'success' ? 'text-green-800' : 'text-red-800'}`}>
            {type === 'success' ? 'Success!' : 'Error!'}
          </h3>
          <p className={`mb-4 ${type === 'success' ? 'text-green-600' : 'text-red-600'}`}>
            {message}
          </p>
          <button
            onClick={onClose}
            className={`px-4 py-2 rounded-lg text-white font-medium ${
              type === 'success' 
                ? 'bg-green-500 hover:bg-green-600' 
                : 'bg-red-500 hover:bg-red-600'
            } transition-colors`}
          >
            OK
          </button>
        </div>
      </div>
    </div>
  );
};

export default Popup;