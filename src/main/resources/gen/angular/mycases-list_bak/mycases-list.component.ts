import { Component, OnInit, AfterViewInit, ViewChild  } from '@angular/core';
import { Router } from '@angular/router';
import { MatPaginator, PageEvent } from '@angular/material';

import { MycasesService } from '../shared/mycases/mycases.service';

@Component({
  selector: 'app-mycases-list',
  templateUrl: './mycases-list.component.html',
  styleUrls: ['./mycases-list.component.css']
})
export class MycasesListComponent implements OnInit, AfterViewInit {
	mycasesList: Array<any>;
	pageItem: {};
	dataSource: [];
	tableColumns:  string[] = ["title", "caseType1", "caseType2", "caseType3", "status", "statusCode", "id"];
	
	@ViewChild(MatPaginator) paginator: MatPaginator;
	
	constructor(private router: Router, 
			private mycasesService: MycasesService) { }
	
	ngOnInit() {
		this.gotoPage(this.paginator.pageIndex);
			//this.mycasesList = data[0].content;
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
		this.mycasesService.getAll(pageNumber, this.paginator.pageSize).subscribe(data => {
			this.dataSource = data[0].content;
			this.paginator.pageSize=data[0].pageable.pageSize;
			this.paginator.pageIndex=data[0].pageable.pageNumber;
			this.paginator.length=data[0].totalElements;
		});
	}

	remove(id) {
		this.mycasesService.remove(id).subscribe(result => {
			this.loadPage();
		}, error => console.error(error));
	}
}
