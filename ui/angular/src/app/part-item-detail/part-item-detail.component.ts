import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription, Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import {FormControl} from '@angular/forms';
import {map, startWith} from 'rxjs/operators';

import { PartItemService } from '../shared/part-item/part-item.service';
import { NgForm } from '@angular/forms';
@Component({
	selector: 'app-part-item-detail',
	templateUrl: './part-item-detail.component.html',
	styleUrls: ['./part-item-detail.component.css']
})
export class PartItemDetailComponent implements OnInit, OnDestroy {
	myControl = new FormControl();
	filteredOptions: Observable<string[]>;
	currId: string = '0';
	resp: any = {};
	details: any = {};
//	details: any = {
//			status: {id:''},
//			caseType1: {id:''},
//			caseType2: {id:''},
//			caseType3: {id:''},
//			statusCode: {id:''}
//	}
	

	sub: Subscription;

	constructor(private route: ActivatedRoute, private router: Router,
		private partItemService: PartItemService) {
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
	getDetails(id){
		this.partItemService.get(id).subscribe((res: any) => {
			this.resp = res;
			this.details = this.resp.partItem;
			
//			if(this.details.status == null){
//				this.details.status = {id:""}
//			}
//			if(this.details.caseType1 == null){
//				this.details.caseType1 = {id:""}
//			}
//			if(this.details.caseType2 == null){
//				this.details.caseType2 = {id:""}
//			}
//			if(this.details.caseType3 == null){
//				this.details.caseType3 = {id:""}
//			}
//			if(this.details.statusCode == null){
//				this.details.statusCode = {id:""}
//			}
			if(this.resp.responseStatus.code == 'FAIL'){
				alert(this.resp.status.code + ":" + this.resp.status.message);
			}else{
				//enable for autocomplete
				//this.filteredOptions = this.myControl.valueChanges
				//.pipe(
				//	startWith(''),
				//	map(value => this._filter(this.details.code))
				//);
				if(!res && this.currId != '-1'){
					console.log(`PartItem with id '${id}' not found, returning to list`);
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
		this.router.navigate(['/part-item-list']);
	}
	save(form: NgForm) {
		this.partItemService.save(form).subscribe(result => {
			this.gotoList();
		}, error => console.error(error));
	}
	remove(id) {
		this.partItemService.remove(id).subscribe(result => {
			this.gotoList();
		}, error => console.error(error));
	}
}


