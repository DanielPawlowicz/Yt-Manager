import axios from "axios";

const API_URL = "http://localhost:8080";

class Service {

    // add video to database
    addVideoToDb(video){
        return axios.post(API_URL+"/videos/add", video);
    }

    // assign video to To Watch playlist (id: 1)
    addVideoToPlaylist(ytId, playlistId){
        // here should i ask for the max orderIndex for playlist Id and assign one above
        return axios.post(API_URL+"/video-playlists/assign", {ytId, playlistId, orderIndex: 1});
    }

    getPlaylists(){
        return axios.get(API_URL+"/playlists/all");
    }
}

export default new Service;