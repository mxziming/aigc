import request from '@/utils/request'

// 用户注册
export function register(userData) {
  return request({
    url: '/user/add',
    method: 'post',
    data: userData
  });
}