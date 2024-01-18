import {Component, OnInit} from '@angular/core';
import {BrnDialogContentDirective, BrnDialogTriggerDirective} from "@spartan-ng/ui-dialog-brain";
import {CategoryDataTableComponent} from "../../components/datatable/category-data-table/category-data-table.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {KeycloakProfile} from "keycloak-js";
import {KeycloakService} from "keycloak-angular";
import {ChartModule} from "primeng/chart";
import {TerrainService} from "../../services/terrain.service";

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    BrnDialogContentDirective,
    BrnDialogTriggerDirective,
    CategoryDataTableComponent,
    FormsModule,
    ReactiveFormsModule,
    ChartModule
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{
  public isAdmin:Boolean;
  public profile? : KeycloakProfile;
  basicData: any;
  basicOptions: any;
  data: any;
  options: any;
  constructor(private keycloakService:KeycloakService,private _terrainService:TerrainService) {
    this.isAdmin=this.keycloakService.isUserInRole("ADMIN")
  }

  ngOnInit() {
    if(this.keycloakService.isLoggedIn()) {
      this.keycloakService.loadUserProfile().then(profile => {
        this.profile = profile;
      });
    }

    this.createCharts()
  }

  createCharts(){
    this._terrainService.getAll().subscribe({
      next:(res)=>{
        const documentStyle = getComputedStyle(document.documentElement);
        const textColor = documentStyle.getPropertyValue('--text-color');
        const textColorSecondary = documentStyle.getPropertyValue('--text-color-secondary');
        const surfaceBorder = documentStyle.getPropertyValue('--surface-border');
        const label = [0, 0, 0];
        const devices:any[]=res
        console.log(devices)
        devices.forEach(device => {
          if(device[0].taxes[0].payementType==="LATE"){
            label[0]++;
          }
        });

        console.log(label)

        this.data = {
          labels: ['Paid Slots', 'UnPaid Slots', 'Late Slots'],
          datasets: [
            {
              data: label,
              backgroundColor: [documentStyle.getPropertyValue('--green-500'), documentStyle.getPropertyValue('--red-500'), documentStyle.getPropertyValue('--gray-500')],
              hoverBackgroundColor: [documentStyle.getPropertyValue('--green-400'), documentStyle.getPropertyValue('--red-400'), documentStyle.getPropertyValue('--gray-400')]
            }
          ]
        };

        this.options = {
          plugins: {
            legend: {
              labels: {
                usePointStyle: true,
                color: textColor
              }
            }
          }
        };
        let names :String[]= []
        let deviceData:number[]=[]
        devices.forEach(device=>{
          names.push(device.name)
          deviceData.push(device.averageRating)
        })
        this.basicData = {
          labels: names,
          datasets: [
            {
              label: 'Slots',
              data: deviceData,
              backgroundColor: ['rgba(255, 159, 64, 0.2)', 'rgba(75, 192, 192, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(153, 102, 255, 0.2)'],
              borderColor: ['rgb(255, 159, 64)', 'rgb(75, 192, 192)', 'rgb(54, 162, 235)', 'rgb(153, 102, 255)'],
              borderWidth: 1
            }
          ]
        };

        this.basicOptions = {
          plugins: {
            legend: {
              labels: {
                color: textColor
              }
            }
          },
          scales: {
            y: {
              beginAtZero: true,
              ticks: {
                color: textColorSecondary
              },
              grid: {
                color: surfaceBorder,
                drawBorder: false
              }
            },
            x: {
              ticks: {
                color: textColorSecondary
              },
              grid: {
                color: surfaceBorder,
                drawBorder: false
              }
            }
          }
        };
      }
    })

  }
}
