import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HlmButtonModule } from '@spartan-ng/ui-button-helm';
import { BrnMenuTriggerDirective } from '@spartan-ng/ui-menu-brain';
import { HlmMenuModule } from '@spartan-ng/ui-menu-helm';
import { BrnTableModule, } from '@spartan-ng/ui-table-brain';
import { HlmTableModule } from '@spartan-ng/ui-table-helm';
import { formatDate } from '../../../../../utlis/utli';
import { CommonModule } from '@angular/common';
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

@Component({
  selector: 'app-user-data-table',
  standalone: true,
  imports: [    
    FormsModule,
    BrnMenuTriggerDirective,
    HlmMenuModule,
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
    CommonModule,],
  templateUrl: './user-data-table.component.html',
  styleUrl: './user-data-table.component.css'
})
export class UserDataTableComponent {
  USER_DATA:any[]=[
    {
      id:1,
      full_name:"CHBALY Adil",
      username:"troike",
      email:"adil@gmail.com",
      addresse:"HAY ZITOUNE No28",
      role:"USER"
    },
    {
      id:1,
      full_name:"OHLALE Badr",
      username:"blank",
      email:"badr@gmail.com",
      addresse:"HAY ZITOUNE No28",
      role:"USER"
    },
    {
      id:3,
      full_name:"",
      username:"admin",
      email:"admin@gmail.com",
      addresse:"HAY ZITOUNE No28",
      role:"ADMIN"
    }
  ]
}
