import { Component, OnInit, AfterViewInit, ViewChild  } from '@angular/core';
import { Router } from '@angular/router';
import { MatPaginator, PageEvent } from '@angular/material';

import { XYCLASSYXService } from '../shared/XYclass-kebabYX/XYclass-kebabYX.service';

@Component({
  selector: 'app-XYclass-kebabYX-list',
  templateUrl: './XYclass-kebabYX-list.component.html',
  styleUrls: ['./XYclass-kebabYX-list.component.css']
})
export class XYCLASSYXListComponent implements OnInit, AfterViewInit {
	XYclassYXList: Array<any>;
	pageItem: {};
	dataSource: [];
	tableColumns:  string[] = [
XYlist-fieldsYX
		"id"
	];
	
	@ViewChild(MatPaginator) paginator: MatPaginator;
	
	constructor(private router: Router, 
			private XYclassYXService: XYCLASSYXService) { }
	
	ngOnInit() {
		this.gotoPage(this.paginator.pageIndex);
			//this.XYclassYXList = data[0].content;
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
		this.XYclassYXService.getAll(pageNumber, this.paginator.pageSize).subscribe(data => {
			this.dataSource = data.XYclassYXList.content;
			this.paginator.pageSize=data.XYclassYXList.pageable.pageSize;
			this.paginator.pageIndex=data.XYclassYXList.pageable.pageNumber;
			this.paginator.length=data.XYclassYXList.totalElements;
		});
	}

	remove(id) {
		this.XYclassYXService.remove(id).subscribe(result => {
			this.loadPage();
		}, error => console.error(error));
	}
}
