import axios from 'axios';
import { URL } from '../utilies/Url'
import authHeader from '../utilies/request_header'

export const signIn = authData => {
    return axios.post(`${URL}/auth/signin`, authData)
    .then(function (response) {
        if (response.status === 200) {
            localStorage.setItem("user", JSON.stringify(response.data));
            return response;
        }
    })
    .catch(function (error) {
        return error;
    });
}

export const getAllUsers = () => {
    return axios.get(`${URL}/users`, {headers: authHeader() });
}