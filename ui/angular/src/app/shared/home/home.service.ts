import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { URL_BASE } from '../constants'

@Injectable({
  providedIn: 'root',
})
export class HomeService {
  public SERVER_API = URL_BASE + '/home'
  constructor(private http: HttpClient) { }

  getAll(page: any, pageSize: any): Observable<any> {
    let url = `${this.SERVER_API}/list?page=${page}&size=${pageSize}`;
    console.log(`[HomeService.getAll] url=${url}`);
    return this.http.get(url)
  }

  get(id: string) {
    return this.http.get(this.SERVER_API + '/' + id)
  }

  save(home: any): Observable<any> {
    let result: Observable<Object>

    //		if (home['href']) {
    //			result = this.http.put(home.href, home);
    //		} else {
    //		myCases.status = {"id": myCases["status"]};
    //		myCases.caseType1 = {"id": myCases["caseType1"]};
    //		myCases.caseType2 = {"id": myCases["caseType2"]};
    //		myCases.caseType3 = {"id": myCases["caseType3"]};
    //		myCases.statusCode = {"id": myCases["statusCode"]};
    result = this.http.post(this.SERVER_API + '/save', home)
    //		}
    return result
  }
  remove(id: string) {
    console.info('HomeService remove==>>' + id)
    return this.http.get(this.SERVER_API + '/delete/' + id)
    //return this.http.delete(this.SERVER_API+'/delete/' + href);
  }
}
