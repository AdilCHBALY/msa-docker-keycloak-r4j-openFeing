import {Component, OnInit} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HlmButtonModule } from '@spartan-ng/ui-button-helm';
import { BrnMenuTriggerDirective } from '@spartan-ng/ui-menu-brain';
import { HlmMenuModule } from '@spartan-ng/ui-menu-helm';
import { BrnTableModule, } from '@spartan-ng/ui-table-brain';
import { HlmTableModule } from '@spartan-ng/ui-table-helm';
import {convertDateFormat, formatCreatedAt, formatDate} from '../../../../../utlis/utli';
import { HlmScrollAreaComponent } from '@spartan-ng/ui-scrollarea-helm';
import {
  HlmMenuComponent,
  HlmMenuGroupComponent,
  HlmMenuItemDirective,
  HlmMenuItemIconDirective,
  HlmMenuItemSubIndicatorComponent,
  HlmMenuLabelComponent,
  HlmMenuSeparatorComponent,
  HlmMenuShortcutComponent,
  HlmSubMenuComponent,
} from '@spartan-ng/ui-menu-helm';
import { CommonModule } from '@angular/common';
import {TaxeService} from "../../../services/taxe.service";

import {
  BrnDialogContentDirective,
  BrnDialogTriggerDirective,
} from '@spartan-ng/ui-dialog-brain';
import {
  HlmDialogComponent,
  HlmDialogContentComponent,
  HlmDialogDescriptionDirective,
  HlmDialogFooterComponent,
  HlmDialogHeaderComponent,
  HlmDialogTitleDirective,
} from '@spartan-ng/ui-dialog-helm';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
} from '@angular/forms';
import { HlmInputDirective } from '@spartan-ng/ui-input-helm';
import {TerrainService} from "../../../services/terrain.service";

@Component({
  selector: 'app-taux-data-table',
  standalone: true,
  imports: [
    HlmInputDirective,
    FormsModule,
    ReactiveFormsModule,
    BrnMenuTriggerDirective,
    HlmMenuModule,
    BrnDialogContentDirective,
    BrnDialogTriggerDirective,
    HlmDialogComponent,
    HlmDialogContentComponent,
    HlmDialogDescriptionDirective,
    HlmDialogFooterComponent,
    HlmDialogHeaderComponent,
    HlmDialogTitleDirective,
    BrnTableModule,
    HlmTableModule,
    HlmButtonModule,
    HlmScrollAreaComponent,
    HlmMenuComponent,
    HlmMenuGroupComponent,
    HlmMenuItemDirective,
    HlmMenuItemIconDirective,
    HlmMenuItemSubIndicatorComponent,
    HlmMenuLabelComponent,
    HlmMenuSeparatorComponent,
    HlmMenuShortcutComponent,
    HlmSubMenuComponent,
    CommonModule,
  ],
  templateUrl: './taux-data-table.component.html',
  styleUrl: './taux-data-table.component.css'
})
export class TauxDataTableComponent implements OnInit{
  TAUX_DATA:any[]=[]
  TERRAIN_DATA:any[]=[]
  taxeForm!: FormGroup;
  constructor(private _taxeService:TaxeService,private _fb:FormBuilder,private _terrainService:TerrainService) {
    this.taxeForm=this._fb.group({
      label:'',
      description:'',
      dueTime:'',
      terrain_id:'',
    })
  }

  ngOnInit() {
    this.getTaxes();
    this.getAllTerrain();
  }

  getAllTerrain(){
    this._terrainService.getAll().subscribe({
      next:(res)=>{
        this.TERRAIN_DATA=res
      },
      error:(err)=>{
        console.log(err)
      }
    })
  }
  onEditTaxe(id:number){
    console.log(this.taxeForm.value)
    console.log(id)
    if(this.taxeForm.valid){
      this._taxeService.updateTaxe(id,this.taxeForm.value).subscribe({
        next:(res)=>{
          this.getTaxes()
      },
        error:(err)=>{
          console.log(err)
        }
      })
    }
  }

  getTaxes(){
    this._taxeService.getAll().subscribe({
      next:(res)=>{
        this.TAUX_DATA=res.map((taxe:any)=>{
          return {
            ...taxe,
            createdAt: convertDateFormat(taxe.createdAt),
            dueTime: convertDateFormat(taxe.dueTime),
          }
        })
      },
      error: (err) => {
        console.log(err);
      },
    })
  }

  onDelete(id:number){
    this._taxeService.deleteTaxe(id).subscribe({
      next:(res)=>{
        this.getTaxes();
      },
      error: (err) => {
        console.log(err);
      },
    })
  }
}
