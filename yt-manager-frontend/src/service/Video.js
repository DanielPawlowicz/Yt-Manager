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

            } catch (er) {
                console.error("Error adding to database: " + er);
            }
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