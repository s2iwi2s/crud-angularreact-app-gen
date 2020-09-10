import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { URL_BASE } from '../constants'

@Injectable({
  providedIn: 'root',
})
export class SerialItemService {
  public SERVER_API = URL_BASE + '/serialItems'
  constructor(private http: HttpClient) {}

  getAll(page: any, pageSize: any): Observable<any> {
    return this.http.get(
      this.SERVER_API + '?page=' + page + '&size=' + pageSize
    )
  }

  get(id: string) {
    return this.http.get(this.SERVER_API + '/' + id)
  }

  save(serialItem: any): Observable<any> {
    let result: Observable<Object>

    if (!serialItem.partItem.id) {
      serialItem.partItem = null
    }
    console.log(JSON.stringify(serialItem))
    result = this.http.post(this.SERVER_API, serialItem)
    //		}
    return result
  }
  remove(id: string) {
    console.info('SerialItemService remove==>>' + id)
    //return this.http.get(this.SERVER_API + '/delete/' + id);
    //return this.http.delete(this.SERVER_API+'/delete/' + href);
    return this.http.delete(this.SERVER_API + '/' + id)
  }
}
