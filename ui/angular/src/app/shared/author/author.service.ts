import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
	public API = 'http://localhost:8080';
	public SERVER_API = this.API + '/author';
	constructor(private http: HttpClient) { }

	getAll(page: any, pageSize: any): Observable<any> {
		return this.http.get(this.SERVER_API + '/list?page=' + page + "&size=" + pageSize);
	}

	get(id: string) {
		return this.http.get(this.SERVER_API + '/' + id);
	}

	save(author: any): Observable<any> {
		let result: Observable<Object>;
		
//		if (author['href']) {
//			result = this.http.put(author.href, author);
//		} else {
//		myCases.status = {"id": myCases["status"]};
//		myCases.caseType1 = {"id": myCases["caseType1"]};
//		myCases.caseType2 = {"id": myCases["caseType2"]};
//		myCases.caseType3 = {"id": myCases["caseType3"]};
//		myCases.statusCode = {"id": myCases["statusCode"]};
		result = this.http.post(this.SERVER_API+'/save', author);
//		}
		return result;
	}
	remove(id: string) {
		console.info("AuthorService remove==>>" + id);
		return this.http.get(this.SERVER_API + '/delete/' + id);
		//return this.http.delete(this.SERVER_API+'/delete/' + href);
	}
}


