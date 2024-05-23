import request from '@/utils/request'

export function relative() {
  return request({
    url: '/relative/info',
    method: 'get',
  });
}


export function addrelative(relative) {
    return request({
      url: `/relative/add`,
      method: 'post',
      data: relative,
    });
  }

  export function getrelative(rid) {
    return request({
      url: '/relative/'+String(rid),
      method: 'get',
    });
  }


//   axios.get(`/api/relative/${rid}`, {
//     headers: {
//       'Authorization': token // 确保Bearer格式
//     }
//   })