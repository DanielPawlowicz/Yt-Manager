import React, { useState } from 'react';
import SearchYoutube from './components/SearchYoutube';
import Navbar from './components/Navbar';

const API_KEY = 'AIzaSyCCd-2OgVAdtWYRIWQ6JapPXYB1-IjSESg';

function App() {
  return(
    <>
      <div className='nav-panel'>
        <Navbar/>
      </div>
      <div className='main-panel'>
        <SearchYoutube/>
      </div>
      <div style={{clear:'both'}}></div>
    </>
  );
}

export default App;