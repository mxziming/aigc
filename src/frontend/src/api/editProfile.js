import { getAccessToken } from '@/utils/auth';
import axios from 'axios';

export function save(userForm){
    const token = getAccessToken();
    return axios.post('/api/user/update', userForm, {
        headers: {
          'Authorization': token
        }
      })
}