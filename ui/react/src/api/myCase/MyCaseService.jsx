import axios from "axios";
import { URL_BASE } from '../Utils.js'

class MyCaseService {

 getList = (page, pageSize) => {
  const theurl = `${URL_BASE}/myCases?page=${page}&size=${pageSize}`
  return axios.get(theurl);
 }
 get = (id) => {
  const theurl = `${URL_BASE}/myCases/${id}`
  return axios.get(theurl);
 }
 delete = (id) => {
  const theurl = `${URL_BASE}/myCases/${id}`
  return axios.delete(theurl);
 }
 save = (data) => {
  const theurl = `${URL_BASE}/myCases`
  return axios.post(theurl, data);
 }
}

export default new MyCaseService();


