import axios from "axios";
// 获取存储的 token
const token = localStorage.getItem('Token');

// 设置默认的请求头
axios.defaults.headers.common['Token'] = token;
export function LookHistory() {
    console.log(token)
    return axios.post('/question/LookHistory');
}





