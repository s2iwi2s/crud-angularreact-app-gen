import { Component, OnInit, AfterViewInit, ViewChild  } from '@angular/core';
import { Router } from '@angular/router';
import { MatPaginator, PageEvent } from '@angular/material';

import { HomeService } from '../shared/home/home.service';

@Component({
  selector: 'app-home-list',
  templateUrl: './home-list.component.html',
  styleUrls: ['./home-list.component.css']
})
export class HomeListComponent implements OnInit, AfterViewInit {
	homeList: Array<any>;
	pageItem: {};
	dataSource: [];
	tableColumns:  string[] = [
		"urlStr", 
		"name", 

		"id"
	];
	
	@ViewChild(MatPaginator) paginator: MatPaginator;
	
	constructor(private router: Router, 
			private homeService: HomeService) { }
	
	ngOnInit() {
		this.gotoPage(this.paginator.pageIndex);
			//this.homeList = data[0].content;
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
		this.homeService.getAll(pageNumber, this.paginator.pageSize).subscribe(data => {
			this.dataSource = data.homeList.content;
			this.paginator.pageSize=data.homeList.pageable.pageSize;
			this.paginator.pageIndex=data.homeList.pageable.pageNumber;
			this.paginator.length=data.homeList.totalElements;
		});
	}

	remove(id) {
		this.homeService.remove(id).subscribe(result => {
			this.loadPage();
		}, error => console.error(error));
	}
}


