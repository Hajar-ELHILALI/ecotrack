import React, {useEffect, useState} from 'react'
import {profil, list, advice, goals, addActivity, statistics, badge, satisfaction} from '../assets'
import Card from '../components/Card'
import AccountNavbar from '../components/AccountNavbar'

const HomePage = () => {
  
  return (
    <div className="h-min-screen ">
      <AccountNavbar/>

      <div className="grid grid-cols-1 sm:grid-cols-3 md:grid-cols-4 gap-2 p-4 ">
          <Card image={profil} title="Account" href="/myAccount"/> 
          <Card image={addActivity} title="Add activity" href="/addActivity"/>
          <Card image={list} title="Activities list" href="/activities"/>
          <Card image={statistics} title="Statistics" href="/statistics"/>
          <Card image={advice} title="Advices" href="/advices"/>
          <Card image={goals} title="Goals" href="/goals"/> 
          <Card image={badge} title="Badge" href="/badge"/>
           <Card image={satisfaction} title=" Add review" href="/review"/>
      </div>

    </div>
  )
}

export default HomePage
