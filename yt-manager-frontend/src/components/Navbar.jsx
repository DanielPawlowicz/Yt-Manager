import React, { useEffect, useState } from 'react'
import Service from '../service/Service';
import SearchIcon from '@mui/icons-material/Search';
import { Icon } from '@mui/material';

const Navbar = ({ setSelectedPlaylist, setShowSearch }) => {

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

    const handleTitleClick = () => {
        setShowSearch(true); // Update state to show SearchYoutube component
        setSelectedPlaylist(null); // Reset selected playlist
    };

    const handlePlaylistClick = (playlist) => {
        setSelectedPlaylist(playlist); // Update the selected playlist
        setShowSearch(false); // Set showSearch to false to hide the SearchYoutube component
    };

  return (
    <div className='Navbar'>
    
        <h1 className='title' onClick={handleTitleClick}>YT Manager</h1>
        <h2 className="search-link" onClick={handleTitleClick}>Search <SearchIcon className='search-icon'/></h2>

        <h2 className='playlists-title'>Playlists</h2>

        {playlists.map((playlist) => (
            <h4 className='playlist-item-nav' key={playlist.id} onClick={() => handlePlaylistClick(playlist)}>{playlist.playlistName}</h4>
        ))}

    
    </div>
  )
}

export default Navbar