import request from '@/utils/request'

export function fetchhistory(){
    return request({
      url: `/chat/chathistory`,
      method: 'get',
  })
}

export function fetchchat(sessionid){
    return request({
      url: `/chat/`+String(sessionid),
      method: 'get',
  })
}