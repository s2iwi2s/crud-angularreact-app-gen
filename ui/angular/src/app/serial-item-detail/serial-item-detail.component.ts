import { Component, OnDestroy, OnInit, Inject } from '@angular/core'
import { Subscription, Observable } from 'rxjs'
import { ActivatedRoute, Router } from '@angular/router'
import { FormControl, FormBuilder } from '@angular/forms'

import { SerialItemService } from '../shared/serial-item/serial-item.service'
import { PartItemService } from '../shared/part-item/part-item.service'
import { NgForm } from '@angular/forms'
import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog'

export interface PartItemDialogData {
  id: ''
  name: ''
}
@Component({
  selector: 'app-serial-item-detail',
  templateUrl: './serial-item-detail.component.html',
  styleUrls: ['./serial-item-detail.component.css'],
})

export class SerialItemDetailComponent implements OnInit, OnDestroy {
  myControl = new FormControl()
  filteredOptions: Observable<string[]>
  currId: string = '0'
  showPartItemSearch: boolean = false
  resp: any = {}
  details: any = {}

  partItemSearch: string = ''
  partItemSearchDataSource: []
  partItemSearchTableColumns: string[] = ['name', 'description', 'serialized']

  sub: Subscription

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private serialItemService: SerialItemService,
    private partItemService: PartItemService,
    public partItemDialog: MatDialog
  ) { }
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
    this.serialItemService.get(id).subscribe((res: any) => {
      this.resp = res
      this.details = this.resp.serialItem
      console.log(`this.details=>`, this.details)
      if (this.details.partItem == null) {
        this.details.partItem = { id: '', name: '' }
      }
      this.partItemSearch = this.details.partItem.name
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
      if (this.resp.responseStatus.code == 'FAIL') {
        alert(this.resp.status.code + ':' + this.resp.status.message)
      } else {
        //enable for autocomplete
        //this.filteredOptions = this.myControl.valueChanges
        //.pipe(
        //	startWith(''),
        //	map(value => this._filter(this.details.code))
        //);
        if (!res && this.currId != '-1') {
          console.log(`SerialItem with id '${id}' not found, returning to list`)
          this.gotoList()
        }
      }
    })
  }
  //	private _filter(value: string): string[] {
  //		const filterValue = value.toLowerCase();
  //		console.info("filterValue=" + filterValue);
  //		return this.codeList.filter(option => option.toLowerCase().includes(filterValue));
  //		//return this.codeList.filter(option => option.toLowerCase().indexOf(filterValue) === 0);
  //	}
  ngOnDestroy() {
    this.sub.unsubscribe()
  }
  gotoList() {
    this.router.navigate(['/serial-item-list'])
  }
  save(form: NgForm) {
    this.serialItemService.save(form).subscribe(
      (result: any) => {
        this.gotoList()
      },
      (error) => console.error(error)
    )
  }
  remove(id) {
    this.serialItemService.remove(id).subscribe(
      (result) => {
        this.gotoList()
      },
      (error) => console.error(error)
    )
  }
  searchPartItem = (obj) => {
    this.partItemService.searchPartItems(this.partItemSearch).subscribe(
      (data: any) => {
        if (data.partItemsSearch) {
          this.partItemSearchDataSource = data.partItemsSearch
        }
      },
      (error) => console.error(error)
    )
  }
  setPartItem = (id, name) => {
    console.log(`result=> id=${id}, name=${name}`)
    this.showPartItemSearch = false
    this.details.partItem = {
      id: id,
      name: name,
    }
  }
  showPartItemDialog() {
    this.showItemDialog(this.details.partItem.id, this.details.partItem.name)
  }
  showItemDialog(itemId, itemName) {
    const dialogRef = this.partItemDialog.open(PartItemDialog, {
      data: {
        id: itemId,
        name: itemName
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('[SerialItemDetailComponent][dialogRef.afterClosed] Dialog result:', result);
      this.details.partItem = {
        id: result.id,
        name: result.name,
      }
    });
  }
}

@Component({
  selector: 'item-dialog',
  templateUrl: './item-dialog.html',
})
export class PartItemDialog {
  partItemForm;
  partItemSearchDS: [];
  partItemSearchTC: string[] = ['name', 'description', 'serialized'];

  constructor(public dialogRef: MatDialogRef<PartItemDialog>,
    private formBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: PartItemDialogData,
    private partItemService: PartItemService) {
    this.partItemForm = this.formBuilder.group({
      id: this.data.id,
      name: this.data.name,
      partItemSearch: this.data.name
    });
  }
  setPartItem = (id, name) => {
    console.log(`[PartItemDialog.setPartItem] id=${id}, name=${name}`)

    this.dialogRef.close({
      id: id,
      name: name
    });
  }

  searchPartItem() {
    console.log(`[PartItemDialog.searchPartItem] ${this.partItemForm.value.partItemSearch}`, this.partItemForm)
    this.partItemService.searchPartItems(this.partItemForm.value.partItemSearch).subscribe(
      (resp: any) => {
        console.log(`[PartItemDialog.searchPartItem] resp=>`, resp)
        if (resp.partItemsSearch) {
          this.partItemSearchDS = resp.partItemsSearch
        }
      },
      (error) => console.error(error)
    )
  }

  closeDialog() {
    this.dialogRef.close(this.partItemForm.value);
  }
}