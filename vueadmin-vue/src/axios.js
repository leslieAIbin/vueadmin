import axios from "axios";
import router from "./router";
import Element from "element-ui";
// 拦截器
// axios.defaults.baseURL = "http://localhost:8081"  //全局定义UI

const request = axios.create({
    timeout: 5000,  // ms
    headers: {      // json数据
        'Content-Type': "application/json; charset=utf-8"
    }
})

// 进行拦截
request.interceptors.request.use(config => {
    config.headers['Authorization'] = localStorage.getItem('token')
    return config
})

// 返回信息
request.interceptors.response.use( response => {
        console.log("response ->" + response)
        let res = response.data

        if (res.code === 200) {
            return response
        } else {
            Element.Message.error(!res.msg ? '系统异常' : res.msg)
            return Promise.reject(response.data.msg)   // 拒绝
        }
    },
    error => {
    console.log(error)
    if(error.response.data){
        error.message = error.response.data.msg
    }
    // 返回页面
    if(error.response.status === 401){
        router.push("/login")
    }

    Element.Message.error(error.message, {duration: 3000})
    return Promise.reject(error)
    }
)

// 暴露出去
export default request