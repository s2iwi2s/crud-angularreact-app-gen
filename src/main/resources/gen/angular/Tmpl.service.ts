import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { URL_BASE } from '../constants'

@Injectable({
  providedIn: 'root'
})
export class XYCLASSYXService {
	public SERVER_API = URL_BASE + '/XYclassYXs';
	constructor(private http: HttpClient) { }

	getAll(page: any, pageSize: any): Observable<any> {
		return this.http.get(this.SERVER_API + '?page=' + page + "&size=" + pageSize);
	}

	get(id: string) {
		return this.http.get(this.SERVER_API + '/' + id);
	}

	save(XYclassYX: any): Observable<any> {
		let result: Observable<Object>;
		
//		if (XYclassYX['href']) {
//			result = this.http.put(XYclassYX.href, XYclassYX);
//		} else {
//		myCases.status = {"id": myCases["status"]};
//		myCases.caseType1 = {"id": myCases["caseType1"]};
//		myCases.caseType2 = {"id": myCases["caseType2"]};
//		myCases.caseType3 = {"id": myCases["caseType3"]};
//		myCases.statusCode = {"id": myCases["statusCode"]};
		result = this.http.post(this.SERVER_API, XYclassYX);
//		}
		return result;
	}
	remove(id: string) {
		console.info("XYCLASSYXService remove==>>" + id);
		//return this.http.get(this.SERVER_API + '/delete/' + id);
		//return this.http.delete(this.SERVER_API+'/delete/' + href);
		return this.http.delete(this.SERVER_API + '/' + id);
	}
}
