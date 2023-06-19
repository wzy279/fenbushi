import axios from "axios";

export function getVerification(data) {
    console.log(data);
    return axios.post('/user/retrievePassword1', data);
}


export function getVerification2(data) {
    console.log(data);
    return axios.post('/user/retrievePassword2', data);
}





