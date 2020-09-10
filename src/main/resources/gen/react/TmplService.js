import axios from "axios";
import { URL_BASE } from '../Utils.js'

class XYCLASSYXService {

 getList = (page, pageSize) => {
  const theurl = `${URL_BASE}/XYclassYXs?page=${page}&size=${pageSize}`
  return axios.get(theurl);
 }
 get = (id) => {
  const theurl = `${URL_BASE}/XYclassYXs/${id}`
  return axios.get(theurl);
 }
 delete = (id) => {
  const theurl = `${URL_BASE}/XYclassYXs/${id}`
  return axios.delete(theurl);
 }
 save = (data) => {
  const theurl = `${URL_BASE}/XYclassYXs`
  return axios.post(theurl, data);
 }
}

export default new XYCLASSYXService();