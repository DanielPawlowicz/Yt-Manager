import Service from "./Service";

class Video {

    // saving video to database
    addToPlaylist = async (video, playlistId) => {
        const ytId = video.id.videoId;
        const title = video.snippet.title;
        const link = `https://www.youtube.com/watch?v=${video.id.videoId}`;
        const duration = video.duration;
        const thumbnailUrl = video.snippet.thumbnails.default.url;

            try{
                // check if there is video of this ytId in database
                const ifVideoExists = await Service.ifExists(ytId);

                // if there is no video of this ytId in database
                if(!ifVideoExists.data){
                    // add video to database
                    await Service.addVideoToDb({ytId, title, link, duration, thumbnailUrl});
                }

                // get the max orderIndex from this playlist
                let order = await Service.maxOrder(playlistId);
                order = order.data + 1;

                // add video to playlist
                await Service.addVideoToPlaylist(ytId, playlistId, order);

                showNotification("Video added successfully");

            } catch (er) {
                console.error("Error adding to database: " + er);
            }

            function showNotification(message) {
                const notification = document.getElementById('notification');
                notification.textContent = message;
                notification.classList.add('show');
            
                setTimeout(() => { notification.classList.remove('show'); }, 1500);
            }
      };

    parseDuration = (duration) => {

        if (!duration) {
            return 'Duration not available';
        }
        
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
    
    //   createPlaylist  = async (video, playlistName) => {
    //     try{
    //         let playlistId = await Service.createNewPlaylist(playlistName);
    //         addToPlaylist(video, playlistId)
            
    //     } catch (er){
    //         console.error("Error creating new playlist: ", er);
    //     }

    //   }

}

export default new Video;