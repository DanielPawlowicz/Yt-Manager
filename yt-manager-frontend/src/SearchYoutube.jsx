import React, { useState } from 'react';
import axios from 'axios';

const API_KEY = 'AIzaSyCCd-2OgVAdtWYRIWQ6JapPXYB1-IjSESg';

const SearchYoutube = () => {
    const [query, setQuery] = useState('');
    const [results, setResults] = useState([]);
  
    const handleChange = (event) => {
      setQuery(event.target.value);
    };
  
    const handleSubmit = async (event) => {
      event.preventDefault();
      try {
        const response = await axios.get(
          `https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&q=${query}&key=${API_KEY}`
        );
        const videoIds = response.data.items.map((item) => item.id.videoId);
        const videoDetailsResponse = await axios.get(
          `https://www.googleapis.com/youtube/v3/videos?part=snippet,contentDetails&id=${videoIds.join(
            ','
          )}&key=${API_KEY}`
        );
    
        // Check if videoDetailsResponse has items
        if (!videoDetailsResponse.data.items) {
          throw new Error('No video details found');
        }
    
        const videoDetails = videoDetailsResponse.data.items;
        const resultsWithDetails = response.data.items.map((item, index) => {
          return {
            ...item,
            duration: videoDetails[index]?.contentDetails?.duration || 'N/A',
          };
        });
        setResults(resultsWithDetails);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    
    const parseDuration = (duration) => {
      const match = duration.match(/PT(\d+H)?(\d+M)?(\d+S)?/);
    
      if (!match) {
        return 'N/A';
      }
    
      const hours = match[1] ? parseInt(match[1]) : 0;
      const minutes = match[2] ? parseInt(match[2]) : 0;
      const seconds = match[3] ? parseInt(match[3]) : 0;
    
      let durationString = '';
      if (hours > 0) {
        durationString += `${hours}h `;
      }
      if (minutes > 0) {
        durationString += `${minutes}m `;
      }
      if (seconds > 0) {
        durationString += `${seconds}s `;
      }
    
      return durationString.trim();
    };
  
    return (
      <div>
        <form onSubmit={handleSubmit}>
          <input type="text" value={query} onChange={handleChange} />
          <button type="submit">Search</button>
        </form>
        <ul>
          {results.map((item) => (
            <li key={item.id.videoId}>
              <a href={`https://www.youtube.com/watch?v=${item.id.videoId}`} target="_blank" rel="noopener noreferrer">
                <img src={item.snippet.thumbnails.default.url} alt={item.snippet.title} />
                {item.snippet.title}
              </a>
              <p>Duration: {parseDuration(item.duration)}</p>
            </li>
          ))}
        </ul>
      </div>
    );
}

export default SearchYoutube