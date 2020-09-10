import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

import { XYCLASSYXService } from '../shared/XYclassYX/XYCLASSYX.service';
import { NgForm } from '@angular/forms';
@Component({
	selector: 'app-XYCLASSYX-detail',
	templateUrl: './XYCLASSYX-detail.component.html',
	styleUrls: ['./XYCLASSYX-detail.component.css']
})
export class XYCLASSYXDetailComponent implements OnInit, OnDestroy {
	resp: any = {};
	details: any = {};
	caseType1List: any = [];
	caseType2List: any = [];
	caseType3List: any = [];
	statusList: any = [];
	statusCodeList: any = [];
	sub: Subscription;

	constructor(private route: ActivatedRoute,
			private router: Router,
		private XYclassYXService: XYCLASSYXService) {
	}
	ngOnInit() {
		this.sub = this.route.params.subscribe(params => {
			const id = params['id'];
			if (id) {
				this.XYclassYXService.get(id).subscribe((res: any) => {
					if (res) {
						this.resp = res;
						this.details = this.resp.XYclassYX;
						this.caseType1List = res.caseType1List;
						this.caseType2List = res.caseType2List;
						this.caseType3List = res.caseType3List;
						this.statusList = res.statusList;
						this.statusCodeList = res.statusCodeList;
						//this.mycases.href = mycases._links.self.href;
						//this.giphyService.get(mycases.name).subscribe(url => mycases.giphyUrl = url);
					} else {
						console.log(`XYCLASSYX with id '${id}' not found, returning to list`);
						this.gotoList();
					}
				});
			}
		});
	}
	ngOnDestroy() {
		this.sub.unsubscribe();
	}
	gotoList() {
		this.router.navigate(['/XYCLASSYX-list']);
	}
	save(form: NgForm) {
		this.XYclassYXService.save(form).subscribe(result => {
			this.gotoList();
		}, error => console.error(error));
	}
	remove(id) {
		this.XYclassYXService.remove(id).subscribe(result => {
			this.gotoList();
		}, error => console.error(error));
	}
}