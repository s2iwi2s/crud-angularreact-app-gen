import { Component, OnInit, AfterViewInit, ViewChild  } from '@angular/core';
import { Router } from '@angular/router';
import { MatPaginator, PageEvent } from '@angular/material';

import { MyCaseService } from '../shared/my-case/my-case.service';

@Component({
  selector: 'app-my-case-list',
  templateUrl: './my-case-list.component.html',
  styleUrls: ['./my-case-list.component.css']
})
export class MyCaseListComponent implements OnInit, AfterViewInit {
	myCaseList: Array<any>;
	pageItem: {};
	dataSource: [];
	tableColumns:  string[] = [
		"title", 
		"status", 
		"caseType1", 
		"caseType2", 
		"caseType3", 
		"statusCode", 
		"comments", 

		"id"
	];
	
	@ViewChild(MatPaginator) paginator: MatPaginator;
	
	constructor(private router: Router, 
			private myCaseService: MyCaseService) { }
	
	ngOnInit() {
		this.gotoPage(this.paginator.pageIndex);
			//this.myCaseList = data[0].content;
			//this.pageItems = data[0];
//			 "pageable" : {
//				    "sort" : {
//				      "sorted" : false,
//				      "unsorted" : true,
//				      "empty" : true
//				    },
//				    "offset" : 0,
//				    "pageSize" : 25,
//				    "pageNumber" : 0,
//				    "paged" : true,
//				    "unpaged" : false
//				  },
//				  "last" : false,
//				  "totalPages" : 8,
//				  "totalElements" : 199,
//				  "size" : 25,
//				  "number" : 0,
//				  "sort" : {
//				    "sorted" : false,
//				    "unsorted" : true,
//				    "empty" : true
//				  },
//				  "first" : true,
//				  "numberOfElements" : 25,
//				  "empty" : false
		
	}
	
	ngAfterViewInit() {
		this.paginator.page.pipe().subscribe(()=>this.loadPage());
	}
	
	loadPage(){
		this.gotoPage(this.paginator.pageIndex);
	}
	
	gotoPage(pageNumber){
		console.info("gotoPage: pageNumber=>" + pageNumber);
		this.myCaseService.getAll(pageNumber, this.paginator.pageSize).subscribe(data => {
			this.dataSource = data.myCaseList.content;
			this.paginator.pageSize=data.myCaseList.pageable.pageSize;
			this.paginator.pageIndex=data.myCaseList.pageable.pageNumber;
			this.paginator.length=data.myCaseList.totalElements;
		});
	}

	remove(id) {
		this.myCaseService.remove(id).subscribe(result => {
			this.loadPage();
		}, error => console.error(error));
	}
}


