import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core'
import { Router } from '@angular/router'
import { MatPaginator, PageEvent } from '@angular/material'

import { PartItemService } from '../shared/part-item/part-item.service'

@Component({
  selector: 'app-part-item-list',
  templateUrl: './part-item-list.component.html',
  styleUrls: ['./part-item-list.component.css'],
})
export class PartItemListComponent implements OnInit, AfterViewInit {
  partItemList: Array<any>
  pageItem: {}
  dataSource: []
  tableColumns: string[] = ['name', 'description', 'serialized', 'id']

  @ViewChild(MatPaginator) paginator: MatPaginator

  constructor(
    private router: Router,
    private partItemService: PartItemService
  ) {}

  ngOnInit() {
    this.gotoPage(this.paginator.pageIndex)
    //this.partItemList = data[0].content;
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
    this.partItemService
      .getAll(pageNumber, this.paginator.pageSize)
      .subscribe((data) => {
        this.dataSource = data.partItemList.content
        this.paginator.pageSize = data.partItemList.pageable.pageSize
        this.paginator.pageIndex = data.partItemList.pageable.pageNumber
        this.paginator.length = data.partItemList.totalElements
      })
  }

  remove(id) {
    this.partItemService.remove(id).subscribe(
      (result) => {
        this.loadPage()
      },
      (error) => console.error(error)
    )
  }
}
