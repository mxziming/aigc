import request from '@/utils/request'



export function updateImage(files,description){
    return request({
        url: '/source/uploadImage',
        method: 'post',
        data: {
            files: files,
            description: description
        },
    })
}
