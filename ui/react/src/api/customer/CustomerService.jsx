import axios from "axios";
import { URL_BASE } from '../Utils.js'

class CustomerService {

 getList = (page, pageSize) => {
  const theurl = `${URL_BASE}/customers?page=${page}&size=${pageSize}`
  return axios.get(theurl);
 }
 get = (id) => {
  const theurl = `${URL_BASE}/customers/${id}`
  return axios.get(theurl);
 }
 delete = (id) => {
  const theurl = `${URL_BASE}/customers/${id}`
  return axios.delete(theurl);
 }
 save = (data) => {
  const theurl = `${URL_BASE}/customers`
  return axios.post(theurl, data);
 }
}

export default new CustomerService();


