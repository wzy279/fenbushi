import axios from "axios";

export function regist(data) {
    console.log(data);
    return axios.post('/user/regist', data);
}
