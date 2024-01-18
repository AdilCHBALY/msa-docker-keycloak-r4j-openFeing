import { CommonModule } from '@angular/common';
import {Component, OnInit} from '@angular/core';
import { HlmButtonModule } from '@spartan-ng/ui-button-helm';
import { BrnAccordionContentComponent } from '@spartan-ng/ui-accordion-brain';
import {
  HlmAccordionContentDirective,
  HlmAccordionDirective,
  HlmAccordionIconDirective,
  HlmAccordionItemDirective,
  HlmAccordionTriggerDirective,
} from '@spartan-ng/ui-accordion-helm';
import { HlmIconComponent } from '@spartan-ng/ui-icon-helm';
import { formatDate } from '../../../../utlis/utli';
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
import { HlmInputDirective } from '@spartan-ng/ui-input-helm';
import {TerrainService} from "../../services/terrain.service";
import {CategoryService} from "../../services/category.service";
import {KeycloakProfile} from "keycloak-js";
import {KeycloakService} from "keycloak-angular";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {TaxeService} from "../../services/taxe.service";

@Component({
  selector: 'app-terrain',
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
    BrnAccordionContentComponent,
    HlmButtonModule,
    HlmIconComponent,
    CommonModule,
    HlmAccordionContentDirective,
    HlmAccordionDirective,
    HlmAccordionIconDirective,
    HlmAccordionItemDirective,
    HlmAccordionTriggerDirective,
    ReactiveFormsModule,
  ],
  templateUrl: './terrain.component.html',
  styleUrl: './terrain.component.css',
})
export class TerrainComponent implements OnInit{
  terrainForm!:FormGroup
  SLOT_DATA: any[] = [];
  CATEGORY_DATA:any[]=[];
  public isAdmin:Boolean;
  public profile? : KeycloakProfile;
  public loading :Boolean = true

  constructor(
    private _fb:FormBuilder,
    private keycloakService:KeycloakService,
    private _taxeService:TaxeService,
    private _terrainService:TerrainService,
    private _categoryService:CategoryService) {
    this.terrainForm=this._fb.group({
      name:'',
      address:'',
      area:0,
      category_id:'',
      description:''
    })
    this.isAdmin=this.keycloakService.isUserInRole("ADMIN")
  }

  ngOnInit() {
    if(this.keycloakService.isLoggedIn()) {
      this.keycloakService.loadUserProfile().then(profile => {
        this.profile = profile;
      });
    }
    this.getAllTerrain()
    this.getAllCategories()
  }

  onDeleteTerrain(id:number){
    this._terrainService.deleteTerrain(id).subscribe({
      next:(res)=>{
        this.getAllTerrain()
      },
      error:(err)=>{
        console.log(err)
      }
    })
  }

  onDeleteTaxeFromTerrain(id:number){
    this._taxeService.deleteTaxe(id).subscribe({
      next:(res)=>{
        this.getAllTerrain()
      },
      error:(err)=>{
        console.log(err)
      }
    })
  }

  onSubmit(){
      if(this.terrainForm.valid){
        const data = {
          ...this.terrainForm.value,
          client_id:this.profile?.id
        }
        this._terrainService.addTerrain(data).subscribe({
          next:(res)=>{
            this.getAllTerrain()
            this.terrainForm.reset()
          },
          error:(err)=>{
            console.log(err)
          }
        })
      }
  }

  getAllCategories(){
    this._categoryService.getAllCategories().subscribe({
      next:(res)=>{
        this.CATEGORY_DATA=res;
      },
      error:(err)=>{
        console.log(err)
      }
    })
  }

  payTerrain(id:number){
    this._terrainService.pay(id).subscribe({
      next:(res)=>{
        this.getAllTerrain()
      },
      error:(err)=>{
        console.log(err)
      }
    })
  }

  getAllTerrain(){
    if(this.isAdmin){
      this._terrainService.getAll().subscribe({
        next:(res)=>{
          this.SLOT_DATA=res[0]
        },
        error:(err)=>{
          console.log(err)
        }
      })
    }else{
      this._terrainService.getByClient().subscribe({
        next:(res)=>{
          this.SLOT_DATA=res
        },
        error:(err)=>{
          console.log(err)
        }
      })
    }

  }
}
