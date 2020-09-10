import axios from "axios";
import { URL_BASE } from '../Utils.js'

class ProductService {

 getList = (page, pageSize) => {
  const theurl = `${URL_BASE}/products?page=${page}&size=${pageSize}`
  return axios.get(theurl);
 }
 get = (id) => {
  const theurl = `${URL_BASE}/products/${id}`
  return axios.get(theurl);
 }
 delete = (id) => {
  const theurl = `${URL_BASE}/products/${id}`
  return axios.delete(theurl);
 }
 save = (data) => {
  const theurl = `${URL_BASE}/products`
  return axios.post(theurl, data);
 }
}

export default new ProductService();


