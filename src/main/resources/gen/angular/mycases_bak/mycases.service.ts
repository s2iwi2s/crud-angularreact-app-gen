import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MycasesService {
	public API = 'http://localhost:8080';
	public MYCASES_API = this.API + '/myCases';
	constructor(private http: HttpClient) { }

	getAll(page: any, pageSize: any): Observable<any> {
		return this.http.get(this.MYCASES_API + '/list?page=' + page + "&size=" + pageSize);
	}

	get(id: string) {
		return this.http.get(this.MYCASES_API + '/' + id);
	}

	save(mycases: any): Observable<any> {
		let result: Observable<Object>;
//		if (mycases['href']) {
//			result = this.http.put(mycases.href, mycases);
//		} else {
			result = this.http.post(this.MYCASES_API+'/save', mycases);
//		}
		return result;
	}
	remove(id: string) {
		console.info("MycasesService remove==>>" + id);
		return this.http.get(this.MYCASES_API + '/delete/' + id);
		//return this.http.delete(this.MYCASES_API+'/delete/' + href);
	}
}
