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
                    alert("Video added to database successfully");
                }

                let order = await Service.maxOrder(playlistId);
                console.log(order.data);
                let order1 = order.data + 1;

                console.log(order1);

                // add video to playlist
                await Service.addVideoToPlaylist(ytId, playlistId, order1);

            } catch (er) {
                console.error("Error adding to database: " + er);
                alert("Filed to add video to database");
            }
      };
  
      // save video to another playlist
    //   addToPlaylist = async (video) => {
    //     // await Service.getPlaylists();
    //     // console.log("works"+video);

    //   }
    

}

export default new Video;