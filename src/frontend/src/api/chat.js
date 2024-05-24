import request from '@/utils/request'

export function fetchhistory(){
    return request({
      url: `/chat/history`,
      method: 'get',
  })
}

export function fetchchat(sessionid){
    return request({
      url: `/chat/`+String(sessionid),
      method: 'get',
  })
}

export function sendquestion(question,sessionid){
  return request({
    url: `/chat/chat`,
    method: 'post',
    data: {
      question: question,
      sessionid: sessionid
    }
})
}

export function newchat(){
  return request({
    url: `/chat/newchat`,
    method: 'get'
})
}