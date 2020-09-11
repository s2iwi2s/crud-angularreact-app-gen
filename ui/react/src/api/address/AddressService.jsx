import axios from "axios";
import { URL_BASE } from '../Utils.js'

class AddressService {

 getList = (page, pageSize) => {
  const theurl = `${URL_BASE}/addresss?page=${page}&size=${pageSize}`
  return axios.get(theurl);
 }
 get = (id) => {
  const theurl = `${URL_BASE}/addresss/${id}`
  return axios.get(theurl);
 }
 getByUser = (endUserId) => {
  const theurl = `${URL_BASE}/addresss/END_USER/${endUserId}`
  return axios.get(theurl);
 }
 delete = (id) => {
  const theurl = `${URL_BASE}/addresss/${id}`
  return axios.delete(theurl);
 }
 save = (data) => {
  const theurl = `${URL_BASE}/addresss`
  console.log(`[AddressService.save] theurl=${theurl}, data=>`, data);
  return axios.post(theurl, data);
 }
}

export default new AddressService();


