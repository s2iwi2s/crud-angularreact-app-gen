import axios from "axios";
import { URL_BASE } from '../Utils.js'

class PartItemService {

 getList = (page, pageSize) => {
  const theurl = `${URL_BASE}/partItems?page=${page}&size=${pageSize}`
  return axios.get(theurl);
 }
 get = (id) => {
  const theurl = `${URL_BASE}/partItems/${id}`
  return axios.get(theurl);
 }
 delete = (id) => {
  const theurl = `${URL_BASE}/partItems/${id}`
  return axios.delete(theurl);
 }
 save = (data) => {
  const theurl = `${URL_BASE}/partItems`
  return axios.post(theurl, data);
 }
}

export default new PartItemService();


