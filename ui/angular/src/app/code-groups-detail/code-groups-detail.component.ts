import { Component, OnDestroy, OnInit } from '@angular/core'
import { Subscription, Observable } from 'rxjs'
import { ActivatedRoute, Router } from '@angular/router'
import { FormControl } from '@angular/forms'
import { map, startWith } from 'rxjs/operators'

import { CodeGroupsService } from '../shared/code-groups/code-groups.service'
import { NgForm } from '@angular/forms'
@Component({
  selector: 'app-code-groups-detail',
  templateUrl: './code-groups-detail.component.html',
  styleUrls: ['./code-groups-detail.component.css'],
})
export class CodeGroupsDetailComponent implements OnInit, OnDestroy {
  myControl = new FormControl()
  filteredOptions: Observable<string[]>
  currId: string = '0'
  resp: any = {}
  details: any = {
    code: '',
  }
  codeList: any = []

  sub: Subscription

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private codeGroupsService: CodeGroupsService
  ) {}
  ngOnInit() {
    this.sub = this.route.params.subscribe((params) => {
      this.currId = params['id']
      if (!this.currId) {
        this.currId = '-1'
      }
      this.getDetails(this.currId)
    })
  }
  getDetails(id) {
    this.codeGroupsService.get(id).subscribe((res: any) => {
      this.resp = res
      this.details = this.resp.codeGroups
      this.codeList = res.codeList

      if (this.resp.responseStatus.code == 'FAIL') {
        alert(this.resp.status.code + ':' + this.resp.status.message)
      } else {
        if (!this.details.code) {
          this.details.code = ''
        }
        // enable for autocomplete
        this.filteredOptions = this.myControl.valueChanges.pipe(
          startWith(''),
          map((value) => this._filter(this.details.code))
        )
        if (!res && this.currId != '-1') {
          console.log(`CodeGroups with id '${id}' not found, returning to list`)
          this.gotoList()
        }
      }
    })
  }
  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase()
    console.info('filterValue=' + filterValue)
    return this.codeList.filter((option) =>
      option.toLowerCase().includes(filterValue)
    )
    //return this.codeList.filter(option => option.toLowerCase().indexOf(filterValue) === 0);
  }
  ngOnDestroy() {
    this.sub.unsubscribe()
  }
  gotoList() {
    this.router.navigate(['/code-groups-list'])
  }
  save(form: NgForm) {
    this.codeGroupsService.save(form).subscribe(
      (result) => {
        this.gotoList()
      },
      (error) => console.error(error)
    )
  }
  remove(id) {
    this.codeGroupsService.remove(id).subscribe(
      (result) => {
        this.gotoList()
      },
      (error) => console.error(error)
    )
  }
}
