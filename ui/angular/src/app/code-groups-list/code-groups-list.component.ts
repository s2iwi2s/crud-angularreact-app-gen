import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core'
import { Router } from '@angular/router'
import { MatPaginator } from '@angular/material'
import { MatSort } from '@angular/material/sort'

import { CodeGroupsService } from '../shared/code-groups/code-groups.service'

@Component({
  selector: 'app-code-groups-list',
  templateUrl: './code-groups-list.component.html',
  styleUrls: ['./code-groups-list.component.css'],
})
export class CodeGroupsListComponent implements OnInit, AfterViewInit {
  codeGroupsList: Array<any>
  pageItem: {}
  dataSource: []
  tableColumns: string[] = ['code', 'value', 'description', 'bool', 'num', 'id']

  @ViewChild(MatPaginator) paginator: MatPaginator

  constructor(
    private router: Router,
    private codeGroupsService: CodeGroupsService
  ) {}

  ngOnInit() {
    this.gotoPage(this.paginator.pageIndex)
    //this.codeGroupsList = data[0].content;
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
    this.paginator.page.pipe().subscribe(() => this.loadPage())
  }

  loadPage() {
    this.gotoPage(this.paginator.pageIndex)
  }

  gotoPage(pageNumber) {
    console.info('gotoPage: pageNumber=>' + pageNumber)
    this.codeGroupsService
      .getAll(pageNumber, this.paginator.pageSize)
      .subscribe((data) => {
        this.dataSource = data.codeGroupsList.content
        this.paginator.pageSize = data.codeGroupsList.pageable.pageSize
        this.paginator.pageIndex = data.codeGroupsList.pageable.pageNumber
        this.paginator.length = data.codeGroupsList.totalElements
      })
  }

  remove(id) {
    this.codeGroupsService.remove(id).subscribe(
      (result) => {
        this.loadPage()
      },
      (error) => console.error(error)
    )
  }
}
