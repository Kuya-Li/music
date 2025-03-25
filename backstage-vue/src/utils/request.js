import axios from 'axios';
import router from "@/router";
import { serverIp } from "../../public/config";
import ElementUI from "element-ui";

const request = axios.create({
    baseURL: `http://${serverIp}:9090`,
    timeout: 30000
});

let hasShownError = false; // 标志是否已弹出错误提示

// request 拦截器
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    let user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null;
    if (user) {
        config.headers['token'] = user.token;  // 设置请求头
    }
    return config;
}, error => {
    return Promise.reject(error);
});

// response 拦截器
request.interceptors.response.use(
    response => {
        let res = response.data;
        if (response.config.responseType === 'blob') {
            return res;
        }
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res;
        }
        if (res.code === '401' && !hasShownError) {
            hasShownError = true; // 标志为已弹出错误提示
            ElementUI.Message({
                message: res.msg,
                type: 'error',
                onClose: () => {
                    router.replace("/login");
                }
            });
            // 移除缓存
            localStorage.removeItem("user")
        }
        return res;
    },
    error => {
        console.log('err' + error); // for debug
        return Promise.reject(error);
    }
);

export default request;
