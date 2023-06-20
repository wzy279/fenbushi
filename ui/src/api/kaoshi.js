import axios from "axios";
// 获取存储的 token
const token = localStorage.getItem('Token');

// 设置默认的请求头
axios.defaults.headers.common['Token'] = token;
export function getQuestion() {
    return axios.post('/question/GetQuestion');
}


//提交题目CommitQuestion
export function CommitQuestion(data) {
    let mm= {
        "data": data,
    }
    return axios.post('/question/CommitQuestion',mm);
}



