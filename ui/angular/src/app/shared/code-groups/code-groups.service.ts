import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { URL_BASE } from '../constants'

@Injectable({
  providedIn: 'root',
})
export class CodeGroupsService {
  public SERVER_API = URL_BASE + '/codeGroups'
  constructor(private http: HttpClient) {}

  getAll(page: any, pageSize: any): Observable<any> {
    return this.http.get(
      this.SERVER_API + '?page=' + page + '&size=' + pageSize
    )
  }

  get(id: string) {
    return this.http.get(this.SERVER_API + '/' + id)
  }

  save(codeGroups: any): Observable<any> {
    let result: Observable<Object>

    //		if (codeGroups['href']) {
    //			result = this.http.put(codeGroups.href, codeGroups);
    //		} else {
    //		myCases.status = {"id": myCases["status"]};
    //		myCases.caseType1 = {"id": myCases["caseType1"]};
    //		myCases.caseType2 = {"id": myCases["caseType2"]};
    //		myCases.caseType3 = {"id": myCases["caseType3"]};
    //		myCases.statusCode = {"id": myCases["statusCode"]};
    result = this.http.post(this.SERVER_API, codeGroups)
    //		}
    return result
  }
  remove(id: string) {
    console.info('CodeGroupsService remove==>>' + id)
    return this.http.delete(this.SERVER_API + '/' + id)
    //return this.http.delete(this.SERVER_API+'/delete/' + href);
  }
}
