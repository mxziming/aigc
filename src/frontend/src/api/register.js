import request from '@/utils/request'

export function register(userData) {
  return request({
    url: '/user/add',
    method: 'post',
    data: userData
  });
}

