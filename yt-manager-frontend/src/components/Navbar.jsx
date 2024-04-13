import React, { useEffect, useState } from 'react'
import Service from '../service/Service';

const Navbar = () => {

    const [playlists, setPlaylists] = useState([]);

    useEffect(()=>{
        loadPlaylists();
    }, []);

    const loadPlaylists = async () => {
        try{
            const response = await Service.getPlaylists();
            setPlaylists(response.data);
        } catch(err){
            console.error("Error fetching playlist for navbar: ", err);
        }
    }

  return (
    <div className='Navbar'>
    
        <h1 className='title'>YT Manager</h1>

        <h2>Playlists</h2>

        {playlists.map((playlist) => (
            <h4 className='playlist-item-nav' key={playlist.id}>{playlist.playlistName}</h4>
        ))}

    
    </div>
  )
}

export default Navbar