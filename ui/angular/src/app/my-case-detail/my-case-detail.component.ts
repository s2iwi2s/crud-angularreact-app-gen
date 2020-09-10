import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription, Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl } from '@angular/forms';
import { map, startWith } from 'rxjs/operators';

import { MyCaseService } from '../shared/my-case/my-case.service';
import { NgForm } from '@angular/forms';
@Component({
	selector: 'app-my-case-detail',
	templateUrl: './my-case-detail.component.html',
	styleUrls: ['./my-case-detail.component.css']
})
export class MyCaseDetailComponent implements OnInit, OnDestroy {
	myControl = new FormControl();
	filteredOptions: Observable<string[]>;
	currId: string = '0';
	resp: any = {};
	// details: any = {};
	listService: any = {};
	details: any = {
		status: { id: '' },
		caseType1: { id: '' },
		caseType2: { id: '' },
		caseType3: { id: '' },
		statusCode: { id: '' }
	}
	/*
		statusList: any = [];
		caseType1List: any = [];
		caseType2List: any = [];
		caseType3List: any = [];
		statusCodeList: any = [];
	
	*/
	sub: Subscription;

	constructor(private route: ActivatedRoute, private router: Router,
		private myCaseService: MyCaseService) {
	}
	ngOnInit() {
		this.sub = this.route.params.subscribe(params => {
			this.currId = params['id'];
			if (!this.currId) {
				this.currId = '-1';
			}
			this.getDetails(this.currId);
		});
	}
	getDetails(id) {
		this.myCaseService.get(id).subscribe((res: any) => {
			this.resp = res;
			this.details = this.resp.myCase;
			this.listService = this.resp.listService;

			if (this.details.status == null) {
				this.details.status = { id: "" }
			}
			if (this.details.caseType1 == null) {
				this.details.caseType1 = { id: "" }
			}
			if (this.details.caseType2 == null) {
				this.details.caseType2 = { id: "" }
			}
			if (this.details.caseType3 == null) {
				this.details.caseType3 = { id: "" }
			}
			if (this.details.statusCode == null) {
				this.details.statusCode = { id: "" }
			}
			if (this.resp.responseStatus.code == 'FAIL') {
				alert(this.resp.status.code + ":" + this.resp.status.message);
			} else {
				//enable for autocomplete
				//this.filteredOptions = this.myControl.valueChanges
				//.pipe(
				//	startWith(''),
				//	map(value => this._filter(this.details.code))
				//);
				if (!res && this.currId != '-1') {
					console.log(`MyCase with id '${id}' not found, returning to list`);
					this.gotoList();
				}
			}
		});
	}
	//	private _filter(value: string): string[] {
	//		const filterValue = value.toLowerCase();
	//		console.info("filterValue=" + filterValue);
	//		return this.codeList.filter(option => option.toLowerCase().includes(filterValue));
	//		//return this.codeList.filter(option => option.toLowerCase().indexOf(filterValue) === 0);
	//	}
	ngOnDestroy() {
		this.sub.unsubscribe();
	}
	gotoList() {
		this.router.navigate(['/my-case-list']);
	}
	save(form: NgForm) {
		this.myCaseService.save(form).subscribe(result => {
			this.gotoList();
		}, error => console.error(error));
	}
	remove(id) {
		this.myCaseService.remove(id).subscribe(result => {
			this.gotoList();
		}, error => console.error(error));
	}
}


