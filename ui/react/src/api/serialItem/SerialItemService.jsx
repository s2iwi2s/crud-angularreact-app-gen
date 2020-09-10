import axios from "axios";
import { URL_BASE } from '../Utils.js'

class SerialItemService {

 getList = (page, pageSize) => {
  const theurl = `${URL_BASE}/serialItems?page=${page}&size=${pageSize}`
  return axios.get(theurl);
 }
 get = (id) => {
  const theurl = `${URL_BASE}/serialItems/${id}`
  return axios.get(theurl);
 }
 delete = (id) => {
  const theurl = `${URL_BASE}/serialItems/${id}`
  return axios.delete(theurl);
 }
 save = (data) => {
  const theurl = `${URL_BASE}/serialItems`
  return axios.post(theurl, data);
 }
}

export default new SerialItemService();


