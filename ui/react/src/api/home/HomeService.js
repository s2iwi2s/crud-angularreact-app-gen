import axios from "axios";
import { URL_BASE } from '../Utils.js'

class HomeService {
 getList = () => {
  const theurl = `${URL_BASE}/home/list`
  return axios.get(theurl);
 }
 get = (id) => {
  const theurl = `${URL_BASE}/home/${id}`
  console.log(`[HomeService.get] theurl ${theurl}`);
  return axios.get(theurl);
 }
 save = (data) => {
  const theurl = `${URL_BASE}/home/save`
  console.log(`[HomeService.save] theurl ${theurl}`);
  console.log(`[HomeService.save] data`, data);
  return axios.post(theurl, data);
 }
}

export default new HomeService();