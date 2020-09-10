import { Component, OnInit, AfterViewInit, ViewChild  } from '@angular/core';
import { Router } from '@angular/router';
import { MatPaginator, PageEvent } from '@angular/material';

import { SerialItemService } from '../shared/serial-item/serial-item.service';

@Component({
  selector: 'app-serial-item-list',
  templateUrl: './serial-item-list.component.html',
  styleUrls: ['./serial-item-list.component.css']
})
export class SerialItemListComponent implements OnInit, AfterViewInit {
	serialItemList: Array<any>;
	pageItem: {};
	dataSource: [];
	tableColumns:  string[] = [
		"name", 
		"description", 
		"partItem", 
		"status", 

		"id"
	];
	
	@ViewChild(MatPaginator) paginator: MatPaginator;
	
	constructor(private router: Router, 
			private serialItemService: SerialItemService) { }
	
	ngOnInit() {
		this.gotoPage(this.paginator.pageIndex);
			//this.serialItemList = data[0].content;
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
		this.serialItemService.getAll(pageNumber, this.paginator.pageSize).subscribe(data => {
			this.dataSource = data.serialItemList.content;
			this.paginator.pageSize=data.serialItemList.pageable.pageSize;
			this.paginator.pageIndex=data.serialItemList.pageable.pageNumber;
			this.paginator.length=data.serialItemList.totalElements;
		});
	}

	remove(id) {
		this.serialItemService.remove(id).subscribe(result => {
			this.loadPage();
		}, error => console.error(error));
	}
}


