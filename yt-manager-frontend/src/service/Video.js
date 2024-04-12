import Service from "./Service";

class Video {

    // saving video to database
    addToDatabase = async (video) => {
        const ytId = video.id.videoId;
        const title = video.snippet.title;
        const link = `https://www.youtube.com/watch?v=${video.id.videoId}`;
        const duration = video.duration;
        const thumbnailUrl = video.snippet.thumbnails.default.url;
        try{
          // add video to database
          await Service.addVideoToDb({ytId, title, link, duration, thumbnailUrl});
          // add video to playlist To watch - id: 1
          await Service.addVideoToPlaylist(ytId, 1);
          alert("Video added to database successfully");
        } catch (er) {
          console.error("Error adding to database: " + er);
          alert("Filed to add video to database");
        }
      };
  
      // save video to another playlist
      addToPlaylist = async (video) => {
        // await Service.getPlaylists();
        // console.log("works"+video);

      }
    

}

export default new Video;