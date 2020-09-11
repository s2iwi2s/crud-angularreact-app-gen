import axios from "axios";
import { URL_BASE } from '../Utils.js'

class EndUserService {

 getList = (page, pageSize) => {
  const theurl = `${URL_BASE}/endUsers?page=${page}&size=${pageSize}`
  return axios.get(theurl);
 }
 get = (id) => {
  const theurl = `${URL_BASE}/endUsers/${id}`
  return axios.get(theurl);
 }
 delete = (id) => {
  const theurl = `${URL_BASE}/endUsers/${id}`
  return axios.delete(theurl);
 }
 save = (data) => {
  const theurl = `${URL_BASE}/endUsers`
  console.log(`[EndUserService.save] ${theurl}=>`, data)
  return axios.post(theurl, data);
 }
}

export default new EndUserService();


