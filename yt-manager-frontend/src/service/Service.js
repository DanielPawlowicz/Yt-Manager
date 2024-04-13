import axios from "axios";

const API_URL = "http://localhost:8080";

class Service {

    // add video to database
    addVideoToDb(video){
        return axios.post(API_URL+"/videos/add", video);
    }

    // assign video to To Watch playlist (id: 1)
    addVideoToPlaylist(ytId, playlistId, orderIndex){
        // here should i ask for the max orderIndex for playlist Id and assign one above
        console.log("from service: "+orderIndex);
        return axios.post(API_URL+"/video-playlists/assign", {ytId, playlistId, orderIndex});
    }

    getPlaylists(){
        return axios.get(API_URL+"/playlists/all");
    }

    ifExists(ytId){
        return axios.get(API_URL+"/videos/exists/"+ytId);
    }

    maxOrder(playlistId){
        return axios.get(API_URL+"/video-playlists/max-order-index/"+playlistId);
    }

    createNewPlaylist(playlistName){
        return axios.post(API_URL+"/playlists/create",{playlistName});
    }

    getPlaylistVideos(playlistId){
        return axios.get(API_URL+"/video-playlists/allVideoFrom/"+playlistId);
    }

    getVideoByYtId(ytId){
        return axios.get(API_URL+"/videos/ytId/"+ytId);
    }

    // setVideoWatched(id, updatedVideo){
    //     return axios.put(`/videos/edit/${id}`, updatedVideo); 
    // }

    editByYtId(ytId, updatedVideo){
        return axios.put(API_URL+"/videos/edit/ytId/"+ytId, updatedVideo);
    }

}

export default new Service;