import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription, Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import {FormControl} from '@angular/forms';
import {map, startWith} from 'rxjs/operators';

import { HomeService } from '../shared/home/home.service';
import { NgForm } from '@angular/forms';
@Component({
	selector: 'app-home-detail',
	templateUrl: './home-detail.component.html',
	styleUrls: ['./home-detail.component.css']
})
export class HomeDetailComponent implements OnInit, OnDestroy {
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
		private homeService: HomeService) {
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
		this.homeService.get(id).subscribe((res: any) => {
			this.resp = res;
			this.details = this.resp.home;
			
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
					console.log(`Home with id '${id}' not found, returning to list`);
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
		this.router.navigate(['/home-list']);
	}
	save(form: NgForm) {
		this.homeService.save(form).subscribe(result => {
			this.gotoList();
		}, error => console.error(error));
	}
	remove(id) {
		this.homeService.remove(id).subscribe(result => {
			this.gotoList();
		}, error => console.error(error));
	}
}


