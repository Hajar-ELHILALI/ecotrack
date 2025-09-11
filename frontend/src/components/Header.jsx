
import Navbar from './Navbar'
import { background } from '../assets'
import About from './About'

const Header = () => {
  return (
    <div className='min-h-screen mb-4 bg-cover bg-center flex items-center w-full overflow-hidden' style={{backgroundImage:  `url(${background})`}} id='Header'>
      <Navbar/>
      <div className='container text-center mx-auto py-4 px-6 md:px-20 lg:px-32'>
        <h2 className="text-4xl sm:text-6xl md:text-8xl font-bold text-orange-500 pt-20 leading-tight">
        Welcome to EcoTrack
        </h2> 
       
      </div>
    </div>
  )
}

export default Header
