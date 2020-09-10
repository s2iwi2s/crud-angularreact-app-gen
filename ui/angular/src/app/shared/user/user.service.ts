import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
	public API = 'http://localhost:8080';
	public SERVER_API = this.API + '/user';
	constructor(private http: HttpClient) { }

	getAll(page: any, pageSize: any): Observable<any> {
		return this.http.get(this.SERVER_API + '/list?page=' + page + "&size=" + pageSize);
	}

	get(id: string) {
		return this.http.get(this.SERVER_API + '/' + id);
	}

	save(user: any): Observable<any> {
		let result: Observable<Object>;
		
//		if (user['href']) {
//			result = this.http.put(user.href, user);
//		} else {
//		myCases.status = {"id": myCases["status"]};
//		myCases.caseType1 = {"id": myCases["caseType1"]};
//		myCases.caseType2 = {"id": myCases["caseType2"]};
//		myCases.caseType3 = {"id": myCases["caseType3"]};
//		myCases.statusCode = {"id": myCases["statusCode"]};
		result = this.http.post(this.SERVER_API+'/save', user);
//		}
		return result;
	}
	remove(id: string) {
		console.info("UserService remove==>>" + id);
		return this.http.get(this.SERVER_API + '/delete/' + id);
		//return this.http.delete(this.SERVER_API+'/delete/' + href);
	}
}


