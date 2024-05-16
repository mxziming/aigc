import request from '@/utils/request'

export function login(username, password) {
    const data = {
      username,
      password
    };
  
    // 返回一个 Promise 对象
    return new Promise((resolve, reject) => {
      request({
        url: '/user/login',
        method: 'post',
        data: data
      })
      .then(response => {
        // 登录成功时，存储 token 到本地存储中
        localStorage.setItem('token', response.token);
        // 在 Promise 的 resolve 函数中返回响应数据
        resolve(response);
      })
      .catch(error => {
        // 在 Promise 的 reject 函数中返回错误信息
        reject(error);
      });
    });
}
  
// 获取用户信息
// export function getInfo() {
//   return request({
//     url: '/system/user/profile/get',
//     method: 'get'
//   })
// }
