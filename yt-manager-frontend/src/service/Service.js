import axios from "axios";

const API_URL = "http://localhost:8080";

class Service {

    addVideoToDb(video){
        return axios.post(API_URL+"/videos/add", video);
    }

}

export default new Service;