import axios from "axios";

export function login(data) {
    console.log(data);
    return axios.post('/user/Login', data);
}





