import {Component, OnInit} from '@angular/core';
import { TauxDataTableComponent } from '../../components/datatable/taux-data-table/taux-data-table.component';
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
import { HlmButtonModule } from '@spartan-ng/ui-button-helm';
import { HlmInputDirective } from '@spartan-ng/ui-input-helm';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {TerrainService} from "../../services/terrain.service";
import {TaxeService} from "../../services/taxe.service";
@Component({
  selector: 'app-taxe',
  standalone: true,
  imports: [
    HlmInputDirective,
    BrnDialogContentDirective,
    BrnDialogTriggerDirective,
    HlmDialogComponent,
    HlmDialogContentComponent,
    HlmDialogDescriptionDirective,
    HlmDialogFooterComponent,
    HlmDialogHeaderComponent,
    HlmDialogTitleDirective,
    TauxDataTableComponent,
    HlmButtonModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  templateUrl: './taxe.component.html',
  styleUrl: './taxe.component.css',
})
export class TaxeComponent implements OnInit{
  taxeForm!: FormGroup;
  TERRAIN_DATA:any[]=[]
  constructor(private _taxeService:TaxeService,private _fb:FormBuilder,private _terrainService:TerrainService) {
    this.taxeForm=this._fb.group({
      label:'',
      description:'',
      dueTime:'',
      terrain_id:'',
    })
  }

  ngOnInit() {
    this.getAllTerrain();
  }

  onAddTaxe(){
    console.log(this.taxeForm.value)
    if(this.taxeForm.valid){
      this._taxeService.addTaxe(this.taxeForm.value).subscribe({
        next:(res)=>{
          this.taxeForm.reset()
          window.location.reload()
        },
        error:(err)=>{
          console.log(err)
        }
      })
    }
  }

  getAllTerrain(){
    this._terrainService.getTerrainForTaxes().subscribe({
      next:(res)=>{
        this.TERRAIN_DATA=res
      },
      error:(err)=>{
        console.log(err)
      }
    })
  }
}
